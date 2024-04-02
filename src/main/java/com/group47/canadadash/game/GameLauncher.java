package com.group47.canadadash.game;

import com.group47.canadadash.processing.App;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*

Util class to test out the Game
Not really sure how you're supposed to test a real time render application with moving parts otherwise***
 */
public class GameLauncher extends Application {
    @Override
    public void start(Stage primaryStage) {//Starts the scene
        GameRender gameRender = new GameRender();//Gets instance of the game
        App x = new App();//gets backend reference
        x.loadData();//loads data from json
        gameRender.loadLevel(x.getLevels().get(1), 1);//loads a level of your choice
        Scene gameScene = gameRender.createGameScene(); //  Init the game state
        primaryStage.setScene(gameScene);//sets the primary scene to game render
        primaryStage.setTitle("Canada Dash"); // Set the window title here
        primaryStage.show();//shows on screen
    }

    public static void main(String[] args) {
        launch(args);
    }

}
