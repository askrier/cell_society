package cellsociety;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Cell extends Rectangle {
    protected int previousState;
    protected int currentState;
    protected int reproductiveTimer;
    protected boolean hasBeenUpdated;
    protected double energy;
    protected ArrayList<Cell> neighborArray;
    protected Color[] myColorArray;

    public Cell(int cellState, int dimension){
        super(dimension-20,dimension-20);
        currentState = cellState;
        previousState = currentState;
        neighborArray = new ArrayList<Cell>();
    }

    /**
     *
     * @return the current state of the cell
     */
    public int getCurrentState(){
        return currentState;
    }

    /**
     *
     * @return the previous state of the cell
     */
    protected int getPreviousState(){
        return previousState;
    }

    /**
     * Sets the previous state of the cell as the current state
     */
    protected void resetState(){
        previousState = currentState;
    }

    /**
     * Update the current state of the cell
     * @param gridOfCells
     * @param cellColumn
     * @param cellRow
     */
    protected  abstract void updateCellValue(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow);

    /**
     * Returns the neighbors of the cell
     */
    protected abstract void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow);

    /**
     * Adds neighbor cells to the neighbor List
     * @param gridOfCells The grid containing all the cells in the simulation
     * @param cellCoordinate the column and row information of the cell
     */
    protected void populateNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int[] cellCoordinate){
        int column = cellCoordinate[0];
        int row = cellCoordinate[1];
        if(column >= 0 && column <= gridOfCells.size()-1 ){
            if (row >= 0 && row <= gridOfCells.get(column).size()-1){
                ArrayList<Cell> Col = gridOfCells.get(column);
                Cell cell = Col.get(row);
                neighborArray.add(cell);
            }
        }
    }

    public void updateCellColor(){
        setFill(myColorArray[currentState]);
    }

    public boolean checkGameOn(ArrayList<ArrayList<Cell>> gridOfCells){
        return true;
    }

}
