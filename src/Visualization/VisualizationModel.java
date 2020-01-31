package Visualization;

import cellsociety.Cell;
import cellsociety.Grid;
import java.lang.reflect.Array;
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

}
