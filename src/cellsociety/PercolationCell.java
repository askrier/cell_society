package cellsociety;

import java.util.ArrayList;

public class PercolationCell extends Cell {
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int PERCOLATED = 2;


    public PercolationCell(int currentState){
        super(currentState);
        neighborArray = new ArrayList<Cell>();
    }

    @Override
    public void updateCellValue(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
        neighborArray.clear();
        findNeighbors(gridOfCells, cellColumn, cellRow);
        if(previousState==OPEN){
            for (Cell neighbor : neighborArray) {
                if (neighbor.getPreviousState() == PERCOLATED) {
                    currentState = PERCOLATED;
                    return;
                }
            }
        }
        currentState = previousState;
    }

    @Override
    protected void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow) {
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
