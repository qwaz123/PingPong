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

public class TestPaddleAndBall extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }
  @Override
  public void start(Stage stage) { 
    Ball ball = new Ball(10, 10, 50);
    
    Paddle rightPaddle = new Paddle(400, 0, 10, 50);
    
    
    
    Timeline ballAnimation;
    ballAnimation = new Timeline(60, new KeyFrame(Duration.millis(10.0), 
      t -> {
          double speed = 0.1;
          ball.move(speed);
          
          if(rightPaddle.isCollision(ball) && ball.isMovingRight()) {
            ball.moveLeft(speed);
          }
          if(rightPaddle.isCollision(ball) && ball.isMovingLeft()) {
            ball.moveRight(speed);
          }
          // if(rightPaddle.isCollision(ball) && ball.isMovingUp()) {
            // ball.moveDown(speed);
          // }
          // if(rightPaddle.isCollision(ball) && ball.isMovingDown()) {
            // ball.moveUp(speed);
          // }   
          
      })
    );
    ballAnimation.setCycleCount(Timeline.INDEFINITE);
    ballAnimation.playFromStart();
    
    Group testGroup = new Group(ball.getBallShape(),
                                rightPaddle.getPaddleShape());
    
    Scene scene = new Scene(testGroup, 500, 500);
    scene.setFill(Color.GRAY);
    
    stage.setScene(scene);
    stage.setTitle("Test Ball and Paddle");
    stage.show();
  }
}