package cellsociety;

import javafx.scene.shape.Polygon;

public abstract class Cell extends Polygon {
    private int previousState;
    private int currentState;
    private Cell[] neighborArray;

    public Cell(){

    }

    /**
     * Update the current state of the cell
     */
    protected  abstract void updateCellValue();

    /**
     * Returns the neighbors of the cell
     */
    protected abstract void findNeighbors();

}
