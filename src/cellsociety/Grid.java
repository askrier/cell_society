package cellsociety;

import java.util.ArrayList;
import java.lang.reflect.*;

public class Grid {
    ArrayList<ArrayList<Cell>> myArrayOfCells = new ArrayList<ArrayList<Cell>>();

    public Grid(int height, int width, int vCellNum, int hCellNum, String gameVariation) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String clsName = "cellsociety.PercolationCell";
        Class<?> myCellClass = Class.forName(gameVariation);
        for(int i=0;i<vCellNum;i++){
            myArrayOfCells.add(new ArrayList<?>());
        }

        Object cell = (Object) myCellClass.getDeclaredConstructor().newInstance();
    }

    public void updateGrid()  {
        for(ArrayList<Cell> innerList : myArrayOfCells){
            for(Cell cell: innerList){
                cell.findNeighbors();
                cell.updateCellValue();
            }
        }
    }
}
