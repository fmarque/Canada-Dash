package com.group47.canadadash.game;

import com.group47.canadadash.GameState;
import com.group47.canadadash.processing.Boulder;
import com.group47.canadadash.processing.BoulderType;
import com.group47.canadadash.processing.Level;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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
import java.util.*;

import javafx.stage.Modality;
import javafx.util.Duration;

//import javax.swing.*;

/**
 * The type Game render.
 * Renders the main Game state
 */
public class GameRender{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double SAFE_POS_X = 50;// X-coordinate of the safe position
    private static final double SAFE_POS_Y = 100; // Y-coordinate of the safe position
    private GameState internalGameState;
    private Image backgroundImage;
    private final double scrollSpeed = 2;
    private double backgroundX = 0;
    private double backgroundX2;

    private int currentPlayerLifeCounter = 5;

    // Player properties
    private double playerX = (double) WIDTH / 2 - 20; // Center the player horizontally
    private double playerY = (double) HEIGHT / 2; // Position the player vertically
    private final double playerWidth = 40;
    private final double playerHeight = 60;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private double playerVelocityY = 0;
    private boolean onGround = false;
    private AnimationTimer gameLoop;
    private final Rectangle platform = new Rectangle(0, 450, 1000, 250); // x, y, width, height
    private final List<Rectangle> platformSegments = new ArrayList<>();

    private List<ImageView> platforms;
    private List<Integer> platformTypes;
    private ImageView leaf;
    private int lastCollisionIndex = -1;

    //UI elements
    private Text scoreText;
    /**
     * The constant pauseStage.
     */
    public static Stage pauseStage;

    //Heart Image handling
    private final Image fullHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/fullHeart.png")));
    private final Image emptyHeart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/emptyHeartIcon.png")));
    private HBox heartsContainer;

    private int jumpDelayCounter = 0;
    private final int JUMP_DELAY_FRAMES = 5; // Number of frames to skip ground check after jumping

    private boolean leafTouchable = true;
    private Level level;

    private void setupLeafSpawning() {
        Timeline leafSpawner = new Timeline(new KeyFrame(Duration.seconds(10), e -> spawnLeafRandomly()));
        leafSpawner.setCycleCount(Timeline.INDEFINITE);
        leafSpawner.play();
    }

    private void initializePlatformsWithPit() {
        // Clear existing platform segments
        platformSegments.clear();
        // First platform segment before the pit
        platformSegments.add(new Rectangle(0, 450, 300, 450)); // Adjust size as needed
        // Second platform segment after the pit
        platformSegments.add(new Rectangle(400, 450, 600, 450)); // Adjust position and size as needed

        platformSegments.add(new Rectangle(800, 450, 600, 450)); // Adjust position and size as needed
    }

    private void spawnLeafRandomly() {
        Random rand = new Random();

        double leftBoundary = (double) WIDTH / 2 - 200;
        double rightBoundary = (double) WIDTH / 2 + 200;

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
        checkPlayerPosition();
        checkWinCondition();
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
            onGround = isPlayerOnGround(playerRect, platforms);
        }

        updateObstaclesPosition();
        scrollBackgroundLeft();//background scrolls to left
        double leftBoundary = (double) WIDTH / 2 - 400;
        double rightBoundary = (double) WIDTH / 2 + 400;
        double moveAmount = 5; // How much the player moves per update
        double potentialNewX = playerX + (movingRight ? moveAmount : 0) - (movingLeft ? moveAmount : 0);

        // Check if the new position is within the allowed boundaries
        if (potentialNewX > leftBoundary && potentialNewX < rightBoundary - playerWidth) {
            playerX = potentialNewX;
        }


        boolean isCurrentlyColliding = false;
        for (int i = 0; i < platforms.size(); i++) {
            if (isCollidingObstacle(playerRect, platforms.get(i))) {
                isCurrentlyColliding = true;
                if (lastCollisionIndex != i) {
                    handlePlayerCollisionWithObstacle(i);
                    lastCollisionIndex = i;
                }
            }
        }
        if (!isCurrentlyColliding) {
            lastCollisionIndex = -1; // Reset if not colliding with any obstacle
        }


        if (!onGround) {
            double GRAVITY = 1;
            playerVelocityY += GRAVITY;
            playerY += playerVelocityY;
        } else if (jumpDelayCounter == 0) {
            playerVelocityY = 0; // Prevent further falling
        }

            if (isCollidingFromBelow(playerRect)) {
                playerVelocityY = 10; // Example knockdown velocity
                onGround = false;
            }
    }

    private void showQuizPopup() {
        // Assuming you have an AnimationTimer named 'animationTimer'
        gameLoop.stop(); // Stop the animation

        Platform.runLater(() -> {
            try {
                showMultipleChoiceTest();
//                Alert quizAlert = new Alert(Alert.AlertType.CONFIRMATION);
//                quizAlert.setTitle("Quiz Time");
//                // Setup and show the alert as before
                movingLeft = false;
                movingRight = false;
//                quizAlert.showAndWait();
            } finally {
                gameLoop.start(); // Restart the animation
            }
        });
    }
    private void showGameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You've run out of lives!");
        alert.setContentText("Would you like to go the map?");

        ButtonType buttonTypeRestart = new ButtonType("Map");
        ButtonType buttonTypeExit = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeRestart, buttonTypeExit);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeRestart) {
            // Restart the game
         //   restartGame();
        } else {
            // Exit
            Platform.exit();
        }
    }
    private void restartGame() {
        // Reset game state
        // Setup initial game scene again
        currentPlayerLifeCounter = 5;
        createGameScene(); // Assume you have a method that creates the initial game scene
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

        gc.setFill(Color.GREEN); // Set the platform color
        for (Rectangle segment : platformSegments) {
            gc.fillRect(segment.getX(), segment.getY(), segment.getWidth(), segment.getHeight());
        }

//        gc.setFill(Color.GREEN); // Set the obstacle color
//        gc.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());

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

        if(currentLives == 0)
        {
            Platform.runLater(this::showGameOverDialog);;
        }
    }

    private void updateScore(int score) {
        internalGameState.increasePoints(score);
        scoreText.setText("Score: " + internalGameState.getTotalPoints());
    }


    /**
     * Create game scene scene.
     *
     * @return the scene
     */
    public Scene createGameScene() {

        scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("Verdana", 20));
        scoreText.setFill(Color.BLACK); // Choose a color that fits your game's theme

        heartsContainer = new HBox(5); // Horizontal box with spacing of 5 pixels
        for (int i = 0; i < currentPlayerLifeCounter; i++) {//todo link wiht heart state
            ImageView heartView = new ImageView(fullHeart);
            heartView.setFitWidth(40); // Example width in pixels
            heartView.setFitHeight(40); // Example height in pixels

            // Preserve the aspect ratio (optional)
            heartView.setPreserveRatio(true);
            heartsContainer.getChildren().add(heartView);
        }
        currentPlayerLifeCounter--;

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
        initializePlatformsWithPit();
        root.getChildren().add(leaf);
        GraphicsContext gc1 = canvas.getGraphicsContext2D();
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

      //  System.out.println("Player Bottom Y: " + playerBottomY + ", Velocity: " + playerVelocityY);

        double nextFrameY = player.getY() + playerVelocityY;
        for (ImageView platform : platforms) {
            if (nextFrameY + player.getHeight() > platform.getY() &&
                    nextFrameY < platform.getY() + platform.getFitHeight() &&
                    player.getX() < platform.getX() + platform.getFitWidth() &&
                    player.getX() + player.getWidth() > platform.getX()) {
                return true;
            }
        }

//        double platformTopY = platform.getY();
//        if (nextFrameBottomY > platformTopY && playerBottomY <= platformTopY + 5) {
//            System.out.println("Collision with Main Platform at Y: " + platformTopY);
//            playerVelocityY = 0;
//            player.setY(platformTopY - player.getHeight()); // Adjust position to top of platform
//            return true;
//        }

        for (Rectangle platform : platformSegments) {
            if (nextFrameY + player.getHeight() > platform.getY() &&
                    nextFrameY < platform.getY() + platform.getWidth() &&
                    player.getX() < platform.getX() + platform.getWidth() &&
                    player.getX() + player.getWidth() > platform.getX())
            {
                playerVelocityY = 0;
                return true;
            }
        }

        return false; // In the air
    }


    private boolean isCollidingFromBelow(Rectangle player) {
        for (ImageView platform : platforms) {
            if (player.getY() < platform.getY() + platform.getFitHeight() &&
                    player.getY() + playerVelocityY > platform.getY() + platform.getFitHeight() &&
                    player.getX() < platform.getX() + platform.getFitWidth() &&
                    player.getX() + player.getWidth() > platform.getX()) {
                return true;
            }
        }
        return false;
    }

    private void checkPlayerPosition() {
        // Assuming 'HEIGHT' is the height of your game screen or level
        if (playerY > HEIGHT) {
            // Player has fallen off the screen, reset position to safe location
            playerX = SAFE_POS_X;
            playerY = SAFE_POS_Y;
            // Reset velocity if your game uses physics or velocity for movement
            playerVelocityY = 0;
            applyDamageToPlayer();
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


    /**
     * Load level.
     *
     * @param levels       the levels
     * @param currentStage the current stage
     */
    public void loadLevel(Level levels, int currentStage) {
        this.level = levels;
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
        updateLives(currentPlayerLifeCounter--);
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

    private void showMultipleChoiceTest() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
        popupStage.setTitle("Multiple Choice Test");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        var questionList = this.level.getQuestions();
        Random random = new Random();
        int min = 0; // Define minimum value, inclusive
        int max = 10; // Define maximum value, exclusive
        int randomNumber = random.nextInt(max - min) + min;

        var question = questionList.get(randomNumber);

        // Question
        RadioButton optionA = new RadioButton(question.getOptions().get(0));
        RadioButton optionB = new RadioButton(question.getOptions().get(1));
        RadioButton optionC = new RadioButton(question.getOptions().get(2));
        RadioButton optionD = new RadioButton(question.getOptions().get(3));

        optionA.setUserData("A");
        optionB.setUserData("B");
        optionC.setUserData("C");
        optionD.setUserData("D");
        // Group the radio buttons
        Label questionPrompt = new Label(question.getQuestion());
        questionPrompt.setFont(new Font("Arial", 16));
        questionPrompt.setStyle("-fx-font-weight: bold; -fx-padding: 10;");
        questionPrompt.setWrapText(true);


        ToggleGroup optionsGroup = new ToggleGroup();
        optionA.setToggleGroup(optionsGroup);
        optionB.setToggleGroup(optionsGroup);
        optionC.setToggleGroup(optionsGroup);
        optionD.setToggleGroup(optionsGroup);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();
            if (selectedRadioButton != null) {
                String answer = question.getAnswer();
                // Check the answer
                if (answer.equals(selectedRadioButton.getUserData())) {
                    showResult("Correct!");
                    updateScore(500);
                } else {
                    showResult("Wrong answer.");
                }

                popupStage.close(); // Close the popup
            }
        });

        layout.getChildren().addAll(questionPrompt, optionA, optionB, optionC, optionD, submitButton);

        Scene scene = new Scene(layout, 500, 500);
        popupStage.setScene(scene);
        popupStage.showAndWait(); // Show and wait for it to be closed
    }

    private void showResult(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWinDialog() {
        gameLoop.stop(); // Stop the game loop

        Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
        winAlert.setTitle("Congratulations!");
        winAlert.setHeaderText("You Win!");
        winAlert.setContentText("You've reached 1500 points. Heading back to the map.");
        winAlert.setOnHidden(evt -> showMapScene());
        winAlert.show();
    }

    private void showMapScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game_map.fxml")); // Adjust the path to your FXML file
            Parent mapRoot = loader.load();
            Scene mapScene = new Scene(mapRoot);

            Stage stage = (Stage) pauseStage.getScene().getWindow(); // Assumes `pauseStage` is your current stage, adjust as needed
            stage.setScene(mapScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    private void checkWinCondition() {
        if (internalGameState.getTotalPoints() >= 1500) {
            Platform.runLater(() -> {
                showWinDialog();
            });
        }
    }




}