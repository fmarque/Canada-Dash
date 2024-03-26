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
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT: movingLeft = false; break;
                case RIGHT: movingRight = false; break;
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }



}
