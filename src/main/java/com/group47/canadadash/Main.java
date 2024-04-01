package com.group47.canadadash;

import com.group47.canadadash.processing.App;
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



    /**
     * Sets the stage and initialized necessary objects and variables for the controller
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playerLogin.fxml"));
        Parent root = loader.load();

        ScreenController controller = loader.getController();
        controller.setApp(App.getInstance());


        primaryStage.setTitle("Player Login");
        primaryStage.setScene(new Scene(root, 800, 500));

        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
