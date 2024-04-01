package com.group47.canadadash.game;

import com.group47.canadadash.processing.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MapController {
    private HashMap mapping;
    private  App app;
    @FXML
    private Button bcBox;

    @FXML
    private ImageView bcImg;

    @FXML
    private Button abBox;

    @FXML
    private ImageView abImg;

    @FXML
    private Button skBox;

    @FXML
    private ImageView skImg;

    @FXML
    private Button mbBox;

    @FXML
    private ImageView mbImg;

    @FXML
    private Button ontBox;

    @FXML
    private ImageView ontImg;

    @FXML
    private Button qbcBox;

    @FXML
    private ImageView qbcImg;

    @FXML
    private Button nflBox;

    @FXML
    private ImageView nflImg;


    @FXML
    private Button nbBox;

    @FXML
    private ImageView nbImg;

    @FXML
    private Button nsBox;

    @FXML
    private ImageView nsImg;

    @FXML
    private Button peiBox;

    @FXML
    private ImageView peiImg;


    private Button[] buttons;

    @FXML
    private void OnLevelBtn() {

    }

    @FXML
    private Button backToMainMenuButton;
    private ImageView[] icons;

    @FXML
    private void initialize() {
        app = new App();
        String uncompleted = "/images/Not_yet_completed.png";
        String completed = "/images/finished.png";
        Image uncompleted_img = new Image(String.valueOf(getClass().getResource(uncompleted)));
        Image completed_img = new Image(String.valueOf(getClass().getResource(completed)));
        boolean[] level_status = app.getUnlockedLevelsStatus();
        System.out.println(Arrays.toString(level_status));
        buttons = new Button[]{bcBox, abBox, skBox, mbBox, ontBox, qbcBox, nflBox, nbBox, nsBox, peiBox};
        icons = new ImageView[]{bcImg, abImg, skImg, mbImg, ontImg, qbcImg, nflImg, nbImg, nsImg, peiImg};
        for (int i = 0; i < level_status.length; i++) {
            // Set the image based on completion status
            icons[i].setImage(level_status[i] ? completed_img : uncompleted_img);

            // Enable the button if the level is completed, otherwise disable it
            buttons[i].setDisable(!level_status[i]);
        }


        bcBox.setOnAction(e -> openGameRenderScene(0));
        abBox.setOnAction(e -> openGameRenderScene(1));
        skBox.setOnAction(e -> openGameRenderScene(2));
        mbBox.setOnAction(e -> openGameRenderScene(3));
        ontBox.setOnAction(e -> openGameRenderScene(4));
        qbcBox.setOnAction(e -> openGameRenderScene(5));
        nflBox.setOnAction(e -> openGameRenderScene(6));
        nbBox.setOnAction(e -> openGameRenderScene(7));
        nsBox.setOnAction(e -> openGameRenderScene(8));
        peiBox.setOnAction(e -> openGameRenderScene(9));
    }




    public void handleBackToMainMenu(ActionEvent event) {
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

    @FXML
    private void handleLevelSelection(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        //    openGameRenderScene(clickedButton);
    }


    private void openGameRenderScene(int level) {
        Stage stage = (Stage) bcBox.getScene().getWindow(); // Assuming 'startGameButton' is your button's fx:id
        GameRender gameRender = new GameRender();
        App x = new App();
        x.loadData();
        gameRender.loadLevel(x.getLevels().get(level), level); // Assuming you want to load the first level
        Scene gameScene = gameRender.createGameScene();
        stage.setScene(gameScene);
        stage.setTitle("Canada Dash");
        stage.show();
    }


}