package com.group47.canadadash;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

public class MainMenu extends Application {

    private int currentIndex = 0;
    private Label arrow = new Label("->");

    @Override
    public void start(Stage primaryStage) {
        VBox menu = new VBox(10);
        menu.setAlignment(Pos.CENTER_LEFT);

        String[] options = {"Option 1", "Option 2", "Option 3"};
        VBox optionsBox = new VBox();
        for (String option : options) {
            Label optionLabel = new Label(option);
            VBox.setMargin(optionLabel, new javafx.geometry.Insets(0, 0, 0, 20));
            optionsBox.getChildren().add(optionLabel);
        }

        updateArrowPosition(optionsBox);

        menu.getChildren().addAll(arrow, optionsBox);
        VBox.setMargin(arrow, new javafx.geometry.Insets(0, 0, 0, 5));

        Scene scene = new Scene(menu, 300, 200);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (currentIndex > 0) {
                        currentIndex--;
                        updateArrowPosition(optionsBox);
                    }
                    break;
                case DOWN:
                    if (currentIndex < options.length - 1) {
                        currentIndex++;
                        updateArrowPosition(optionsBox);
                    }
                    break;
                default:
                    break;
            }
        });

        primaryStage.setTitle("Menu Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateArrowPosition(VBox optionsBox) {
        arrow.setTranslateY(30 * currentIndex);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
