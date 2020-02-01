package cellsociety;


import java.util.ArrayList;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid {
    public ArrayList<ArrayList<Cell>> myArrayOfCells = new ArrayList<ArrayList<Cell>>();
    //these were the booleans I was using to call the grid initial and updated states, but do it how it works for yall
    public boolean exists;
    public boolean update = false;

//    public <T extends Cell> Grid(int height, int width, int vCellNum, int hCellNum, Class<T> gameVariation) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        for (int i = 0; i < vCellNum; i++) {
//            myArrayOfCells.add(new ArrayList<Cell>());
//            for (int j=0; j< hCellNum; j++){
//                Cell obj = getInstance(gameVariation);
//                myArrayOfCells.get(i).add(obj);
//
//            }
//        }
//    }

    public Grid(int height, int width, int vCellNum, int hCellNum, String gameVariation) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        Cell obj = null;
//        if(gameVariation == "GameOfLife"){
//            obj = new GameOfLifeCell(0);
//        }
//        else if(gameVariation == "PercolationCell"){
//            obj = new PercolationCell(0);
//        }
//        else if(gameVariation == "PredatorPreyCell"){
//            obj = new PredatorPreyCell(0);
//        }
//        else if(gameVariation == "SegregationCell"){
//            obj = new SegregationCell(0);
//        }
//        else if(gameVariation == "FireCell"){
//            obj = new FireCell(0);
//        }
//        Class cls = obj.getClass();
        Class cls = Class.forName(gameVariation);


        for (int i = 0; i < vCellNum; i++) {
            myArrayOfCells.add(new ArrayList<Cell>());
            for (int j=0; j< hCellNum; j++){
                Constructor constructor = cls.getConstructor(int.class);
                Object objectCell = constructor.newInstance(0);
                Cell cell = (Cell) objectCell;
                myArrayOfCells.get(i).add(cell);

            }
        }


    }

    public void updateGrid(){
        for(ArrayList<Cell> innerList : myArrayOfCells){
            int arrayIndex = myArrayOfCells.indexOf(innerList);
            for(Cell cell: innerList){
                int cellIndex = innerList.indexOf(cell);
                cell.findNeighbors(myArrayOfCells, arrayIndex, cellIndex);
                cell.updateCellValue();
            }
        }
    }

//    public <T extends Cell> Cell getInstance(Class<T> gameVariation) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Cell cell = gameVariation.getDeclaredConstructor().newInstance();
//        return cell;
//    }

    public ArrayList<ArrayList<Cell>> getListOfCells(){
        return myArrayOfCells;
    }
}