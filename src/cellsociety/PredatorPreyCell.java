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
        hasBeenUpdated = false;
    }

    @Override
    protected void resetState(){
        previousState = currentState;
        hasBeenUpdated = false;
    }


    @Override
    public void updateCellValue(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        findNeighbors(gridOfCells, cellColumn, cellRow);
        if (hasBeenUpdated == false){
            if (previousState == FISH){
                for (Cell neighbor : neighborArray){
                    if (neighbor.getPreviousState() == WATER && neighbor.hasBeenUpdated != true){
                        neighbor.currentState = FISH;
                        neighbor.reproductiveTimer = reproductiveTimer + 1;
                        neighbor.hasBeenUpdated = true;
                        currentState = WATER; //if reproduction timer is Good, FISH
                        if (reproductiveTimer >= REPRODUCETIME) {currentState = FISH;}
                        reproductiveTimer = 0;
                        hasBeenUpdated = true;
                        return;
                    }
                }
                currentState = previousState;
            }
            currentState = previousState;
        }
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
