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

  public Grid getGrid(){
    myGrid = new Grid(mySimData.getRows(), mySimData.getColumns(), mySimData.getSimType(), mySimData.getValList(),mySimData.getSpreadProb());
    return myGrid;

  }

  /**
   * Returns the state of grid, if there is a grid
   */
  public Grid start (Timeline Animation) {
    animation = Animation;
    animation.play();
      myGrid.updateGrid();
      return myGrid;
  }

  public void end(){
    animation.stop();
  }

  public void slow(){ animation.setRate(.1); }

  public void speed(){  animation.setRate(7); }

  public void stepThrough(){
    animation.pause();
    myGrid.updateGrid();
  }

}