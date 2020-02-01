package cellsociety;

import java.util.ArrayList;

public class GameOfLifeCell extends Cell{
    private static final int DEAD = 0;
    private static final int ALIVE = 1;
    protected ArrayList<Cell> neighborArray;

    public GameOfLifeCell(int currentState){
        super(currentState);
        neighborArray = new ArrayList<Cell>();

    }

    /**
     * @see Cell#updateCellValue()
     */
    @Override
    public void updateCellValue(){
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
        else if(numAliveNeighbors<2 || numAliveNeighbors>3){
                currentState = DEAD;
                return;
            }
        currentState = previousState;
    }


    @Override
    public void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        int[] northNeighbor = {cellColumn, cellRow-1};
        int[] southNeighbor = {cellColumn, cellRow+1};
        int[] eastNeighbor = {cellColumn+1, cellRow};
        int[] westNeighbor = {cellColumn-1, cellRow-1};
        populateNeighbors(gridOfCells, northNeighbor);
        populateNeighbors(gridOfCells, southNeighbor);
        populateNeighbors(gridOfCells, eastNeighbor);
        populateNeighbors(gridOfCells, westNeighbor);
    }
    
}
