package Visualization;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class VisualizationView {

  private static final String RESOURCES = "resources";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  public static final String STYLE_SHEET = "";

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
  // create model data
  private VisualizationModel myModel;

  public VisualizationView(VisualizationModel model){
    myModel = model;
  }

  /**
   * Returns scene for the browser so it can be added to stage.
   */
  public Scene makeScene (int width, int height) {
    BorderPane root = new BorderPane();
    // must be first since other panels may refer to page
    root.setCenter(makePageDisplay());
    root.setTop(makeInputPanel());
    root.setBottom(makeInformationPanel());
    // control the navigation
    enableButtons();
    // create scene to hold UI
    Scene scene = new Scene(root, width, height);
    // activate CSS styling
    //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  // Display given message as an error in the GUI
  private void showError (String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(myResources.getString("ErrorTitle"));
    alert.setContentText(message);
    alert.showAndWait();
  }

  // move to the next URL in the history
  private void next () {
    update(myModel.next());
  }

  // move to the previous URL in the history
  private void back () {
    update(myModel.back());
  }

  // update just the view to display next state
  private void update (String url) {

  }

  // make user-entered URL/text field and back/next buttons
  private Node makeInputPanel () {
    HBox result = new HBox();
    // create buttons, with their associated actions
    // old style way to do set up callback (anonymous class)
    myBackButton = makeButton("BackCommand", new EventHandler<>() {
      @Override
      public void handle (ActionEvent event) {
        back();
      }
    });
    result.getChildren().add(myBackButton);
    // new style way to do set up callback (lambdas)
    myNextButton = makeButton("NextCommand", event -> next());
    result.getChildren().add(myNextButton);
    // if user presses button or enter in text field, load/show the URL
   // ShowPage showHandler = new ShowPage();
   // result.getChildren().add(makeButton("GoCommand", showHandler));
    return result;
  }

  // makes a button using either an image or a label
  private Button makeButton (String property, EventHandler<ActionEvent> handler) {
    // represent all supported image suffixes
    final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
    Button result = new Button();
    String label = myResources.getString(property);
    if (label.matches(IMAGEFILE_SUFFIXES)) {
      result.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label))));
    }
    else {
      result.setText(label);
    }
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




}
