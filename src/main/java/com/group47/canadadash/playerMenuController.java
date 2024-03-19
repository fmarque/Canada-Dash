package com.group47.canadadash;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;


public class playerMenuController {
    public VBox menuVBox;
    public Label loadGameLabel;
    public Label startNewLabel;
    public Label highScoresLabel;
    public Label howToPlayLabel;
    public Label exitGameLabel;
    public Label menuTitle;
    public Label arrowLabel;
    private int currentIndex = 0; // Index of the currently selected menu item

    @FXML
    public void initialize() {
        // Initial setup if needed
        // For example, you could set the initial position of the arrow based on the first menu item
        updateArrowPosition();

        // Optional: Add listeners or initial setup here
    }


    /**
     * Handles key events on the scene.
     * @param event The key event.
     */
    public void handleKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                if (currentIndex > 0) {
                    currentIndex--;
                    updateArrowPosition();
                }
                break;
            case DOWN:
                if (currentIndex < menuVBox.getChildren().size() - 1) { // Ensure the index stays within bounds
                    currentIndex++;
                    updateArrowPosition();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Updates the position of the arrow based on the current index.
     */
    private void updateArrowPosition() {
        double newY = menuVBox.getChildren().get(currentIndex).getLayoutY() + menuVBox.getLayoutY() + 15; // +15 for visual alignment, adjust as necessary
        arrowLabel.setLayoutY(newY);
    }


}
