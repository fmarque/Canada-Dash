package com.group47.canadadash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {
   private Stage stage;
   private Scene scene;
   private Parent root;

   //TODO: load player's last saved progress (must be dynamic, NOT just fxml)
   public void loadSavedGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_map.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   public void startNewGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/newMap.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }


   public void playerViewScores() {

   }

   // load the highscores amongst all students
   public void instViewScores() {

   }

   public void switchToTutPage(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tutorialPage.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   public void switchToPLogin(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/playerLogin.fxml")));
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

   public void switchToILogin (ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/instructorLogin.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   public void viewSHighscores(ActionEvent event) throws IOException {

   }

   public void loadPlayerAnalytics (ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/instructorLogin.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
}
