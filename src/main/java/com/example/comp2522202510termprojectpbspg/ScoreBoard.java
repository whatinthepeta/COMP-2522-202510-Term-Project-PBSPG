package com.example.comp2522202510termprojectpbspg;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreBoard {
    private static final int PLAYER_STARTING_LIVES = 3;
    private static final int ZERO = 0;
    private static final int WIN_BALLOONS_SHOT = 100;
    private static final int LOSE_BALLOONS_PASSED = 1000;
    private static final double SCOREBOARD_TEXT_X = 10.0;
    private static final double SCOREBOARD_TEXT_Y = 20.0;
    private static final double SCOREBOARD_FONT_SIZE = 14.0;
    private static final int MINIMUM_NUMBER_OF_DEATHS = 50;
    private static final int DEATHS_UP_TO_FIFTY_MORE = 51;
    private static final Random random = new Random();
    private int lives;
    private int balloonsShot;
    private int peopleDead;
    private final Text scoreBoard;

    /**
     * Constructs a ScoreBoard object with default starting values and initializes
     * the display text with default position, color, and font.
     */
    public ScoreBoard() {
        this.lives = PLAYER_STARTING_LIVES;
        this.balloonsShot = ZERO;
        this.peopleDead = ZERO;
        this.scoreBoard = new Text(SCOREBOARD_TEXT_X, SCOREBOARD_TEXT_Y, "");
        this.scoreBoard.setFill(Color.WHITE);
        this.scoreBoard.setFont(Font.font(SCOREBOARD_FONT_SIZE));
    }

    /**
     * Updates the scoreboard text with current lives, balloons shot, and people killed.
     */
    public void update() {
        scoreBoard.setText("Lives: " + lives + "\nBalloons: " +
                balloonsShot + "\nPeople Killed: " + peopleDead);
    }

    /**
     * Increments the number of balloons shot by the player.
     */
    public void balloonShot() {
        balloonsShot++;
    }
}
