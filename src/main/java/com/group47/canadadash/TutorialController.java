package com.group47.canadadash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Application.launch;

public class TutorialController extends Application implements EventHandler<ActionEvent> {
    @FXML private Button backToLogin;
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
        // dev login page as placeholder for now, but will change once tut files are uploaded
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("developerLogin.fxml")));
        primaryStage.setTitle("How to Play");
        primaryStage.setScene(new Scene(root, 800, 500));
        //backToLogin.setOnKeyPressed(this);

        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
