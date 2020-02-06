package Visualization;

import XML.SimData;
import XML.XMLParser;
import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Main;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
import javafx.scene.layout.TilePane;
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
  private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
  private static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  private static final String STYLESHEET = "default.css";

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
  private Grid myGrid;
  private  SimData simData;
  private Scene myPage;
  // create model data
  private VisualizationModel myModel;
  private GridPane gridPane;
  private GridPane gridP;
  private Button browseFolder;
  private boolean stopped;
  private BorderPane root;
  private Timeline animation;

  public VisualizationView(VisualizationModel model) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    myModel = model;
    myGrid = myModel.getGrid();
  }

  /**
   * Returns scene for the browser so it can be added to stage.
   */
  public Scene makeScene (int width, int height) {
    root = new BorderPane();
    gridPane = new GridPane();
    myGrid.updateColors();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setAlignment(Pos.CENTER);
    //Add the grid bricks to the root, this is the intial view/ intital settings grid
    int i=0;
    int j=0;
    for(ArrayList<Cell> list: myGrid.getListOfCells()) {
      i++;
      j=0;
      for (Cell cell : list) {
        j++;
        gridPane.add(cell,i,j);
      }
    }
    root.setCenter(gridPane);
    root.setTop(makeInputPanel());
    root.setBottom(makeInputField());
    // create scene to hold UI
    Scene scene = new Scene(root, width, height);
    // activate CSS styling
    scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  // Display given message as an error in the GUI
  public void showError (String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(message);
    alert.showAndWait();
  }

  // move to the next URL in the history
  private void start () {
    myModel.start(animation);
  }
  private void stop(){
    myModel.end();
    stopped = true;
    browseFolder.setDisable(!stopped);
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
    myGrid.updateGrid();
    gridP=new GridPane();
    gridP.setHgap(10);
    gridP.setVgap(10);
    gridP.setAlignment(Pos.CENTER);
    //Add the grid bricks to the root, this is the intial view/ intital settings grid
    int i=0;
    int j=0;
    for(ArrayList<Cell> list: myGrid.getListOfCells()) {
      i++;
      j=0;
      for (Cell cell : list) {
        j++;
        gridP.add(cell,i,j);
      }
    }
    root.setCenter(gridP);
      return gridP;
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

    changeSim();
    result.getChildren().add(browseFolder);

    return result;
  }

  private void changeSim() {
    FileChooser fileChooser = new FileChooser();
    browseFolder = makeButton("Browse", new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null){

          XMLParser parser = new XMLParser("game");
          simData = parser.getSimData(file);
          myModel.setSimData(simData);
          try {
            myGrid = myModel.getGrid();
            myGrid.updateColors();
            update(myGrid);

          } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
          }

        }
      }
    });
    browseFolder.setDisable(!stopped);
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
    result.setText(simData.getSimType() + ": by " + simData.getAuthor());
    return result;
  }

  public void setSimData (SimData sim) {
    simData = sim;
  }

  public Grid getSetGrid () {return myGrid;}

  public void setAnimation(Timeline Animation) {
    animation = Animation;
  }
}