package cellsociety;

import java.util.ArrayList;
import java.lang.reflect.Method;

public class Grid {
    ArrayList<ArrayList<Cell>> myArrayOfCells = new ArrayList<ArrayList<Cell>>();

    public Grid(int height, int width, int vCellNum, int hCellNum, String gameVariation){


    }

    public void updateGrid(){
        for(ArrayList<Cell> innerList : myArrayOfCells){
            for(Cell cell: innerList){
                cell.findNeighbors();
                cell.updateCellValue();
            }
        }
    }
}