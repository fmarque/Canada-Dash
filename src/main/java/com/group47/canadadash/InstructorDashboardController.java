package com.group47.canadadash;

import com.group47.canadadash.processing.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

public class InstructorDashboardController {

    @FXML
    private TableView<User> studentTableView;

    @FXML
    private TableColumn<User, String> studentIdColumn;

    @FXML
    private TableColumn<User, Number> highScoreColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onBackButtonClicked(ActionEvent event) {

    }
}
