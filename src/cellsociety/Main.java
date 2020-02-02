package cellsociety;

import Visualization.VisualizationModel;
import Visualization.VisualizationView;
import XML.SimData;
import XML.XMLParser;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    public static int FRAMES_PER_SECOND = 60;
    public static int MILLISECOND_DELAY = 800 / FRAMES_PER_SECOND;
    public static double SECOND_DELAY = 5.0 / FRAMES_PER_SECOND;
    private static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
    public static final String STYLESHEET = "default.css";
    public static final double WIDTH = 800;
    public static final double HEIGHT = 800;
    public static final Paint BACKGROUND = Color.GRAY;
    public static Timeline animation;
    public static KeyFrame frame;
    public Group root;
    public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
    private Desktop desktop = Desktop.getDesktop();
    private VisualizationModel model;
    private VisualizationView display;
    public SimData simData;
    private Button browseFolder;

    @Override
    public void start(Stage stage) throws Exception {
        // create program specific components
        BorderPane layout = new BorderPane();
        Button startButton = start();
        Text gameName = getSplashText();
        layout.setTop(startButton);
        layout.setCenter(gameName);
        FileChooser fileChooser = new FileChooser();
        browseFolder = browse("Browse", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                File file = fileChooser.showOpenDialog(stage);
                if(file!=null){
                    XMLParser parser = new XMLParser("game");
                    simData = parser.getSimData(file);
                    model.setSimData(simData);
                    try {
                        model.getGrid();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        layout.setBottom(browseFolder);
        Scene myScene = new Scene(layout, WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        stage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model = new VisualizationModel();
                try {
                    display = new VisualizationView(model);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                stage.setScene(display.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
                stage.show();
                frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
                animation = new Timeline();
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.getKeyFrames().add(frame);
            }
        });
    }
    private Text getSplashText() throws IOException {
        Text gameName = new Text();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/resources/UserInterface.properties"));
        Set p = properties.keySet();
        Iterator i = p.iterator();
        String key = "";
        while (i.hasNext()) {
            key = (String) i.next();
        }
        gameName.setText(properties.getProperty(key));
        gameName.setFont(Font.font("Apple Chancery",100));
        gameName.setTextAlignment(TextAlignment.CENTER);
        return gameName;
    }

    private Button start() {
        Button startButton = new Button();
        startButton.setText("Play");
        return startButton;
    }

    private Button browse (String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = property;
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    public void step(double elapsedTime) {
        //calls grid update
        model.myGrid.updateGrid();
    }


    /**
     * Start of the program.
     */
    public static void main(String[] args) {
        launch(args);
    }


}
