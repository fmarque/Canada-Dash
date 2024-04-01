package com.group47.canadadash.game;

import com.group47.canadadash.GameState;
import com.group47.canadadash.processing.Boulder;
import com.group47.canadadash.processing.BoulderType;
import com.group47.canadadash.processing.Level;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.stage.Modality;
import javafx.scene.control.Button;

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
    private Rectangle leaf = new Rectangle(WIDTH / 2 + 50, HEIGHT / 2 + 50, 100, 100);

    //UI elements
    private Text scoreText;
    public static Stage pauseStage;

    //Heart Image handling
    private final Image fullHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/fullHeart.png")));
    private final Image emptyHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/emptyHeartIcon.png")));
    private HBox heartsContainer;

    public GameRender() {
    }

    private boolean isColliding(Rectangle player, Rectangle obstacle) {
        return player.getBoundsInParent().intersects(obstacle.getBoundsInParent());
    }
    private void update() throws IOException {

        Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);

        if (isColliding(playerRect, obstacle)) {
            System.out.println("Player has touched the obstacle!");
            updateScore(5);// Placeholder action
        }

        if (isColliding(playerRect, leaf)) {
            System.out.println("Player has touched the leaf!");
            showNotification();
        }


        scrollBackgroundLeft();//background scrolls to left


        double leftBoundary = (double) WIDTH / 2 - 200;
        if (movingLeft && playerX > leftBoundary) {
            playerX -= 5;
        }
        double rightBoundary = (double) WIDTH / 2 + 200;
        if (movingRight && playerX < rightBoundary) {
            playerX += 5;
        }

        // Update player's vertical position and velocity
        if (!onGround)
        {
            // Apply gravity

            playerVelocityY += GRAVITY; // Make sure you have a gravity variable defined, e.g., 0.5 or 1
            playerY += playerVelocityY;

            // Prevent player from falling through the platform due to high velocity
            if (playerVelocityY > 0)
            { // Only check when coming down
                if (isColliding(playerRect, platform))
                {
                    onGround = true;
                    playerVelocityY = 0;
                    playerY = platform.getY() - playerHeight; // Adjust player to stand on top of the platform
                }
            }
        }

        if (playerY >= HEIGHT - playerHeight) {
            playerY = HEIGHT - playerHeight;
            onGround = true;
            playerVelocityY = 0;
        }

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

        gc.setFill(Color.GOLD);
        gc.fillRect(leaf.getX(), leaf.getY(), leaf.getWidth(), leaf.getHeight());

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
            // todo half heart
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
        StackPane uiLayer = new StackPane();
        uiLayer.getChildren().add(heartsContainer);
        uiLayer.getChildren().add(scoreText);

        uiLayer.getChildren().add(pauseButton);

        internalGameState = new GameState();
        Group root = new Group();
        Scene scene = new Scene(root);
        playerY = platform.getY() - playerHeight;//puts player on the ground
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        root.getChildren().add(uiLayer);

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
        }
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

        for (Boulder boulder : x) {
            if (boulder.type == BoulderType.FENCE) {
                URL resourceUrl = getClass().getResource("/images/fence.png");
                assert resourceUrl != null;
                platforms.add(createPlatform(boulder.x, HEIGHT - boulder.y + 450, boulder.width, boulder.height, resourceUrl));
            }

            if (boulder.type == BoulderType.BOX) {
                URL resourceUrl = getClass().getResource("/images/object.png");
                assert resourceUrl != null;
                platforms.add(createPlatform(boulder.x, HEIGHT - boulder.y + 450, boulder.width, boulder.height, resourceUrl));
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



}