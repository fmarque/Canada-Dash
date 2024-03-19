package com.group47.canadadash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Objects;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerMenu.fxml")); // Ensure this path is correctly pointing to your FXML file("playerMenu.fxml"))); // Ensure the path is correct
        Parent root = loader.load();
        playerMenuController controller = loader.getController(); // Get the controller directly from the FXMLLoader

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(controller::handleKeyEvent); // Use a method reference to handle key events

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
