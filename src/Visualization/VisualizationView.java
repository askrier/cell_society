package Visualization;

import cellsociety.Grid;
import java.io.File;
import java.net.URL;
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
  private Button browseFolder;
  private Grid myGrid;
  private Scene myPage;
  // create model data
  private VisualizationModel myModel;

  public VisualizationView(VisualizationModel model){
    myModel = model;
    myGrid = myModel.getGrid();
  }

  /**
   * Returns scene for the browser so it can be added to stage.
   */
  public Scene makeScene (int width, int height) {
    BorderPane root = new BorderPane();
    // must be first since other panels may refer to page
    root.setCenter(myGrid.r);
    root.setTop(makeInputPanel());
    //root.setBottom(makeInformationPanel());
    // create scene to hold UI
    Scene scene = new Scene(root, width, height);
    // activate CSS styling
    scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  // Display given message as an error in the GUI
  private void showError (String message) {
    Alert alert = new Alert(AlertType.ERROR);
  //  alert.setTitle(myResources.getString("ErrorTitle"));
    alert.setContentText(message);
    alert.showAndWait();
  }

  // move to the next URL in the history
  private void start () {
    update(myModel.next());
  }

  // update just the view to display next state
  private void update (Grid grid) {
    showPage(grid);
  }


  // make user-entered URL/text field and back/next buttons
  private Node makeInputPanel () {
    HBox result = new HBox();
    startSimulation = makeButton("Start", event -> start());
    result.getChildren().add(startSimulation);
    // new style way to do set up callback (lambdas)
    stopSimulation = makeButton("Stop", null);
    result.getChildren().add(stopSimulation);
    FileChooser fileChooser = new FileChooser();
    browseFolder = makeButton("Browse", new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null){
          //pass to andrew
          System.out.println(file);
        }
      }
    });
    result.getChildren().add(browseFolder);
    return result;
  }

  /**
   * Display given Grid
   */
  public Node showPage (Grid grid) {
    System.out.println(myGrid);
    GridPane gridPane = new GridPane();
    if (grid != null) {
      update(grid);
      gridPane.getChildren().add(grid.r);
    }
    else {
      showError("Could not load grid");
    }
    return gridPane;
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
  private TextField makeInputField (int width, EventHandler<ActionEvent> handler) {
    TextField result = new TextField();
    result.setPrefColumnCount(width);
    result.setOnAction(handler);
    return result;
  }
}



