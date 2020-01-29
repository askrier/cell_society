package cellsociety;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        VBox layout = new VBox(WIDTH/2);
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
                stage.show();
            }
        });
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Text getSplashText() {
        Text gameName = new Text();
        gameName.setText("Cellular Automata");
        gameName.setFont(Font.font(50));
        gameName.setTextAlignment(TextAlignment.CENTER);
        return gameName;
    }

    public Button start() {
        Button startButton = new Button();
        startButton.setText("Play");
        startButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
        startButton.setLayoutX(0);
        startButton.setLayoutY(HEIGHT);
        return startButton;
    }



    public Scene setUpInitialScreen(double width, double height, Paint background){
        Group root = new Group();
        Button playButton = playSim();
        Button stopButton = stopSim();
        Button slowButton = slowDown();
        Button speedButton = speedUp();
        Button stepButton = stepThrough();
        root.getChildren().add(playButton);
        root.getChildren().add(stopButton);
        root.getChildren().add(slowButton);
        root.getChildren().add(speedButton);
        root.getChildren().add(stepButton);
        Scene scene = new Scene(root,width,height,background);
        return scene;
    }

       public Button playSim(){
           Button playButton = new Button();
           playButton.setText("Play");
           playButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
           playButton.setLayoutX(0);
           playButton.setLayoutY(HEIGHT-100);
           return playButton;
        }

        public Button stopSim(){
            Button stopButton = new Button();
            stopButton.setText("Stop");
            stopButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
            stopButton.setLayoutX(100);
            stopButton.setLayoutY(HEIGHT-100);
            return stopButton;
        }
        public  Button slowDown(){
            Button slowButton = new Button();
            slowButton.setText("Slow");
            slowButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
            slowButton.setLayoutX(200);
            slowButton.setLayoutY(HEIGHT-100);
            return slowButton;
        }
        public Button speedUp(){
            Button speedButton = new Button();
            speedButton.setText("Speed");
            speedButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
            speedButton.setLayoutX(300);
            speedButton.setLayoutY(HEIGHT-100);
            return speedButton;
        }
        public Button stepThrough(){
            Button stepButton = new Button();
            stepButton.setText("Step");
            stepButton.setStyle("-fx-font: 18 arial; -fx-background-color: #b6e7c9");
            stepButton.setLayoutX(400);
            stepButton.setLayoutY(HEIGHT-100);
            return stepButton;
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
