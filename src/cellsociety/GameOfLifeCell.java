package cellsociety;

import java.util.ArrayList;

public class GameOfLifeCell extends Cell{
    protected ArrayList<Cell> neighborArray;

    public GameOfLifeCell(int currentState){
        super(currentState);

    }

    @Override
    public void updateCellValue(){

    }

    @Override
    protected void findNeighbors(ArrayList<ArrayList<Cell>> gridOfCells, int cellColumn, int cellRow) {

    }
}
