package com.group47.canadadash.game;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenuController {

    @FXML
    private void initialize(){
        System.out.println(1);
    }
    @FXML
    private void handelSaveGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/02_save_progress.fxml"));
        Parent pauseMenuRoot = loader.load();
        // Setup the new stage for the pause menu

       // Block input events to other windows
        GameRender.pauseStage.setTitle("Pause Menu");
        Scene scene = new Scene(pauseMenuRoot);
        GameRender.pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        GameRender.pauseStage.showAndWait();
        // Show and wait - returns when the pause stage is closed

    }


    @FXML
    private void handelGameControl(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/05_game_control.fxml"));
        Parent pauseMenuRoot = loader.load();
        // Setup the new stage for the pause menu

        // Block input events to other windows
        GameRender.pauseStage.setTitle("Pause Menu");
        Scene scene = new Scene(pauseMenuRoot);
        GameRender.pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        GameRender.pauseStage.showAndWait();
        // Show and wait - returns when the pause stage is closed

    }


    @FXML
    private void handelGameMechanics(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/05_game_control.fxml"));
        Parent pauseMenuRoot = loader.load();
        // Setup the new stage for the pause menu

        // Block input events to other windows
        GameRender.pauseStage.setTitle("Pause Menu");
        Scene scene = new Scene(pauseMenuRoot);
        GameRender.pauseStage.setScene(scene);
        // Show and wait - returns when the pause stage is closed
        GameRender.pauseStage.showAndWait();
        // Show and wait - returns when the pause stage is closed

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

    @FXML
    private void handelExitGameBtn() throws IOException {
       System.exit(0);
    }

}
