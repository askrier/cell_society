package cellsociety;

import java.util.ArrayList;

public class FireCell extends Cell {
    private static final int EMPTY = 0;
    private static final int TREE = 1;
    private static final int BURNING = 2;
    private static final double PROBABILITYOFCATCH = 0.15;

    public FireCell(int currentState){
        super (currentState);
        neighborArray = new ArrayList<Cell>();
    }

    /**
     * @see Cell#updateCellValue()
     */
    @Override
    public void updateCellValue(){
        double chanceOfCatch = Math.random();
        if (previousState == TREE){
            for (Cell neighbor : neighborArray){
                if (neighbor.getPreviousState() == BURNING && chanceOfCatch < PROBABILITYOFCATCH){
                    currentState = BURNING;
                    break;
                }
                currentState = TREE;
            }
        }
        else if (previousState == BURNING) {currentState = EMPTY;}
        else {currentState = previousState;}
    }

    /**
     * @see Cell#findNeighbors(ArrayList, int, int)
     */
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