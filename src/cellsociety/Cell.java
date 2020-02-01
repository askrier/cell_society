package cellsociety;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Cell extends Polygon {
    protected int previousState;
    protected int currentState;
    protected ArrayList<Cell> neighborArray;

    public Cell(int cellState){
        currentState = cellState;
        previousState = currentState;
    }

    public int getCurrentState(){
        return currentState;
    }

    public int getPreviousState(){
        return previousState;
    }

    /**
     * Update the current state of the cell
     */
    protected  abstract void updateCellValue();

    /**
     * Returns the neighbors of the cell
     */
    protected abstract void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow);

    protected void populateNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int[] cellCoordinate){
        if(gridOfCells.get(cellCoordinate[0]).get(cellCoordinate[1]) != null){
            neighborArray.add(gridOfCells.get(cellCoordinate[0]).get(cellCoordinate[1]));
        }
    }

}
