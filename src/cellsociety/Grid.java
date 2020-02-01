package cellsociety;


import java.util.ArrayList;
import java.lang.reflect.Method;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid {
    ArrayList<ArrayList<Cell>> myArrayOfCells = new ArrayList<ArrayList<Cell>>();

    //these were the booleans I was using to call the grid intial and updated states, but do it how it works for yall
    public boolean exists;
    public boolean update = false;


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