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

  public void getGrid(Grid grid){
      myGrid = grid;
  }


  /**
   * Returns the next state of grid
   */
  public Grid start () {
    if (hasNext()) {
      return myGrid;
    }
    return null;
  }

  /**
   * Returns true if there is a next Grid available
   */
  public boolean hasNext () {
    return myGrid.exists;
  }

}
