package cellsociety;

import java.util.ArrayList;

public class PredatorPreyCell extends Cell{
    private static final int WATER = 0;
    private static final int FISH = 1;
    private static final int SHARK = 2;
    private static final int REPRODUCETIME = 4;

    public PredatorPreyCell(int currentState){
        super(currentState);
        neighborArray = new ArrayList<Cell>();
        reproductiveTimer = 0;
        energy = 5;
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
        if (!hasBeenUpdated){
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
                currentState = WATER; //if reproduction timer is Good, FISH
                if (reproductiveTimer >= REPRODUCETIME) {currentState = FISH;}
                moveAnimal(neighbor, FISH);
                return true;
            }
        }
        return false;
    }

    private boolean updateShark() {
        Cell fish = null;
        currentState = WATER;
        if (energy == 0){return true;}
        energy --;
        for (Cell neighbor : neighborArray){
            if (!neighbor.hasBeenUpdated){
                if (neighbor.getPreviousState() == FISH || neighbor.getPreviousState() == WATER){
                    fish = neighbor;
                    if (neighbor.getPreviousState() == FISH){energy ++; moveShark(neighbor, SHARK); return true;}
                }
            }
        }
        if (fish !=null && fish.getPreviousState() == WATER){moveShark(fish, SHARK); return true;}
        return false;
    }

    private void moveShark(Cell neighbor, int neighborCurrentState){
        moveAnimal(neighbor, neighborCurrentState);
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
