package com.example.comp2522202510termprojectpbspg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double SCREEN_WIDTH = 600;
    private static final double SCREEN_HEIGHT = 400;
    private GameController gameController;

    @Override
    public void start(Stage primaryStage) {
        Game gameWorld = new Game(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameWorld.loadGame();
        gameController = new GameController(gameWorld);
        Scene scene = new Scene(gameWorld.getGamePane(), SCREEN_WIDTH, SCREEN_HEIGHT);
        gameController.setupInput(scene);
        gameController.startGameLoop();
        primaryStage.setTitle("Batwing Batallon Battle");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(_ -> gameController.stopGameLoop());
    }

    public static void main(String[] args) {
        launch(args);
    }
}