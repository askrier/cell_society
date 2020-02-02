package cellsociety;

import java.util.ArrayList;

public class GameOfLifeCell extends Cell{
    private static final int DEAD = 0;
    private static final int ALIVE = 1;

    public GameOfLifeCell(int currentState){
        super(currentState);
        neighborArray = new ArrayList<Cell>();
    }

    /**
     * @see Cell#updateCellValue(ArrayList, int, int)
     * @param gridOfCells
     * @param cellColumn
     * @param cellRow
     */
    @Override
    public void updateCellValue(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        neighborArray.clear();
        findNeighbors(gridOfCells, cellColumn, cellRow);
        int numAliveNeighbors = 0;
        for (Cell neighbor : neighborArray) {
            if (neighbor.getPreviousState() == ALIVE) {
                    numAliveNeighbors+=1;
            }
        }
        //check for each state
        if(previousState==DEAD && numAliveNeighbors==3){
                currentState = ALIVE;
                return;
        }
        else if(previousState==ALIVE && (numAliveNeighbors<2 || numAliveNeighbors>3)){
                currentState = DEAD;
                return;
            }
        currentState = previousState;
    }


    @Override
    public void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        int[] northNeighbor = {cellColumn, cellRow-1};
        int[] northEastNeighbor = {cellColumn+1, cellRow-1};
        int[] northWestNeighbor = {cellColumn-1, cellRow-1};
        int[] southNeighbor = {cellColumn, cellRow+1};
        int[] southEastNeighbor = {cellColumn+1, cellRow+1};
        int[] southWestNeighbor = {cellColumn-1, cellRow+1};
        int[] eastNeighbor = {cellColumn+1, cellRow};
        int[] westNeighbor = {cellColumn-1, cellRow};
        populateNeighbors(gridOfCells, northNeighbor);
        populateNeighbors(gridOfCells, southNeighbor);
        populateNeighbors(gridOfCells, eastNeighbor);
        populateNeighbors(gridOfCells, westNeighbor);
        populateNeighbors(gridOfCells, northWestNeighbor);
        populateNeighbors(gridOfCells, northEastNeighbor);
        populateNeighbors(gridOfCells, southEastNeighbor);
        populateNeighbors(gridOfCells, southWestNeighbor);
    }

}
