package cellsociety;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PercolationCell extends Cell {
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int PERCOLATED = 2;


    public PercolationCell(int currentState, int dimension){
        super (currentState, dimension);
        neighborArray = new ArrayList<Cell>();
        myColorArray = new Color[]{Color.BLACK, Color.WHITE, Color.BLUE};
    }

    @Override
    public boolean checkGameOn(ArrayList<ArrayList<Cell>> gridOfCells){
        for(Cell cell: gridOfCells.get(0)){
            if(cell.getCurrentState()==PERCOLATED){
                for(Cell cell2: gridOfCells.get(gridOfCells.size()-1)){
                    if(cell2.currentState==PERCOLATED){
                        return false;
                    }
                }
            }
        }
        return true;
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
