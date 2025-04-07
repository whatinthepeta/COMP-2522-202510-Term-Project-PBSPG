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

    /**
     * Increases the number of people dead by a randomized amount between 50 and 100
     * everytime a balloon passes the Batwing.
     */
    public void balloonPassed() {
        int passedScore = MINIMUM_NUMBER_OF_DEATHS + random.nextInt(DEATHS_UP_TO_FIFTY_MORE);
        peopleDead += passedScore;
    }

    /**
     * Decreases the Batwing life count by one.
     */
    public void loseLife() {
        lives--;
    }

    /**
     * Returns the current number of lives the Batwing has left.
     *
     * @return an int of the number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Returns the current number of people dead.
     *
     * @return an int of current number of people dead
     */
    public int getPeopleDead() {
        return peopleDead;
    }

    /**
     * Returns the current number of balloons shot down from the Batwing.
     *
     * @return an int of the number of balloons shot down
     */
    public int getBalloonsShot() {
        return balloonsShot;
    }

    /**
     * Sets the number of lives the player has.
     *
     * @param lives the number of lives being set
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Sets the number of dead people.
     *
     * @param peopleDead the number of people dead being set
     */
    public void setPeopleDead(int peopleDead) {
        this.peopleDead = peopleDead;
    }

    /**
     * Sets the number of balloons the Batwing has popped.
     *
     * @param balloonsShot the number of balloons shot being set
     */
    public void setBalloonsShot(int balloonsShot) {
        this.balloonsShot = balloonsShot;
    }

    /**
     * Determines if the player has won the game by shooting enough balloons.
     *
     * @return {@code true} if the player has shot at least 100 balloons
     */
    public boolean hasWon() {
        return balloonsShot >= WIN_BALLOONS_SHOT;
    }

    /**
     * Determines if the player has lost the game by either losing all Batwing lives
     * or allowing too many people to be killed.
     *
     * @return true if loss conditions are met, otherwise false
     */
    public boolean hasLost() {
        return lives <= ZERO || peopleDead >= LOSE_BALLOONS_PASSED;
    }
}
