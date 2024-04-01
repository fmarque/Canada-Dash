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

/**
 * Controller class for the main menu of the application. This class handles user
 * interaction with the main menu UI, including starting new games, loading saved games,
 * switching to different login screens (player, instructor, developer), and viewing
 * high scores.
 *
 * It interacts with the {@link App} class to perform application-level operations like
 * retrieving user lists for high scores and managing game states.
 *
 * @author: Frances Marquez, Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version: 1.0
 * @since: 1.0
 */
public class MainMenuController {
   private Stage stage;
   private Scene scene;
   private Parent root;
   private App app;


   /**
    * Sets the {@link App} instance for this controller, allowing interaction with
    * the application's core functionalities.
    *
    * @param app The singleton instance of the application's main class.
    */
   public void setApp(App app) {
      this.app = app;
      System.out.println("App instance set in MainMenuController: " + app);
   }

   /**
    * Loads the player's last saved game. This method dynamically loads the saved
    * game state rather than relying on static FXML, allowing for a more flexible
    * and user-specific experience.
    *
    * @param event The action event triggering this method.
    * @throws IOException If there is an error loading the FXML file.
    */
   //TODO: load player's last saved progress (must be dynamic, NOT just fxml)
   public void loadSavedGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/game_map.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Starts a new game by loading the game map. Similar to loadSavedGame, but
    * always starts a fresh game rather than loading a save.
    *
    * @param event The action event triggering this method.
    * @throws IOException If there is an error loading the FXML file.
    */
   public void startNewGame(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/game_map.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }


   /**
    * Loads the high scores view for instructors, showing scores amongst all students.
    * Assumes the role of the current user is an instructor.
    *
    * @param event The action event triggering this method.
    * @throws IOException If there is an error loading the FXML file.
    */
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

   /**
    * Switches to the tutorial page, guiding users through the application usage or
    * game mechanics. This method facilitates access to instructional content.
    *
    * @param event The action event that triggered this method.
    * @throws IOException If an error occurs during FXML loading.
    */
   public void switchToTutPage(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tutorialPage.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Switches to the player login page.
    *
    * @param event The action event triggering this method.
    * @throws IOException If there is an error loading the FXML file.
    */
   public void switchToPLogin(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/playerLogin.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Switches to the developer login page.
    *
    * @param event The action event triggering this method.
    * @throws IOException If there is an error loading the FXML file.
    */
   public void switchToDLogin (ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/developerLogin.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Switches to the instructor login page.
    *
    * @param event The action event triggering this method.
    * @throws IOException If there is an error loading the FXML file.
    */
   public void switchToILogin (ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/instructorLogin.fxml")));
      stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Displays high scores accessible to instructors. This method allows instructors to
    * view a dashboard containing high scores among their students, facilitating academic
    * review and competition.
    *
    * @param event The action event that triggered this method.
    * @throws IOException If an error occurs during FXML loading or controller initialization.
    */
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

   /**
    * Displays high scores from a player's perspective. This method is tailored for players
    * to view their standings amongst peers, encouraging a competitive yet educational environment.
    *
    * @param event The action event that triggered this method.
    * @throws IOException If an error occurs during FXML loading or controller initialization.
    */
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
