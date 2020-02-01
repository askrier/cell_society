package Visualization;

import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Main;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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

  public Grid getGrid(){
      myGrid = new Grid(5,5,5,5,"hello");
      return myGrid;
  }

  /**
   * Returns the state of grid
   */
  public Grid start () {
    if (has()) {
      return myGrid;
    }
    return null;
  }

  /**
   * Returns the next state of grid
   */
  public Grid next(){
    Main.animation.play();
    if(hasNext()) {
      myGrid.update();
      return myGrid;
    }
    return null;
  }

  public void end(){
     Main.animation.stop();
  }
  public boolean slow(){
    Main.animation.setRate(.5);
    return true;
  }
  public boolean speed(){
    Main.animation.setRate(10);
    return true;
  }
  public void stepThrough(){
//    Main.animation.play();
  //  Main.animation.stop();
    Main.animation.pause();
   // Main.animation.setDelay(Duration.ONE);
  //  Main.animation.play();




  }

  /**
   * Returns true if there is a next Grid available
   */
  public boolean has () {
    return myGrid.exists;
  }

  public boolean hasNext(){
    return myGrid.update;
  }
}
