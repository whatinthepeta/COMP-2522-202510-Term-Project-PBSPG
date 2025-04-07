package com.example.comp2522202510termprojectpbspg;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Represents the Game Object.
 *
 * @author Peter Beretanos
 * @version 2025
 */
public class Game {
    private static final long BALLOON_INTERVAL = 5000000000L;
    private static final double BACKGROUND_HEIGHT = 100.0;
    private static final double BACKGROUND_WIDTH = 100.0;
    private static final int HALF = 2;
    private static final int TEN = 10;
    private static final int ARM_OF_BALLOONS_START_POSITION = -50;
    private static final int BULLET_SPEED = 10;
    private static final int ZERO = 0;
    private static final int BATWING_RESPAWN_TIME = 3;
    private static final double ENDING_MESSAGE_FONT_SIZE = 40.0;
    private static final int NUMBER_OF_BALLOON_ARMY = 6;
    private static final int ONE_HUNDRED_TWENTY_FIVE = 125;
    private static final String SAVE_FILE = "savegame.ser";
    private final Pane gamePane;
    private final Batwing player;
    private final List<Balloon> balloons;
    private final List<Rectangle> bullets;
    private final ScoreBoard scoreBoard;
    private final double width;
    private final double height;
    private boolean canShoot;
    private boolean gameOver;
    private long lastBalloonCreationTime;

    /**
     * Constructs a new Game object with the specified screen width and height.
     * Initializes the game pane, player, background, scoreboard, and game state.
     *
     * @param width  a double the width of the game screen
     * @param height a double the height of the game screen
     */
    public Game(final double width, final double height) {
        this.width = width;
        this.height = height;
        this.gamePane = new Pane();
        Map map = new Map();
        this.constructMap(map);
        this.player = new Batwing(width);
        double startingXCalculation = width / HALF - this.player.getBatwingIMG().getFitWidth() / HALF;
        double startingYCalculation = height - this.player.getBatwingIMG().getFitHeight() - TEN;
        this.player.getBatwingIMG().setLayoutX(startingXCalculation);
        this.player.getBatwingIMG().setLayoutY(startingYCalculation);
        this.canShoot = true;
        this.gameOver = false;
        this.lastBalloonCreationTime = ZERO;
        this.balloons = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.scoreBoard = new ScoreBoard();
        gamePane.getChildren().add(this.player.getBatwingIMG());
        gamePane.getChildren().add(scoreBoard.getScoreBoard());
    }

    /**
     * Constructs the background of the game pane using the Map object.
     *
     * @param map the Map object containing the background image
     */
    private void constructMap(final Map map) {
        Image mapFinal = map.getMap();
        BackgroundImage fixBackgroundSize = new BackgroundImage(
                mapFinal,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BACKGROUND_HEIGHT, BACKGROUND_WIDTH, true, true, true, true)
        );
        gamePane.setBackground(new Background(fixBackgroundSize));
    }

    /**
     * Updates status of balloon spawning, bullet movement,
     * balloon movement, collision checks, scoreboard, and game updates.
     *
     * @param now the current time in nanoseconds
     */
    public void update(final long now) {
        if (player.isAlive()) {
            if (now - lastBalloonCreationTime > BALLOON_INTERVAL) {
                createBalloonRow();
                lastBalloonCreationTime = now;
            }
            updateBullets();
            updateBalloons();
            scoreBoard.update();
            checkGameEnd();
        }
    }
}