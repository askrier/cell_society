package Visualization;

import XML.SimData;
import XML.XMLParser;
import cellsociety.Cell;
import cellsociety.Grid;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JWindow;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class VisualizationView {

  private static final String RESOURCES = "resources";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  public static final String STYLESHEET = "default.css";

  private ResourceBundle myResources;
  //scene to send to application
  private Scene myScene;
  // all the buttons for the view
  private Button startButton;
  private Button startSimulation;
  private Button stopSimulation;
  private Button speedSimulation;
  private Button slowSimulation;
  private Button stepSimulation;
  public Grid myGrid;
  public SimData simData;
  private Scene myPage;
  // create model data
  private VisualizationModel myModel;

  public VisualizationView(VisualizationModel model) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    myModel = model;
    //myGrid = null;
    myGrid = myModel.getGrid();
  }

  /**
   * Returns scene for the browser so it can be added to stage.
   */
  public Scene makeScene (int width, int height) {
    BorderPane root = new BorderPane();
    // must be first since other panels may refer to page


    //Add the grid bricks to the root, this is the intial view/ intital settings grid

    for(ArrayList<Cell> list: myGrid.getListOfCells()) {
      for (Cell cell : list) {
       root.setCenter(cell);
      }
    }


    root.setTop(makeInputPanel());
    root.setBottom(makeInputField());
    // create scene to hold UI
    Scene scene = new Scene(root, width, height);
    // activate CSS styling
    scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  // Display given message as an error in the GUI
  private void showError (String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(message);
    alert.showAndWait();
  }

  // move to the next URL in the history
  private void start () {
    myModel.next();
  }
  private void stop(){
    myModel.end();
  }
  private void slow(){
    myModel.slow();
  }
  private void speed(){
    myModel.speed();
  }
  private void stepThrough() {
    myModel.stepThrough();
  }



  // update just the view to display next state
  private Node update (Grid grid) {
    GridPane gridPane = new GridPane();

    for(ArrayList<Cell> list: myGrid.getListOfCells()) {
      for (Cell cell : list) {
        gridPane.getChildren().add(cell);
      }
    }
      return gridPane;
  }


  private Node makeInputPanel () {
    HBox result = new HBox();

    startSimulation = makeButton("Start", event -> start());
    result.getChildren().add(startSimulation);


    stopSimulation = makeButton("Stop", event -> stop());
    result.getChildren().add(stopSimulation);


    slowSimulation = makeButton("Slow",event -> slow());
    result.getChildren().add(slowSimulation);

    speedSimulation = makeButton("Speed", event-> speed());
    result.getChildren().add(speedSimulation);

    stepSimulation = makeButton("Step", event -> stepThrough());
    result.getChildren().add(stepSimulation);

    return result;
  }

  /**
   * Display given Grid, if not there it throws an error
   */
  public void showPage (Grid grid) {

    if (grid != null) {
      update(grid);
    }
    else {
      showError("Could not load grid");
    }

  }

  private class ShowPage implements EventHandler<ActionEvent> {
    @Override
    public void handle (ActionEvent event) {
      showPage(myGrid);
    }
  }

  // makes a button using either an image or a label
  private Button makeButton (String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    String label = property;
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }

  // make text field for input
  private Text makeInputField () {
    Text result = new Text();

    // Down here is where the Simulation Name can be put in

    result.setText("Simulation name");

    return result;
  }

  public int getRows() { return simData.getRows(); }

  public int getCols() { return simData.getColumns(); }
}