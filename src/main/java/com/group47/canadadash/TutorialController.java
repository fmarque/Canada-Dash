package com.group47.canadadash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.application.Application.launch;

public class TutorialController extends Application implements EventHandler<ActionEvent> {
    @FXML private Button backToLogin;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the stage and initialized necessary objects and variables for the controller
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tutorialPage.fxml")));
        primaryStage.setTitle("How to Play");
        primaryStage.setScene(new Scene(root, 800, 500));

        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }

    public void backToPMainMenu(ActionEvent event) throws IOException {
        ScreenController backMenu = new ScreenController();
        backMenu.switchToPMenu(event);
    }
}
