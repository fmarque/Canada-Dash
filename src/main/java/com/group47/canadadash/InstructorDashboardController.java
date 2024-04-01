package com.group47.canadadash;

import com.group47.canadadash.processing.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Instructor Dashboard UI.
 * Manages the display of student information including ID, high scores, and the highest level reached.
 * Allows for dynamic navigation based on the context (player or instructor view)
 * through the setting of a back context and customized back button functionality.
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version : 1.0
 * @since : 1.0
 */
public class InstructorDashboardController {

    @FXML
    private TableView<User> studentTableView;
    @FXML
    private TableColumn<User, String> studentIdColumn;
    @FXML
    private TableColumn<User, Number> highScoreColumn;
    @FXML
    private TableColumn<User, Number> highestLevelColumn;
    @FXML
    private Button backButton;

    private String backContext = "default";

    /**
     * Initializes the controller. This method is automatically called after the FXML file has been loaded.
     * It sets up the table columns to display student information.
     */
    @FXML
    private void initialize() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        highScoreColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        highestLevelColumn.setCellValueFactory(new PropertyValueFactory<>("highestLevelReached"));
    }

    /**
     * Sets the list of students to be displayed in the table view.
     * If the list is empty, it prints a message to the console indicating no students are registered under this instructor.
     *
     * @param students The list of students to display.
     */
    public void setStudents(List<User> students) {
        if (students.isEmpty()) {
            System.out.println("No students registered under this instructor.");
        }
        ObservableList<User> studentData = FXCollections.observableArrayList(students);
        studentTableView.setItems(studentData);
    }

    /**
     * Sets the context for the back button's action.
     * The context determines which scene to load when the back button is clicked.
     *
     * @param context The context indicating the destination scene (e.g., "playerView" or "instructorView").
     */
    public void setBackContext(String context) {
        this.backContext = context;
    }

    /**
     * Handles the back button click event.
     * Loads a different FXML based on the previously set context.
     *
     * @param event The action event triggering this method.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @FXML
    private void onBackButtonClicked(ActionEvent event) throws IOException {
        switch (backContext) {
            case "playerView":
                loadFXML("/fxml/playerMainMenu.fxml", event);
                break;
            case "instructorView":
            default:
                loadFXML("/fxml/instructorMainMenu.fxml", event);
                break;
        }
    }

    /**
     * Loads the specified FXML file and sets it as the current scene.
     *
     * @param fxmlPath The path to the FXML file to load.
     * @param event The action event triggering this method.
     * @throws IOException If the FXML file cannot be loaded.
     */
    private void loadFXML(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

