package com.example.comp2522202510termprojectpbspg;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

/**
 * Controls all input and game loop logic.
 */
public class GameController {
    private final Game game;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private AnimationTimer gameLoop;
    private boolean isRunning = false;

    /**
     * Constructs a GameController object.
     *
     * @param game The game instance to control
     */
    public GameController(Game game) {
        this.game = game;
    }

    /**
     * Sets up input handling for a scene.
     *
     * @param scene a Scene to handle input
     */
    public void setupInput(Scene scene) {
        scene.setOnKeyPressed((e) -> {
            pressedKeys.add(e.getCode());
            if (e.getCode() == KeyCode.SPACE) {
                game.shootHandling(true);
            }

            if (e.getCode() == KeyCode.Q) {
                game.saveGame();
                Platform.exit();
            }
        });
        scene.setOnKeyReleased((e) -> {
            pressedKeys.remove(e.getCode());
            if (e.getCode() == KeyCode.SPACE) {
                game.shootHandling(false);
            }
        });
    }

    /**
     * Starts the game loop.
     */
    public void startGameLoop() {
        if (isRunning) {
            return;
        }
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePlayerMovement();
                game.update(now);
                if (game.isGameOver()) {
                    stopGameLoop();
                }
            }
        };
        gameLoop.start();
        isRunning = true;
    }
}
