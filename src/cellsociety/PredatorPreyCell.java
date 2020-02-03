package cellsociety;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

public class PredatorPreyCell extends Cell{
    private static final int WATER = 0;
    private static final int FISH = 1;
    private static final int SHARK = 2;
    private static final int REPRODUCETIME = 4;

    private ArrayList<Cell> water = new ArrayList<Cell>();

    public PredatorPreyCell(int currentState, int dimension){
        super (currentState, dimension);
        neighborArray = new ArrayList<Cell>();
        myColorArray = new Color[]{Color.BLUE, Color.GREEN, Color.ORANGE};
        reproductiveTimer = 0;
        energy = 2;
        hasBeenUpdated = false;
    }

    @Override
    protected void resetState(){
        previousState = currentState;
        hasBeenUpdated = false;
    }


    @Override
    public void updateCellValue(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        neighborArray.clear();
        findNeighbors(gridOfCells, cellColumn, cellRow);
        water.clear();
        if (!hasBeenUpdated){
            water.clear();
            reproductiveTimer ++;
            if (previousState == FISH){
                hasBeenUpdated = true;
                if(updateFish()){return;}
            }
            else if (previousState == SHARK){
                hasBeenUpdated = true;
                if(updateShark()){return;}
            }
            currentState = previousState;
        }
    }

    private boolean updateFish() {
        for (Cell neighbor : neighborArray){
            if (neighbor.getPreviousState() == WATER && !neighbor.hasBeenUpdated){
                water.add(neighbor);
            }
        }
        if(water.size()>0){
            Cell neighbor = RandomWaterCell();
            currentState = WATER; //if reproduction timer is Good, FISH
            if (reproductiveTimer >= REPRODUCETIME) {currentState = FISH;}
            moveAnimal(neighbor, FISH);
            return true;
        }
        return false;
    }

    private Cell RandomWaterCell() {
        Random rand = new Random();
        int random = rand.nextInt(water.size());
        return water.get(random);
    }

    private boolean updateShark() {
        currentState = WATER;
        if (energy == 0){return true;}
        for (Cell neighbor : neighborArray){
            if (!neighbor.hasBeenUpdated){
                if (neighbor.getPreviousState() == FISH || neighbor.getPreviousState() == WATER){
                    water.add(neighbor);
                    if (neighbor.getPreviousState() == FISH){energy = energy+0.5; moveShark(neighbor); return true;}
                }
            }
        }
        energy --;
        if (water.size()>0){
            Cell neighbor = RandomWaterCell();
            moveShark(neighbor);
            return true;
        }
        return false;
    }

    private void moveShark(Cell neighbor){
        moveAnimal(neighbor, SHARK);
        neighbor.energy = energy;
        if (reproductiveTimer >= REPRODUCETIME){
            currentState = SHARK;
            reproductiveTimer = 0;
            energy = 5;
        }
    }

    private void moveAnimal(Cell neighbor, int neighborCurrentState){
        neighbor.currentState = neighborCurrentState;
        neighbor.reproductiveTimer = reproductiveTimer;
        neighbor.hasBeenUpdated = true;
    }

    @Override
    protected void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow) {
        int[] northNeighbor = {cellColumn, cellRow-1};
        int[] southNeighbor = {cellColumn, cellRow+1};
        int[] eastNeighbor = {cellColumn+1, cellRow};
        int[] westNeighbor = {cellColumn-1, cellRow};
        populateNeighbors(gridOfCells, northNeighbor);
        populateNeighbors(gridOfCells, southNeighbor);
        populateNeighbors(gridOfCells, eastNeighbor);
        populateNeighbors(gridOfCells, westNeighbor);
    }

    @Override
    protected void populateNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int[] cellCoordinate){
        int column = cellCoordinate[0];
        int row = cellCoordinate[1];
        if(column < 0){
            column = column + gridOfCells.size();
        }
        else if (column > gridOfCells.size() - 1) { column = 0;}

        if(row < 0){
            row = row + gridOfCells.get(column).size();
        }
        else if (row > gridOfCells.size() - 1) { row = 0;}

        neighborArray.add(gridOfCells.get(column).get(row));
    }

}

