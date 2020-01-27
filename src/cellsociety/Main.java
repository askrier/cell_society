package cellsociety;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 400 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 3.0 / FRAMES_PER_SECOND;
    public static final double WIDTH = 500;
    public static final double HEIGHT = 500;
    public static final Paint BACKGROUND = Color.GRAY;

    public Text t;

    @Override
    public void start(Stage stage) throws Exception {
        Scene initialScene = setUpInitialScreen(WIDTH,HEIGHT,BACKGROUND);
        stage.setScene(initialScene);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public Scene setUpInitialScreen(double width, double height, Paint background){
        Group root = new Group();
        t = new Text();
        t.setText("hi");
        root.getChildren().add(t);

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
