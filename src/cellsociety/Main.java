package cellsociety;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application{

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 400 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 3.0 / FRAMES_PER_SECOND;
    public static final double WIDTH = 800;
    public static final double HEIGHT = 800;
    public static final Paint BACKGROUND = Color.GRAY;
    public static Timeline animation;

    @Override
    public void start(Stage stage) throws Exception {
        VBox layout = new VBox();
        Button startButton = start();
        Text gameName = getSplashText();
        layout.getChildren().add(startButton);
        layout.getChildren().add(gameName);
        Scene myScene = new Scene(layout,WIDTH,HEIGHT,BACKGROUND);
        stage.setScene(myScene);
        stage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene initialScene = setUpInitialScreen(WIDTH,HEIGHT,BACKGROUND);
                stage.setScene(initialScene);
            }
        });
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        // animation.play();
    }

    private Text getSplashText() {
        Text gameName = new Text();
        gameName.setText("Cellular Automata");
        gameName.setFont(Font.font(50));
        return gameName;
    }

    public Button start() {
        Button startButton = new Button();
        startButton.setText("Play");
        startButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
        startButton.setLayoutX(WIDTH/2);
        startButton.setLayoutY(HEIGHT);
        return startButton;
    }

    public Scene setUpInitialScreen(double width, double height, Paint background){
        Group root = new Group();

        Scene scene = new Scene(root,width,height,background);
        return scene;
    }


    public void ReadFile() {}

    public void step (double elapsedTime) {}






    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }


}
