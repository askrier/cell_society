package cellsociety;


import java.util.ArrayList;
import java.lang.reflect.*;

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

    public Grid(int height, int width, int vCellNum, int hCellNum, int states, String gameVariation) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        int intVal = 0;
        exists = true;
        Class<?> cls = Class.forName(gameVariation);


        for (int i = 0; i < vCellNum; i++) {
            myArrayOfCells.add(new ArrayList<Cell>());
            for (int j=0; j< hCellNum; j++){
                intVal = getRandom(states);
                Constructor constructor = cls.getConstructor(int.class);
                Object objectCell = constructor.newInstance(intVal);
                Cell cell = (Cell) objectCell;
                myArrayOfCells.get(i).add(cell);
            }
        }


    }

    public void updateGrid(){
        update = true;
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
    }

//    public <T extends Cell> Cell getInstance(Class<T> gameVariation) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Cell cell = gameVariation.getDeclaredConstructor().newInstance();
//        return cell;
//    }

    public ArrayList<ArrayList<Cell>> getListOfCells(){
        return myArrayOfCells;
    }

    private int getRandom(int num){
        double rAsFloat = 1 * (num + Math.random( ) );
        return (int)rAsFloat;
    }
}