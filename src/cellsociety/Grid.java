package cellsociety;


import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.List;

public class Grid {
    private ArrayList<ArrayList<Cell>> myArrayOfCells = new ArrayList<ArrayList<Cell>>();
    private boolean GameOn = true;

    public Grid(int vCellNum, int hCellNum, String gameVariation, List<List<Integer>> cellVals, double spreadProb) {
        try {
            myArrayOfCells = createList(vCellNum, hCellNum, gameVariation, cellVals, spreadProb);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            //rebrowse for another file
        }

    }

    public void updateGrid(){
        GameOn = myArrayOfCells.get(0).get(0).checkGameOn(myArrayOfCells);
        if(GameOn){
          //  update = true;
            for(ArrayList<Cell> innerList : myArrayOfCells){
                for(Cell cell: innerList){
                    cell.resetState();
                }
            }
            for(ArrayList<Cell> innerList : myArrayOfCells){
                int arrayIndex = myArrayOfCells.indexOf(innerList);
                for(Cell cell: innerList){
                    int cellIndex = innerList.indexOf(cell);
                    cell.updateCellValue(myArrayOfCells, arrayIndex, cellIndex);
                }
            }
            updateColors();
        }
    }

    public void updateColors(){
        for(ArrayList<Cell> innerList : myArrayOfCells){
            for(Cell cell: innerList){
                cell.updateCellColor();
            }
        }
    }


    public ArrayList<ArrayList<Cell>> getListOfCells(){
        return myArrayOfCells;
    }

    private ArrayList<ArrayList<Cell>> createList(int vCellNum, int hCellNum, String gameVariation, List<List<Integer>> cellVals, double spreadProb) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<ArrayList<Cell>> arrayOfCells = new ArrayList<ArrayList<Cell>>();
        int intVal;
        int dimension;
        int width = (800/hCellNum);
        int height = (800/vCellNum);
        if(width<height){
            dimension=width;
        }
        else{
            dimension=height;
        }

        Class<?> cls = Class.forName(gameVariation);

        for (int i = 0; i < vCellNum; i++) {
            arrayOfCells.add(new ArrayList<Cell>());
            for (int j=0; j< hCellNum; j++){
                intVal = cellVals.get(j).get(i);
                Object objectCell;
                if (gameVariation.equals("cellsociety.FireCell")){
                    Constructor<?> constructor = cls.getConstructor(int.class, int.class, double.class);
                    objectCell = constructor.newInstance(intVal,dimension, spreadProb);
                }
                else{
                    Constructor constructor = cls.getConstructor(int.class,int.class);
                    objectCell = constructor.newInstance(intVal,dimension);
                }
                Cell cell = (Cell) objectCell;
                arrayOfCells.get(i).add(cell);
            }
        }
        return arrayOfCells;
    }

}