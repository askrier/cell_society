package cellsociety;

import java.util.ArrayList;

public class SegregationCell extends Cell {

    public SegregationCell(int cellState){
        super(cellState);
        neighborArray = new ArrayList<Cell>();
    }

    /**
     * @see Cell#updateCellValue()
     */
    @Override
    public void updateCellValue(){

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
