package cellsociety;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FireCell extends Cell {
    private static final int EMPTY = 0;
    private static final int TREE = 1;
    private static final int BURNING = 2;

    private double probabilityOfCatch;

    public FireCell(int currentState, int dimension, double probOfCatch){
        super (currentState, dimension);
        probabilityOfCatch = probOfCatch;
        neighborArray = new ArrayList<Cell>();
        myColorArray = new Color[]{Color.YELLOW, Color.GREEN, Color.RED};
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
        double chanceOfCatch = Math.random();
        if (previousState == TREE){
            for (Cell neighbor : neighborArray){
                if (neighbor.getPreviousState() == BURNING && chanceOfCatch < probabilityOfCatch){
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
    protected void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        int[] northNeighbor = {cellColumn, cellRow-1};
        int[] southNeighbor = {cellColumn, cellRow+1};
        int[] eastNeighbor = {cellColumn+1, cellRow};
        int[] westNeighbor = {cellColumn-1, cellRow};
        populateNeighbors(gridOfCells, northNeighbor);
        populateNeighbors(gridOfCells, southNeighbor);
        populateNeighbors(gridOfCells, eastNeighbor);
        populateNeighbors(gridOfCells, westNeighbor);
    }
}