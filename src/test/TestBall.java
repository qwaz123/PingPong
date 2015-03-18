import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.beans.property.*;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.application.*;
import javafx.util.*;


public class TestBall extends Application {
  
    
  public static void main(String[] args) {
    Application.launch(args);
  }
  @Override
  public void start(Stage stage) { 
    Ball ball = new Ball(20, 20, 50);
    Timeline ballAnimation;
    ballAnimation = new Timeline(new KeyFrame(Duration.millis(10.0), 
      t -> {
          ball.moveRight(1); 
          ball.moveDown(1);          
      })
    );
    // ballAnimation.setCycleCount(Timeline.INDEFINITE);
    ballAnimation.setCycleCount(100);
    ballAnimation.playFromStart();
    
    Group testGroup = new Group(ball.getBallShape());
    
    Scene scene = new Scene(testGroup, 500, 500);
    scene.setFill(Color.GRAY);
    
    stage.setScene(scene);
    stage.setTitle("Test Ball");
    stage.show();
  }
}