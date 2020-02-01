package cellsociety;

import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Cell extends Polygon {
    protected int previousState;
    protected int currentState;
    protected ArrayList<Cell> neighborArray;

    public Cell(int cellState){
        currentState = cellState;
        previousState = currentState;
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
     */
    protected  abstract void updateCellValue();

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
        if(gridOfCells.get(cellCoordinate[0]).get(cellCoordinate[1]) != null){
            neighborArray.add(gridOfCells.get(cellCoordinate[0]).get(cellCoordinate[1]));
        }
    }

}
