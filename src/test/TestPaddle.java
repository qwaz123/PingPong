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

public class TestPaddle extends Application {
  
  public static void main(String[] args) {
    Application.launch(args);
  }
  @Override
  public void start(Stage stage) { 
  
    Paddle rightPaddle = new Paddle(400, 0, 10, 50);
    
    
    Group testGroup = new Group(rightPaddle.getPaddleShape());
    
    
    
    Scene scene = new Scene(testGroup, 500, 500);
    scene.setFill(Color.GRAY);
    
    stage.setScene(scene);
    stage.setTitle("Test Paddle");
    stage.show();
  }  
}