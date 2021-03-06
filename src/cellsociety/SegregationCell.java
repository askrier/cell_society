package cellsociety;


import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SegregationCell extends Cell {
    private static final int EMPTY = 0;
    private static final int AGENTX = 1;
    private static final int AGENTO = 2;
    private static final double SATISFIEDTHRESHOLD = 0.6;

    public SegregationCell(int currentState, int dimension){
        super (currentState, dimension);
        neighborArray = new ArrayList<Cell>();
        myColorArray = new Color[]{Color.WHITE, Color.RED, Color.MAGENTA};
        hasBeenUpdated = false;
    }

    @Override
    protected void resetState(){
        previousState = currentState;
        hasBeenUpdated = false;
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
            if (sameAgentPercent < SATISFIEDTHRESHOLD && findandUpdateEmptyCell(gridOfCells)){
                previousState = EMPTY;
                currentState = previousState;
            }
            else {currentState = previousState; }
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
        if (totalAgentNeighbors == 0){return 0;}
        return (sameAgentCount / totalAgentNeighbors);
    }

    private boolean findandUpdateEmptyCell(ArrayList<ArrayList<Cell>> gridOfCells){
        for(ArrayList<Cell> innerList : gridOfCells){
            for(Cell cell: innerList){
                if(!cell.hasBeenUpdated && cell.getPreviousState() == EMPTY ){
                    cell.currentState = previousState;
                    cell.hasBeenUpdated = true;
                    return true;
                }
            }
        }
        return false;
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
