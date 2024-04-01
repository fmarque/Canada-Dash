package com.group47.canadadash.game;

import com.group47.canadadash.processing.App;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameLauncher extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameRender gameRender = new GameRender();
        App x = new App();
        x.loadData();
        gameRender.loadLevel(x.getLevels().get(8), 9);
        Scene gameScene = gameRender.createGameScene(); // Adjust `createGameScene` method to accept a Stage if necessary
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Canada Dash"); // Set the window title here
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
