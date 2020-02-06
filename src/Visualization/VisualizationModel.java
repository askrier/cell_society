package Visualization;

import XML.SimData;
import cellsociety.Grid;
import cellsociety.Main;

import java.lang.reflect.InvocationTargetException;

public class VisualizationModel {

  private Grid myGrid;
  private SimData mySimData;
  private Main main;

  public VisualizationModel() {
    myGrid = null;
  }

  public void setSimData (SimData sim) {
    mySimData = sim;
  }

  public Grid getGrid() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    myGrid = new Grid(mySimData.getRows(), mySimData.getColumns(), 2,mySimData.getSimType(), mySimData.getValList(),mySimData.getSpreadProb());
    return myGrid;

  }

  /**
   * Returns the state of grid, if there is a grid
   */
  public Grid start () {
    //if (has()) {
      return myGrid;
  //  }
  // return null;
  }

  /**
   * Returns the next state of grid, if there is an update
   */
  public Grid next(){

    main.getAnimation().play();
    System.out.println("hi");
   // if(hasNext()) {
      //update the grid
      myGrid.updateGrid();
      return myGrid;
  //  }
   // return null;
  }

  public void end(){
    main.getAnimation().stop();
  }

  public void slow(){  main.getAnimation().setRate(.1); }

  public void speed(){  main.getAnimation().setRate(7); }

  public void stepThrough(){
    main.getAnimation().pause();
   // myGrid.update = true;
    myGrid.updateGrid();
  }

  /**
   * Returns true if there is a next Grid available
   */
 // public boolean has () { return myGrid.exists; }

 // public boolean hasNext(){
   // return myGrid.update;
 // }
}