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

public class loginSignupController extends Application implements EventHandler<ActionEvent>{
    @FXML private Button pSwitchLogin, iSwitchLogin, dSwitchLogin;
    @FXML private Button pSwitchSignup, iSwitchSignup;
    @FXML private Button proceed, backToLogin;
    @FXML private CheckBox togglePw;
    @FXML private TextField enterUser, enterPw, enterCode;
    //private Label pageTitle;
    @FXML private TextArea pageTitle;

    // add stuff for the link to next scenes
    // instead of using multiple screens, reduce the ones with the same format
    // group 1: player and instructor login
    // group 2: player and instructor sign up (with slight removed stuff and added stuff: code enter for s/p)

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
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playerLogin.fxml")));
        primaryStage.setTitle("Player Login");
        primaryStage.setScene(new Scene(root, 800, 500));

        // create necessary instances
        proceed = new Button();
        pSwitchLogin = new Button();
        iSwitchLogin = new Button();
        dSwitchLogin = new Button();
        backToLogin = new Button();
        togglePw = new CheckBox();

        proceed.setOnAction(this.verifyLogin());
        pSwitchLogin.setOnAction(this);
        // insert the rest of the setOnAction things
        primaryStage.show();
    }

    // make methods for switch page depending on button pressed

    /**
     * This method verifies the user's credentials upon them entering it and clicking "->"
     *
     * @return
     */
    @FXML
    private EventHandler<ActionEvent> verifyLogin() {
        System.out.println("-> has been clicked");
        // do stuff to verify the credentials

        // switch cases depending on pagetitle to determine which menu to lead to
        // check if pageTitle is Login as existing player
        // load info from json file according to which user (check user AND pw)
        // if match, switch to appropriate player menu page
        // else, go to change page title to "Invalid Info. Try Again."
        // elif pT is for instructor login
        // load info from json file according to which user (check user AND pw)
        // if match, switch to appropriate player menu page
        // else, go to change page title to "Invalid Info. Try Again."
        // switch to instructor main menu
        // elif pT is for dev login
        // check access code matches hardcoded pw
        // if match, go to dev menu
        // else, chagne page title for error try again
        // if invalid, change text to say "Invalid info. Try again"
        //pageTitle.setText("Invalid Info. Try again");
        return null;
    }
    @Override
    public void handle(ActionEvent event) {

        String action = (String) event.getSource();
        switch(action) {
            case "proceed":
                System.out.println("proceed button was clicked");
        }
        /*
        if (event.getSource()==proceed) {

        }*/
    }

    // cases for when pw and proceed buttons are toggles
    // cases for when the diff signup login buttons are clicked
}

