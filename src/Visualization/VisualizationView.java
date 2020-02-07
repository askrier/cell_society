package Visualization;

import XML.SimData;
import XML.XMLParser;
import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
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
  private static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  private static final String STYLESHEET = "default.css";
  private Button startSimulation;
  private Button stopSimulation;
  private Button speedSimulation;
  private Button slowSimulation;
  private Button stepSimulation;
  private Button chooseAnother;
  private Grid myGrid;
  private Grid myGridTwo;
  private SimData simData;
  private VisualizationModel myModel;
  private GridPane gridPane;
  private GridPane gridP;
  private GridPane gridPTwo;
  private GridPane gridPaneTwo;
  private Button browseFolder;
  private BorderPane root;
  private Timeline animation;
  private double speed;
  private Slider slider;
  private Label sliderCaption;
  private boolean browseAgain;

  public VisualizationView(VisualizationModel model) {
    myModel = model;
    myGrid = myModel.getGrid();
    myGridTwo = myModel.getGrid();
  }

  /**
   * Returns scene for the browser so it can be added to stage.
   */
  public Scene makeScene(int width, int height) {
    root = new BorderPane();
    gridPane = new GridPane();
    myGrid.updateColors();
    createGrid(myGrid, gridPane);
    myGridTwo.updateColors();
    gridPaneTwo = new GridPane();
    createGrid(myGridTwo,gridPaneTwo);

    root.setBottom(makeInputPanel());
    root.setTop(makeInputField());
    root.setCenter(gridPane);

    Scene scene = new Scene(root, width, height);
    scene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  private void createGrid(Grid grid,GridPane gridPane) {
   gridPane.setAlignment(Pos.CENTER);
    int i = 0;
    int j;
    for (ArrayList<Cell> list : grid.getListOfCells()) {
      i++;
      j = 0;
      for (Cell cell : list) {
        j++;
        gridPane.add(cell, i, j);
      }
    }
    if(browseAgain){
      root.setRight(gridPane);
    }
  }

  // Display given message as an error in the GUI
  public void showError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void start() {
    slider.setDisable(false);
    myModel.start(animation);
  }

  private void stop() {
    myModel.end();
    browseAgain = false;
  }

  private void slow() {
    myModel.slow();
  }

  private void speed() {
    myModel.speed(speed);
  }

  private void stepThrough() {
    myModel.stepThrough();
  }


  private void chooseAnother() {
    browseAgain = true;
  }

  // update just the view to display next state
  private Node update(Grid grid, GridPane pane) {
    grid.updateGrid();
    createGrid(grid,pane);
    return pane;
  }


  private Node makeInputPanel(){
    HBox result = new HBox();
    result.setSpacing(5);
    startSimulation = makeButton("Start", event -> start());
    result.getChildren().add(startSimulation);

    stopSimulation = makeButton("Stop", event -> stop());
    result.getChildren().add(stopSimulation);

    slowSimulation = makeButton("Slow", event -> slow());
   // result.getChildren().add(slowSimulation);

    speedSimulation = makeButton("Speed", event -> speed());
  //  result.getChildren().add(speedSimulation);

    stepSimulation = makeButton("Step", event -> stepThrough());
    result.getChildren().add(stepSimulation);

    changeSim();
    result.getChildren().add(browseFolder);

    chooseAnother = makeButton("Turn on Dual Screen", event -> chooseAnother());
    result.getChildren().add(chooseAnother);
    chooseAnother.setId("choose-another");

    slider = createSlider("Change Speed");
    slider.setDisable(true);
    result.getChildren().add(slider);
    result.getChildren().add(sliderCaption);

    return result;
  }


  private Slider createSlider(String property){
    Slider slider = new Slider();
    slider.setMin(0);
    slider.setMax(6);
    sliderCaption = new Label(property);
    slider.setBlockIncrement(1);
    slider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        speed = newValue.doubleValue();
        speed();
      }
    });
    return slider;
  }

  private void changeSim() {
    FileChooser fileChooser = new FileChooser();
    browseFolder = makeButton("Browse", new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          XMLParser parser = new XMLParser("game");
          simData = parser.getSimData(file);
          myModel.setSimData(simData);
          if(browseAgain) {
            myGridTwo = myModel.getGrid();
            gridPTwo = new GridPane();
            update(myGridTwo, gridPTwo);
          }
         else{ myGrid = myModel.getGrid();
          myGrid.updateColors();
          update(myGrid,gridP);
        }}
      }
    });
  }

  private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    String label = property;
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }

  private Text makeInputField() {
    Text result = new Text();
    result.setFont(Font.font("Apple Chancery", 20));
    result.setText(simData.getSimType() + ": by " + simData.getAuthor());
    return result;
  }

  public void setSimData(SimData sim) {
    simData = sim;
  }

  public Grid getSetGrid() {
    return myGrid;
  }

  public Grid getMyGridTwo(){
    return myGridTwo;
  }

  public void setAnimation(Timeline Animation) {
    animation = Animation;
  }
}