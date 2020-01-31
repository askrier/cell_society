package Visualization;

import cellsociety.Cell;
import cellsociety.Grid;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class VisualizationModel {

  private Grid myGrid;
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
    if(hasNext()) {
      myGrid.update();
      return myGrid;
    }
    return null;
  }

  /**
   * Returns true if there is a next Grid available
   */
  public boolean has () {
    return myGrid.exists;
  }

  public boolean hasNext(){
    return myGrid.update = true;
  }
}
