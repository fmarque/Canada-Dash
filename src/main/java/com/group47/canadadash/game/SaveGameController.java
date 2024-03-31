package com.group47.canadadash.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class SaveGameController {

//    @FXML
//    private void handelSaveBtn(){
//
//    }
//    }
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

