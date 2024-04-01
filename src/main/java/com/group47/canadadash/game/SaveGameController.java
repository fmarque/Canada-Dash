package com.group47.canadadash.game;

import com.group47.canadadash.processing.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveGameController {

    @FXML
    private void handelSaveBtn(ActionEvent event){
        App.getInstance().userSave();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handelCancelBtn() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/01_settings.fxml"));
        Parent pauseMenuRoot = loader.load();
        // Setup the new stage for the pause menu

        // Block input events to other windows
        GameRender.pauseStage.setTitle("Pause Menu");
        Scene scene = new Scene(pauseMenuRoot);
        GameRender.pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        GameRender.pauseStage.showAndWait();
    }




}

