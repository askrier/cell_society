package cellsociety;

import java.util.ArrayList;

public class PredatorPreyCell extends Cell{
    private static final int WATER = 0;
    private static final int FISH = 1;
    private static final int SHARK = 2;

    public PredatorPreyCell(int currentState){
        super(currentState);
        neighborArray = new ArrayList<Cell>();
    }

    @Override
    public void updateCellValue(){

    }

    @Override
    protected void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow) {
        int[] northNeighbor = {cellColumn, cellRow-1};
        int[] southNeighbor = {cellColumn, cellRow+1};
        int[] eastNeighbor = {cellColumn+1, cellRow};
        int[] westNeighbor = {cellColumn-1, cellRow};
        populateNeighbors(gridOfCells, northNeighbor);
        populateNeighbors(gridOfCells, southNeighbor);
        populateNeighbors(gridOfCells, eastNeighbor);
        populateNeighbors(gridOfCells, westNeighbor);
    }

    @Override
    protected void populateNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int[] cellCoordinate){
        int column = cellCoordinate[0];
        int row = cellCoordinate[1]
        if(column < 0){
            column = column+gridOfCells.size()-1;
        }

        if(cellCoordinate[0] >= 0 || cellCoordinate[0] <= gridOfCells.size()-1 ){
            if (cellCoordinate[1] >= 0 || cellCoordinate[1] <= gridOfCells.get(cellCoordinate[0]).size()-1){
                neighborArray.add(gridOfCells.get(cellCoordinate[0]).get(cellCoordinate[1]));
            }
        }
    }

}
