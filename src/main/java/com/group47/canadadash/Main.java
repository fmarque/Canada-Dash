package com.group47.canadadash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application implements EventHandler<ActionEvent> {
    public static void main(String[] args) {
        launch(args);
    }

    // MAIN ISSUE: how to connect the fxml buttons to the ones here

    /**
     * Sets the stage and initialized necessary objects and variables for the controller
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/playerLogin.fxml")));
        primaryStage.setTitle("Player Login");
        primaryStage.setScene(new Scene(root, 800, 500));

        // insert the rest of the setOnAction things
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
