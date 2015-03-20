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


public class TestBallAndWall extends Application {
    
  public static void main(String[] args) {
    Application.launch(args);
  }
  @Override
  public void start(Stage stage) { 
    Ball ball = new Ball(50, 20, 50);

    Wall rightWall = new Wall(490, 0, 10, 500);
    rightWall.setFill(Color.BLUE);
    Wall leftWall = new Wall(0, 0, 10, 500);
    leftWall.setFill(Color.RED);
    
    Wall topWall = new Wall(0, 0, 500, 10);
    topWall.setFill(Color.GOLD);
    Wall bottomWall = new Wall(0, 490, 500, 10);
    bottomWall.setFill(Color.BLUEVIOLET); 
    
    Timeline ballAnimation;
    ballAnimation = new Timeline(new KeyFrame(Duration.millis(10.0), 
      t -> {
          double speed = 10;
          ball.move(speed);
          
          if(rightWall.isCollision(ball) && ball.isMovingRight()) {
            ball.moveLeft(speed);
          }
          if(leftWall.isCollision(ball) && ball.isMovingLeft()) {
            ball.moveRight(speed);
          }
          if(topWall.isCollision(ball) && ball.isMovingUp()) {
            ball.moveDown(speed);
          }
          if(bottomWall.isCollision(ball) && ball.isMovingDown()) {
            ball.moveUp(speed);
          }   
          
      })
    );
    ballAnimation.setCycleCount(Timeline.INDEFINITE);
    ballAnimation.playFromStart();
    
    Group testGroup = new Group(ball.getBallShape(),
                                rightWall.getWallShape(),
                                leftWall.getWallShape(),
                                topWall.getWallShape(),
                                bottomWall.getWallShape());
    
    Scene scene = new Scene(testGroup, 500, 500);
    scene.setFill(Color.GRAY);
    
    stage.setScene(scene);
    stage.setTitle("Test Ball and Wall");
    stage.show();
  }
}