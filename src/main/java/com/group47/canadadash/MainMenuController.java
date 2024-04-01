package com.group47.canadadash;

import com.group47.canadadash.processing.App;
import com.group47.canadadash.processing.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainMenuController {
   private Stage stage;
   private Scene scene;
   private Parent root;
   private App app;


   public void setApp(App app) {
      this.app = app;
      System.out.println("App instance set in MainMenuController: " + app);
   }

   //TODO: load player's last saved progress (must be dynamic, NOT just fxml)
   public void loadSavedGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/game_map.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   public void startNewGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/game_map.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }


   // load the highscores amongst all students
   public void instViewScores(ActionEvent event) throws IOException {

      if (this.app == null) {
         this.app = App.getInstance();
      }

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/instructorDashboard.fxml")); // Correct path
      Parent root = loader.load();

      InstructorDashboardController controller = loader.getController();

      List<User> students = app.getStudentsForInstructor();
      controller.setStudents(students);
      controller.setBackContext("instructorView");


      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
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

   public void viewIHighscores(ActionEvent event) throws IOException{
      if (this.app == null) {
         this.app = App.getInstance();
      }

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/instructorDashboard.fxml")); // Correct path
      Parent root = loader.load();

      InstructorDashboardController controller = loader.getController();
      controller.setBackContext("instructorView");

      List<User> students = app.getUserList();
      controller.setStudents(students);


      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   public void viewHighscores(ActionEvent event) throws IOException{
      if (this.app == null) {
         this.app = App.getInstance();
      }

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/instructorDashboard.fxml")); // Correct path
      Parent root = loader.load();

      InstructorDashboardController controller = loader.getController();
      controller.setBackContext("playerView");

      List<User> students = app.getUserList();
      controller.setStudents(students);


      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
}
