package com.group47.canadadash.game;

import com.group47.canadadash.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Group;

import java.util.Objects;


public class GameRender extends Application {

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

    private Rectangle platform = new Rectangle(100, 450, 6000, 50); // x, y, width, height

    private boolean isColliding(Rectangle player, Rectangle obstacle) {
        return player.getBoundsInParent().intersects(obstacle.getBoundsInParent());
    }
    private void update() {

        // Update game state
        if (movingLeft) {
            scrollBackgroundRight();
        }
        if (movingRight) {
            scrollBackgroundLeft();
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
                Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
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
        gc.fillRect(playerX, playerY, playerWidth, playerHeight);

        gc.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
    }

    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("2D Platformer");

        internalGameState = new GameState();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();
        backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.png")));
        backgroundX2 = backgroundImage.getWidth();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = true;
            if (e.getCode() == KeyCode.RIGHT) movingRight = true;
            if (e.getCode() == KeyCode.SPACE) if (onGround) jump();
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) movingLeft = false;
            if (e.getCode() == KeyCode.RIGHT) movingRight = false;
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render(gc);
            }
        }.start();

        stage.show();
    }

    private void jump() {
        if (onGround) {
            playerVelocityY = -20; // Adjust this value to change jump height
            onGround = false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }



}
