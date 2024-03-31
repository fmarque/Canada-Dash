package com.group47.canadadash;

import com.group47.canadadash.processing.App;
import com.group47.canadadash.processing.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ScreenController implements Initializable {
    @FXML
    private CheckBox togglePassword;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;
    App app;
    @FXML
    private TextField username, password, type, classCode;
    @FXML
    private Text instruction;
    @FXML
    private PasswordField passHidden;


    public void switchToPLogin (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/playerLogin.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToILogin (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/instructorLogin.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDLogin (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/developerLogin.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPSignup (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/playerSignup.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToISignup (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/instructorSignup.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showPassword (ActionEvent event) throws IOException {
        togglevisiblePassword(event);
        System.out.println("toggle clicked");

    }

    public void clearUsername(MouseEvent event) {
        username.clear();
    }

    public void clearPassword(MouseEvent event) {
        password.clear();
    }

    public void clearField(MouseEvent event) {
        passHidden.clear();
    }
    public void clearCode(MouseEvent event) {
        classCode.clear();
    }


    @FXML
    public void togglevisiblePassword(ActionEvent event) throws IOException {
        if (togglePassword.isSelected()) {
            password.setText(passHidden.getText());
            password.setVisible(true);
            passHidden.setVisible(false);
            return;
        }
        passHidden.setText(password.getText());
        passHidden.setVisible(true);
        password.setVisible(false);
    }
    private String passwordValue() {
        return togglePassword.isSelected()?
                password.getText(): passHidden.getText();
    }
    public void checkPLoginInfo(ActionEvent event) throws IOException {
        app = new App();
        app.loadData();
        if (app.signIn(username.getText(), passHidden.getText(), "student")) {
            switchToPMenu(event);
        } else {
            instruction.setText("Invalid Player Info. Try again");
        }
    }

    public void checkILoginInfo(ActionEvent event) throws IOException {
        //app = new App();
        app.loadData();
        if (app.signIn(username.getText(), passHidden.getText(), "instructor")) {
            switchToIMenu(event);
        } else {
            instruction.setText("Invalid Instructor Info. Try again");
        }
    }

    //this method can be removed if player and instructor and dev info are the same
    public void checkDLoginInfo(ActionEvent event) throws IOException {
        app = new App();
        app.loadData();
        if (app.signIn("dev", passHidden.getText(), "developer")) {
            switchToDMenu(event);
        } else {
            instruction.setText("Invalid Developer Code. Try again");
        }
    }

    // sign up should check if sign in successful, if yes, then username taken, otherwise, create account
    public void makePAccount(ActionEvent event) throws IOException {
        app = new App();
        user = new User();
        app.loadData();

        // if username is already taken, let user know
        if (app.signIn(username.getText(), passHidden.getText(), "student")) {
            instruction.setText("Username Already Taken. Try again");
        } else {
            // if valid username, then check class code validity
            if (app.isValidClassCode(classCode.getText())) {
                boolean accountCreated = app.createAccount(username.getText(), password.getText(), "student");
                if (accountCreated) {
                    app.user.setClassCode(classCode.getText());
                    app.userSave();
                    switchToPLogin(event);
                }

                // if valid class code and username, make an account and make them login
            } else {
                instruction.setText("Invalid Class Code. Try again");
            }
        }
    }

    public void makeIAccount(ActionEvent event) throws IOException {
        app = new App();
        app.loadData();
        if (app.signIn("dev", passHidden.getText(), "developer")) {
            switchToDMenu(event);
        } else {
            instruction.setText("Invalid Developer Code. Try again");
        }
    }


    public void switchToPMenu (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/playerMainMenu.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIMenu (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/instructorMainMenu.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDMenu (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/developerMainMenu.fxml")));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.togglevisiblePassword(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
