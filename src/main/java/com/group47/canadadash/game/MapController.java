package com.group47.canadadash.game;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MapController {

    @FXML
    private void OnLevelBtn() {
        System.out.println("Welcome to Canada");
    }

    @FXML
    private Button backToMainMenuButton;

    @FXML
    private void handleBackToMainMenu(ActionEvent event) {
        System.out.println("Back to main menu button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playerMainMenu.fxml")); // Make sure the file name is correct
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, maybe show an error message
        }
    }
}
