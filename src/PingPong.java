import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class PingPong extends Application {

  Ball ball;
  double ballSpeed = 1;
  
  Paddle rightPaddle;
  Paddle leftPaddle;
  
  Wall rightWall;
  Wall leftWall;
  Wall topWall;
  Wall bottomWall;
  
  
   /**
    * The animation of the ball
    */ 
   Timeline ballAnimation;
  
  Button startButton;

  public static void main(String[] args) {
    Application.launch(args);
  }
  
  @Override
  public void start(Stage stage) {     
  
    setUpAllComponent();

    ballAnimation = new Timeline(60, new KeyFrame(Duration.millis(10.0), 
      t -> {
        ball.move(ballSpeed);
        checkForCollision();
        checkIfGameOver();

      })
    );
    ballAnimation.setCycleCount(Timeline.INDEFINITE);
    
    startButton = new Button("Start!");
    startButton.setLayoutX(225);
    startButton.setLayoutY(470);
    startButton.setOnAction(e -> {
      startButton.setVisible(false);
      ballAnimation.playFromStart();
    });  
    
    Group testGroup = new Group(ball.getBallShape(),
                                leftPaddle.getPaddleShape(),
                                rightPaddle.getPaddleShape(),
                                leftWall.getWallShape(),
                                rightWall.getWallShape(),
                                topWall.getWallShape(),
                                bottomWall.getWallShape(),
                                startButton);
    
    Scene scene = new Scene(testGroup, 500, 500);
    scene.setFill(Color.GRAY);
    
    stage.setScene(scene);
    stage.setTitle("Ping Pong Game");
    stage.show();
  }
  
  
  
  private void setUpAllComponent() {
    createBall();
    createPaddle();
    createWall();
  }
  private void createBall() {
    ball = new Ball(50, 50, 10);
  }
  private void createPaddle() {
    leftPaddle = new Paddle(20, 10, 10, 50);
    leftPaddle.setFill(Color.LIMEGREEN);
    
    rightPaddle = new Paddle(470, 10, 10, 50);
    rightPaddle.setFill(Color.MEDIUMBLUE);
  }
  private void createWall() {
    rightWall = new Wall(490, 0, 10, 500);
    rightWall.setFill(Color.BLUE);
    
    leftWall = new Wall(0, 0, 10, 500);
    leftWall.setFill(Color.RED);
    
    topWall = new Wall(0, 0, 500, 10);
    topWall.setFill(Color.GOLD);
    
    bottomWall = new Wall(0, 490, 500, 10);
    bottomWall.setFill(Color.BLUEVIOLET); 
  } 
  
  private void checkForCollision() {
    checkForBallAndWallCollision();
    checkForBallCollision(leftPaddle);
    checkForBallCollision(rightPaddle);
    checkForPaddleAndWallBoundary();
  }
  
  private void checkForBallAndWallCollision() {
    if(topWall.isCollision(ball) && ball.isMovingUp()) {
      ball.moveDown(ballSpeed);
    }
    if(bottomWall.isCollision(ball) && ball.isMovingDown()) {
      ball.moveUp(ballSpeed);
    }   
  }
  private void checkForBallCollision(Paddle paddle) {
    if(paddle.isCollision(ball) && ball.isMovingRight()) {
      ball.moveLeft(ballSpeed);
    }
    if(paddle.isCollision(ball) && ball.isMovingLeft()) {
      ball.moveRight(ballSpeed);
    }
  }
  
  private void checkForPaddleAndWallBoundary() {
    double rightWallEdge = rightWall.getX();
    double leftWallEdge = leftWall.getX() + leftWall.getWidth();
    double topWallEdge = topWall.getY() + topWall.getHeight();
    double bottomWallEdge = bottomWall.getY() - rightPaddle.getHeight();
    
    if(rightPaddle.getX() > rightWallEdge) {
      rightPaddle.move(rightWallEdge - rightPaddle.getWidth(), rightPaddle.getY());
    }
    if(rightPaddle.getX() < leftWallEdge) {
      rightPaddle.move(leftWallEdge, rightPaddle.getY());
    }
    if(rightPaddle.getY() < topWallEdge) {
      rightPaddle.move(rightPaddle.getX(), topWallEdge);
    }
    if(rightPaddle.getY() > bottomWallEdge) {
      rightPaddle.move(rightPaddle.getX(), bottomWallEdge);
    }
  }
  
  private void checkIfGameOver() { 
    if(rightWall.isCollision(ball) && ball.isMovingRight() ||
       leftWall.isCollision(ball) && ball.isMovingLeft()) {
      ballAnimation.stop();
      startButton.setVisible(true);
      ball.setCenterX(250);
      ball.setCenterY(250);
    }
  }    

}