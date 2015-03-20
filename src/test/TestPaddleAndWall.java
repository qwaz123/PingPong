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
import javafx.scene.paint.Color;

public class TestPaddleAndWall extends Application {
  
  private double paddleDragAnchorX;
  private double paddleDragAnchorY;
  
  public static void main(String[] args) {
    Application.launch(args);
  }
  @Override
  public void start(Stage stage) { 
  
    Paddle rightPaddle = new Paddle(400, 0, 10, 50);
    
    Wall rightWall = new Wall(490, 0, 10, 500);
    rightWall.setFill(Color.BLUE);
    Wall leftWall = new Wall(0, 0, 10, 500);
    leftWall.setFill(Color.RED);
    
    Wall topWall = new Wall(0, 0, 500, 10);
    topWall.setFill(Color.GOLD);
    Wall bottomWall = new Wall(0, 490, 500, 10);
    bottomWall.setFill(Color.BLUEVIOLET);
    
    rightPaddle.setOnMousePressed(me -> {
      paddleDragAnchorX = me.getSceneX() - rightPaddle.getX();
      paddleDragAnchorY = me.getSceneY() - rightPaddle.getY();
    });
    rightPaddle.setOnMouseDragged(me -> {
      double dragX = me.getSceneX() - paddleDragAnchorX;
      double dragY = me.getSceneY() - paddleDragAnchorY;
      
      double rightGapOfWallAndPaddle = rightWall.getX() - rightPaddle.getWidth();
      double leftGapOfWallAndPaddle = leftWall.getX() + leftWall.getWidth();
      double topGapOfWallAndPaddle = topWall.getY() + topWall.getHeight();
      double bottomOfWallAndPaddle = bottomWall.getY() - rightPaddle.getHeight();

      if(me.getSceneX() > rightGapOfWallAndPaddle) { 
        dragX = rightGapOfWallAndPaddle;
      } 
      if(me.getSceneX() < leftGapOfWallAndPaddle) { 
        dragX = leftGapOfWallAndPaddle;
      } 
      if(me.getSceneY() < topGapOfWallAndPaddle) {
        dragY = topGapOfWallAndPaddle;
      } 
      if(me.getSceneY() > bottomOfWallAndPaddle) {
        dragY = bottomOfWallAndPaddle;
      }
      rightPaddle.move(dragX, dragY);
    });
    
    
    Group testGroup = new Group(rightPaddle.getPaddleShape(),
                                rightWall.getWallShape(),
                                leftWall.getWallShape(),
                                topWall.getWallShape(),
                                bottomWall.getWallShape());
    
    
    
    Scene scene = new Scene(testGroup, 500, 500);
    scene.setFill(Color.GRAY);
    
    stage.setScene(scene);
    stage.setTitle("Test Wall and Paddle");
    stage.show();
  }  
}