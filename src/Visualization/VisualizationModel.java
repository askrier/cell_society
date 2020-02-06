package Visualization;

import XML.SimData;
import cellsociety.Grid;
import cellsociety.Main;

import java.lang.reflect.InvocationTargetException;
import javafx.animation.Timeline;

public class VisualizationModel {

  private Grid myGrid;
  private SimData mySimData;
  private Timeline animation;

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
  public Grid start (Timeline animation) {
    animation = animation;
    animation.play();
   // System.out.println("hi");
   // if(hasNext()) {
      //update the grid
      myGrid.updateGrid();
      return myGrid;
  //  }
   // return null;
  }

  public void end(){
    animation.stop();
  }

  public void slow(){ animation.setRate(.1); }

  public void speed(){  animation.setRate(7); }

  public void stepThrough(){
    animation.pause();
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