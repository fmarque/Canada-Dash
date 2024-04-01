package com.group47.canadadash.game;

import com.group47.canadadash.GameState;
import com.group47.canadadash.processing.Boulder;
import com.group47.canadadash.processing.BoulderType;
import com.group47.canadadash.processing.Level;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.stage.Modality;
import javafx.scene.control.Button;
import javafx.util.Duration;

//import javax.swing.*;

public class GameRender{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private GameState internalGameState;
    private GraphicsContext gc;
    private Image backgroundImage;
    private double scrollSpeed = 2;
    private double backgroundX = 0;
    private double backgroundX2;



    // Player properties
    private double playerX = WIDTH / 2 - 20; // Center the player horizontally
    private double playerY = HEIGHT / 2; // Position the player vertically
    private final double playerWidth = 40;
    private final double playerHeight = 60;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private final double GRAVITY = 1;
    private double playerVelocityY = 0;
    private boolean onGround = false;
    private AnimationTimer gameLoop;
    private Rectangle platform = new Rectangle(100, 450, 6000, 50); // x, y, width, height
    private Rectangle obstacle = new Rectangle(WIDTH / 2 - 20, HEIGHT / 2, 100, 100);

    private List<ImageView> platforms;
    private List<Integer> platformTypes;
    private ImageView leaf;

    //UI elements
    private Text scoreText;
    public static Stage pauseStage;

    //Heart Image handling
    private final Image fullHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/fullHeart.png")));
    private final Image emptyHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/emptyHeartIcon.png")));
    private HBox heartsContainer;

    private int jumpDelayCounter = 0;
    private final int JUMP_DELAY_FRAMES = 5; // Number of frames to skip ground check after jumping

    private Timeline leafSpawner;
    private boolean leafTouchable = true;
    private void setupLeafSpawning() {
        leafSpawner = new Timeline(new KeyFrame(Duration.seconds(10), e -> spawnLeafRandomly()));
        leafSpawner.setCycleCount(Timeline.INDEFINITE);
        leafSpawner.play();
    }

    private void spawnLeafRandomly() {
        Random rand = new Random();

        double leftBoundary = (double) WIDTH / 2 - 200;
        double rightBoundary = (double) WIDTH / 2 + 200;

        double minX = 0.0;
        double maxX = leftBoundary + (rightBoundary - leftBoundary) * rand.nextDouble();
        double minY = 0.0;
        double maxY = HEIGHT - leaf.getImage().getHeight();



        leaf.setX(maxX);
        leaf.setY(minY + (maxY - minY) * rand.nextDouble());
        leaf.setVisible(true);
        leafTouchable = true; // Allow the leaf to be touched again
    }

    private void initializeLeaf() {
        Image leafImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/leaf.png")));
        leaf = new ImageView(leafImage);
        leaf.setVisible(false); // Initially hidden
    }

    private boolean checkCollisionWithLeaf(Rectangle playerRect) {
        return playerRect.intersects(leaf.getBoundsInParent());
    }



    private boolean isColliding(Rectangle player, Rectangle obstacle) {
        return player.getBoundsInParent().intersects(obstacle.getBoundsInParent());
    }
    private void update() throws IOException {

        Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);

        if (checkCollisionWithLeaf(playerRect)) {
           if(leafTouchable)
           {
               showQuizPopup();
           }
            leafTouchable = false; // Prevent further touches until respawned
        }
        
        
        
        if (jumpDelayCounter > 0) {
            jumpDelayCounter--;
        } else {
            onGround = isPlayerOnGround(new Rectangle(playerX, playerY, playerWidth, playerHeight), platforms);
        }

        updateObstaclesPosition();
        if (isColliding(playerRect, obstacle)) {
            System.out.println("Player has touched the obstacle!");
            updateScore(5);// Placeholder action
        }


        //onGround = isPlayerOnGround(playerRect, platforms);
        scrollBackgroundLeft();//background scrolls to left


        double leftBoundary = (double) WIDTH / 2 - 200;
        if (movingLeft && playerX > leftBoundary) {
            playerX -= 5;
        }
        double rightBoundary = (double) WIDTH / 2 + 200;
        if (movingRight && playerX < rightBoundary) {
            playerX += 5;
        }

        for(int i =0; i < platforms.size(); i++)
        {
            if(isCollidingObstacle(playerRect, platforms.get(i)))
            {
                handlePlayerCollisionWithObstacle(i);
            }
        }


        if (!onGround) {
            playerVelocityY += GRAVITY;
            playerY += playerVelocityY;
        } else if (jumpDelayCounter == 0) {
            playerVelocityY = 0; // Prevent further falling
        }

        // Update player's vertical position and velocity
//        if (!onGround)
//        {
//            // Apply gravity
//            playerVelocityY += GRAVITY; // Make sure you have a gravity variable defined, e.g., 0.5 or 1
//            playerY += playerVelocityY;
//
//            // Prevent player from falling through the platform due to high velocity
//            if (playerVelocityY > 0)
//            { // Only check when coming down
//                if (isColliding(playerRect, platform))
//                {
//                    onGround = true;
//                    playerVelocityY = 0;
//                    playerY = platform.getY() - playerHeight; // Adjust player to stand on top of the platform
//                }
//            }
//        }




//        // Update player's vertical position and velocity
//        if (!onGround)
//        {
//            // Apply gravity
//            playerVelocityY += GRAVITY; // Make sure you have a gravity variable defined, e.g., 0.5 or 1
//            playerY += playerVelocityY;
//
//            // Prevent player from falling through the platform due to high velocity
//            if (playerVelocityY > 0)
//            { // Only check when coming down
//                for (ImageView imageView : platforms) {
//                    if (isCollidingObstacle(playerRect, imageView)) {
//                        onGround = true;
//                        playerVelocityY = 0;
//                        playerY = imageView.getY() - playerHeight; // Adjust player to stand on top of the platform
//                    }
//                }
//
//            }
//        }
//        else {
//            playerVelocityY = 0;
//        }



        for (ImageView obstacle : platforms) {
            if (isCollidingFromBelow(playerRect, obstacle)) {
                handleCollisionFromBelow();
                break; // Assuming only one collision is handled per frame
            }
        }

        if (playerY >= HEIGHT - playerHeight) {
            playerY = HEIGHT - playerHeight;
            onGround = true;
            playerVelocityY = 0;
        }
    }

    private void showQuizPopup() {
        // Assuming you have an AnimationTimer named 'animationTimer'
        gameLoop.stop(); // Stop the animation

        Platform.runLater(() -> {
            try {
                Alert quizAlert = new Alert(Alert.AlertType.CONFIRMATION);
                quizAlert.setTitle("Quiz Time");
                // Setup and show the alert as before
                quizAlert.showAndWait();
            } finally {
                gameLoop.start(); // Restart the animation
            }
        });
    }


    private void scrollBackgroundLeft() {
        backgroundX -= scrollSpeed;
        backgroundX2 -= scrollSpeed;

        if (backgroundX <= -backgroundImage.getWidth()) {
            backgroundX = backgroundImage.getWidth();
        }
        if (backgroundX2 <= -backgroundImage.getWidth()) {
            backgroundX2 = backgroundImage.getWidth();
        }
    }

    private void scrollBackgroundRight() {
        backgroundX += scrollSpeed;
        backgroundX2 += scrollSpeed;

        if (backgroundX >= backgroundImage.getWidth()) {
            backgroundX = -backgroundImage.getWidth();
        }
        if (backgroundX2 >= backgroundImage.getWidth()) {
            backgroundX2 = -backgroundImage.getWidth();
        }
    }


    private void render(GraphicsContext gc) {
        // Render game

        // Clear the canvas
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        // Draw the scrolling backgrounds
        gc.drawImage(backgroundImage, backgroundX, 0);
        gc.drawImage(backgroundImage, backgroundX2, 0);

        // Draw the player
        gc.setFill(Color.RED); // Set the obstacle color
        gc.fillRect(playerX, playerY, playerWidth, playerHeight);

        gc.setFill(Color.GREEN); // Set the obstacle color
        gc.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());

        // Draw the obstacle
        gc.setFill(Color.BLUE); // Set the obstacle color
        gc.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        
        for (ImageView imageView : platforms) {
            gc.drawImage(imageView.getImage(), imageView.getX(), imageView.getY());
        }


    }



    private void updateLives(int currentLives) {
        for (int i = 0; i < heartsContainer.getChildren().size(); i++) {
            ImageView heartView = (ImageView) heartsContainer.getChildren().get(i);
            if (i < currentLives) {
                heartView.setImage(fullHeart);
            } else {
                heartView.setImage(emptyHeart);
            }
        }
    }

    private void updateScore(int score) {
        internalGameState.increasePoints(score);
        scoreText.setText("Score: " + internalGameState.getTotalPoints());
    }


    public Scene createGameScene() {

        scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("Verdana", 20));
        scoreText.setFill(Color.BLACK); // Choose a color that fits your game's theme

        heartsContainer = new HBox(5); // Horizontal box with spacing of 5 pixels
        for (int i = 0; i < 3; i++) {//todo link wiht heart state
            ImageView heartView = new ImageView(fullHeart);
            heartsContainer.getChildren().add(heartView);
        }


        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(10); // Position the button; adjust as needed
        pauseButton.setLayoutY(10);
        pauseButton.setFocusTraversable(false);
        pauseButton.setOnAction(event -> {
            try {
                showPauseMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        //UI elements INI
        AnchorPane uiLayer = new AnchorPane();

// Place heartsContainer in the top-left corner
        AnchorPane.setTopAnchor(heartsContainer, 10.0);
        AnchorPane.setLeftAnchor(heartsContainer, 10.0);

// Place scoreText in the top-right corner
        AnchorPane.setTopAnchor(scoreText, 10.0);
        AnchorPane.setRightAnchor(scoreText, 10.0);

// Place pauseButton in the bottom-right corner
        AnchorPane.setBottomAnchor(pauseButton, 10.0);
        AnchorPane.setRightAnchor(pauseButton, 10.0);
        uiLayer.setPrefSize(WIDTH, HEIGHT);
        uiLayer.getChildren().addAll(heartsContainer, scoreText, pauseButton);



        internalGameState = new GameState();
        Group root = new Group();
        Scene scene = new Scene(root);
        playerY = platform.getY() - playerHeight;//puts player on the ground
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        root.getChildren().add(uiLayer);
        initializeLeaf();
        setupLeafSpawning();
        root.getChildren().add(leaf);
        gc = canvas.getGraphicsContext2D();
        backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.png")));
        backgroundX2 = backgroundImage.getWidth();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = true;
            if (e.getCode() == KeyCode.RIGHT) movingRight = true;
            if (e.getCode() == KeyCode.SPACE) if (onGround) jump();
            if (e.getCode() == KeyCode.ESCAPE) {
                try {
                    showPauseMenu();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getCode() == KeyCode.M) {
                try {
                    showMap();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = false;
            if (e.getCode() == KeyCode.RIGHT) movingRight = false;
        });

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                render(gc);
            }
        };

        gameLoop.start();
        return scene;

    }

    private void showPauseMenu() throws IOException {
        // Pause the game loop
        gameLoop.stop();

        // Load the pause menu FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/01_settings.fxml"));
        Parent pauseMenuRoot = loader.load();

        // Setup the new stage for the pause menu
        pauseStage = new Stage();
        pauseStage.initModality(Modality.APPLICATION_MODAL); // Block input events to other windows
        pauseStage.setTitle("Pause Menu");
        Scene scene = new Scene(pauseMenuRoot);
        pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        pauseStage.showAndWait();

        // Optionally resume the game loop here if not handled by the FXML controller
        gameLoop.start();
    }


    private void showNotification() throws IOException {
        // Pause the game loop
        //gameLoop.stop();

        // Load the pause menu FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/01_settings.fxml"));
        Parent pauseMenuRoot = loader.load();

        // Setup the new stage for the pause menu
        Stage pauseStage = new Stage();
        pauseStage.initModality(Modality.APPLICATION_MODAL); // Block input events to other windows
        pauseStage.setTitle("Pause Menu");
        Scene scene = new Scene(pauseMenuRoot);
        pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        // Your code to show the dialog
        Platform.runLater(pauseStage::showAndWait);
        gameLoop.stop();
        // Optionally resume the game loop here if not handled by the FXML controller
        //  gameLoop.start();
    }


    private void jump() {
        if (onGround) {
            playerVelocityY = -20; // Adjust this value to change jump height
            onGround = false;
            System.out.println(playerVelocityY);
        }
    }

    private boolean isPlayerOnGround(Rectangle player, List<ImageView> obstacles) {
        double playerBottomY = player.getY() + player.getHeight();
        double nextFrameBottomY = playerBottomY + playerVelocityY; // Predict next position
        double tolerance = 5; // Tolerance for fast movements

        System.out.println("Player Bottom Y: " + playerBottomY + ", Velocity: " + playerVelocityY);

        for (ImageView obstacle : obstacles) {
            double obstacleTopY = obstacle.getY();
            if (nextFrameBottomY > obstacleTopY && playerBottomY <= obstacleTopY) {
                System.out.println("Collision with Obstacle at Y: " + obstacleTopY);
                playerVelocityY = 0;
                player.setY(obstacleTopY - player.getHeight()); // Adjust position to top of obstacle
                return true;
            }
        }

        double platformTopY = platform.getY();
        if (nextFrameBottomY > platformTopY && playerBottomY <= platformTopY + tolerance) {
            System.out.println("Collision with Main Platform at Y: " + platformTopY);
            playerVelocityY = 0;
            player.setY(platformTopY - player.getHeight()); // Adjust position to top of platform
            return true;
        }

        return false; // In the air
    }


    private boolean isCollidingFromBelow(Rectangle player, ImageView obstacle) {
        double playerTopY = player.getY();
        double obstacleBottomY = obstacle.getY() + obstacle.getFitHeight();

        // Check if the player is moving up and collides with the bottom edge of the obstacle
        if (playerVelocityY < 0 && playerTopY <= obstacleBottomY && playerTopY + playerVelocityY > obstacleBottomY) {
            return true;
        }
        return false;
    }

    private void handleCollisionFromBelow() {
        // Example of applying a knockdown effect
        // Set the player's velocity to simulate being knocked downwards
        playerVelocityY = 10; // Adjust the value based on the desired knockdown effect
        onGround = false; // Ensure gravity takes over after the initial knockdown impulse
    }


    private void showMap() throws IOException {
        gameLoop.stop();

        // Load the pause menu FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game_map.fxml"));
        Parent pauseMenuRoot = loader.load();

        // Setup the new stage for the pause menu
        pauseStage = new Stage();
        pauseStage.initModality(Modality.APPLICATION_MODAL); // Block input events to other windows
        pauseStage.setTitle("Map Menu");
        Scene scene = new Scene(pauseMenuRoot);
        pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        pauseStage.showAndWait();

        // Optionally resume the game loop here if not handled by the FXML controller
        gameLoop.start();
    }


    public void loadLevel(Level levels, int currentStage) {
        List<Boulder> x = levels.getBoulders();
        platforms = new ArrayList<>();
        platformTypes = new ArrayList<>();
        for (Boulder boulder : x) {
            if (boulder.type == BoulderType.FENCE) {
                URL resourceUrl = getClass().getResource("/images/fence.png");
                assert resourceUrl != null;
                platforms.add(createPlatform(boulder.x, HEIGHT - boulder.y + 450, boulder.width, boulder.height, resourceUrl));
                platformTypes.add(0);
            }

            if (boulder.type == BoulderType.BOX) {
                URL resourceUrl = getClass().getResource("/images/object.png");
                assert resourceUrl != null;
                platforms.add(createPlatform(boulder.x, HEIGHT - boulder.y + 450, boulder.width, boulder.height, resourceUrl));
                platformTypes.add(1);
            }
        }
    }

    private ImageView createPlatform(int x, int y, int width, int height, URL imagePath) {
        Image image = new Image(imagePath.toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private void updateObstaclesPosition() {
        for (ImageView obstacle : platforms) {
            obstacle.setX(obstacle.getX() - scrollSpeed);
            // Reset position if it goes off-screen, or apply other logic

            // Check if the obstacle has moved off the left side of the screen
            if (obstacle.getX() + obstacle.getFitWidth() < 0) {
                // Reset its position to the right side of the screen
                obstacle.setX(WIDTH + obstacle.getFitWidth());
                // Optionally, randomize the Y position to vary the appearance
                obstacle.setY(Math.random() * (HEIGHT - obstacle.getFitHeight()));
            }
        }
    }

    private boolean isCollidingObstacle(Rectangle player, ImageView obstacle) {
        return player.getBoundsInParent().intersects(obstacle.getBoundsInParent());
    }

    private void applyDamageToPlayer() {
        internalGameState.loseLife();
        updateLives(internalGameState.getLives());
    }

    private void handlePlayerCollisionWithObstacle(int index) {
        switch (index) {
            case 0:
                applyDamageToPlayer();
                break;
            case 1:
                break;
        }
    }

    public void loadLevel(List<Level> levels) {
    }
}