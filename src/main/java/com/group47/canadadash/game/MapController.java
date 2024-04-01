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
        App app = new App();
        String uncompleted = "/images/Not_yet_completed.png";
        String completed = "/images/finished.png";
        Image uncompleted_img = new Image(String.valueOf(getClass().getResource(uncompleted)));
        Image completed_img = new Image(String.valueOf(getClass().getResource(completed)));
        boolean[] level_status = app.getUnlockedLevelsStatus();
        System.out.println(Arrays.toString(level_status));
        buttons = new Button[]{bcBox, abBox, skBox, mbBox, ontBox, qbcBox, nflBox, nbBox, nsBox, peiBox};
        icons = new ImageView[]{bcImg, abImg, skImg, mbImg, ontImg, qbcImg, nflImg, nbImg, nsImg, peiImg};
        for (int i = 0; i < level_status.length; i++) {
            icons[i].setImage(level_status[i] ? completed_img : uncompleted_img);
        }
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

}