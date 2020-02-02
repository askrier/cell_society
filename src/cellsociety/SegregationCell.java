package cellsociety;

import java.util.ArrayList;

public class SegregationCell extends Cell {
    private static final int EMPTY = 0;
    private static final double SATISFIEDTHRESHOLD = 0.3;

    public SegregationCell(int currentState, int dimension){
        super (currentState, dimension);
        neighborArray = new ArrayList<Cell>();
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
        if (previousState != EMPTY){
            double sameAgentPercent = percentageOfSameNeighbor();
            if (sameAgentPercent < SATISFIEDTHRESHOLD){
                Cell emptyCell = findEmptyCell(gridOfCells);
                emptyCell.currentState = currentState;
                currentState = EMPTY;
            }
            else {currentState = previousState;}
        }
        else {currentState = previousState;}
    }

    private double percentageOfSameNeighbor(){
        double sameAgentCount = 0;
        double totalAgentNeighbors = 0;
        for (Cell neighbor : neighborArray){
            if (neighbor.getPreviousState() != EMPTY){
                totalAgentNeighbors ++;
                if (neighbor.getPreviousState() == previousState){
                    sameAgentCount ++;
                }
            }
        }
        return sameAgentCount / totalAgentNeighbors;
    }

    private Cell findEmptyCell(ArrayList<ArrayList<Cell>> gridOfCells){
        for(ArrayList<Cell> innerList : gridOfCells){
            for(Cell cell: innerList){
                if(cell.getCurrentState() == EMPTY){return cell;}
            }
        }
        return null;
    }

    /**
     * @see Cell#findNeighbors(ArrayList, int, int)
     */
    @Override
    protected void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow){
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
