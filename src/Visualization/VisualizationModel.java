package Visualization;

import static java.time.Duration.ofSeconds;

import XML.SimData;
import cellsociety.Grid;
import cellsociety.Main;

import java.lang.reflect.InvocationTargetException;

public class VisualizationModel {

  public Grid myGrid;
  private SimData mySimData;
  private String myFileURL;
  private VisualizationView view;

  public VisualizationModel() {
    myFileURL = null;
    myGrid = null;
  }

  public void getFile (String file){
    myFileURL = file;
  }

  public void setSimData (SimData sim) {
    mySimData = sim;
  }

  public Grid getGrid() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    myGrid = new Grid(mySimData.getRows(), mySimData.getColumns(), 2,mySimData.getSimType(), mySimData.getValList());
    return myGrid;

  }

  /**
   * Returns the state of grid, if there is a grid
   */
  public Grid start () {
    if (has()) {
      return myGrid;
    }
    return null;
  }

  /**
   * Returns the next state of grid, if there is an update
   */
  public Grid next(){
    Main.animation.play();
    if(hasNext()) {
      //update the grid
      myGrid.updateGrid();
      return myGrid;
    }
    return null;
  }

  public void end(){
    Main.animation.stop();
  }

  public void slow(){ Main.animation.setRate(.1); }

  public void speed(){ Main.animation.setRate(7); }

  public void stepThrough(){
    Main.animation.pause();
    myGrid.update = true;
    myGrid.updateGrid();
  }

  /**
   * Returns true if there is a next Grid available
   */
  public boolean has () { return myGrid.exists; }

  public boolean hasNext(){
    return myGrid.update;
  }
}