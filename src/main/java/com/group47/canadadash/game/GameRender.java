package com.group47.canadadash.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GameRender extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private double playerX = 400;
    private double playerY = 300;
    private final double playerWidth = 40;
    private final double playerHeight = 60;

    private boolean movingLeft = false;
    private boolean movingRight = false;

    private boolean movingUp = false;
    private boolean movingDown = false;

    private double playerVelocityY = 0;
    private boolean onGround = true;


    /*
    Moves Player X coordinate
     */
    private void movePlayerX() {

    }

    /*
    Moves Player Y coordinate
     */
    private  void movePlayerY(){

    }

    private void update() {
        // Update game state
        if (movingLeft) playerX -= 5;
        if (movingRight) playerX += 5;

        // Apply gravity
        if (!onGround) {
            playerVelocityY += 1; // Adjust this value for gravity strength
            playerY += playerVelocityY;
        }

        // Implement a simple ground check
        if (playerY >= 300) { // Assuming 300 is ground level
            playerY = 300;
            onGround = true;
            playerVelocityY = 0;
        } else {
            onGround = false;
        }


    }

    private void render(GraphicsContext gc) {
        // Render game

        // Clear the canvas
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        // Draw the player
        gc.fillRect(playerX, playerY, playerWidth, playerHeight);
    }

    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("2D Platformer");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Game loop
                update();
                render(gc);
            }
        }.start();


        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT: movingLeft = true; break;
                case RIGHT: movingRight = true; break;
                case SPACE: if (onGround) jump(); break; // Jump on space press
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT: movingLeft = false; break;
                case RIGHT: movingRight = false; break;
               
            }
        });

    }

    private void jump() {
        if (onGround) {
            playerVelocityY = -15; // Adjust this value to change jump height
            onGround = false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }



}
