package com.example.comp2522202510termprojectpbspg;

import javafx.animation.AnimationTimer;
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
}
