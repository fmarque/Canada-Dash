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


    @FXML
    private void initialize() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        highScoreColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        highestLevelColumn.setCellValueFactory(new PropertyValueFactory<>("highestLevelReached"));
    }

    public void setStudents(List<User> students) {
        ObservableList<User> studentData = FXCollections.observableArrayList(students);
        studentTableView.setItems(studentData);
    }


    @FXML
    private void onBackButtonClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/instructorMainMenu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
