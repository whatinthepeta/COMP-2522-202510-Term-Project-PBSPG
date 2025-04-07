package com.example.comp2522202510termprojectpbspg;

import java.util.List;
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
}