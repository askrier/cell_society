package Visualization;

import static java.time.Duration.ofSeconds;

import cellsociety.Cell;
import cellsociety.FireCell;
import cellsociety.Grid;
import cellsociety.Main;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class VisualizationModel {

  public Grid myGrid;
  private String myFileURL;

  public VisualizationModel() {
    myFileURL = null;
    myGrid = null;
  }

  public void getFile (String file){
    myFileURL = file;
  }

  public Grid getGrid() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    //this is going to be created by andrew's data and passed to create the grid

    myGrid = new Grid(5,5,5,5, FireCell.class);
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

  public void slow(){ Main.animation.setRate(.5); }

  public void speed(){ Main.animation.setRate(10); }

  public void stepThrough(){
    Main.animation.pause();

    // I had a boolean that checks if the grid needs to be updated, and in order to update it with step it needed to be set to true
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