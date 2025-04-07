package com.example.comp2522202510termprojectpbspg;

import java.util.ArrayList;
import java.util.Iterator;
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

    /**
     * Moves bullets upward, removes off-screen bullets, and checks for collisions with balloons.
     */
    private void updateBullets() {
        Iterator<Rectangle> bulletIterator = bullets.iterator();

        while(bulletIterator.hasNext()) {
            Rectangle bullet = bulletIterator.next();
            bullet.setY(bullet.getY() - BULLET_SPEED);

            if (isBulletOffScreen(bullet)) {
                gamePane.getChildren().remove(bullet);
                bulletIterator.remove();
            }
            checkBulletHitBalloon(bullet, bulletIterator);
        }
    }

    /**
     * Checks if a bullet has moved off the top of the screen.
     *
     * @param bullet a Rectangle
     * @return true if off-screen, otherwise false
     */
    private boolean isBulletOffScreen(final Rectangle bullet){
        return bullet.getY() < ZERO;
    }

    /**
     * Checks whether a bullet intersects any balloon. If so, both are removed and score is updated.
     *
     * @param bullet a Rectangle
     * @param bulletIter the iterator used to remove the bullet
     */
    private void checkBulletHitBalloon(final Rectangle bullet, final Iterator<Rectangle> bulletIter) {
        Iterator<Balloon> balloonIterator = balloons.iterator();

        while(balloonIterator.hasNext()) {
            Balloon balloon = balloonIterator.next();
            if (bullet.getBoundsInParent().intersects(balloon.getBalloonIMG().getBoundsInParent())) {
                gamePane.getChildren().remove(bullet);
                gamePane.getChildren().remove(balloon.getBalloonIMG());
                bulletIter.remove();
                balloonIterator.remove();
                scoreBoard.balloonShot();
                break;
            }
        }

    }

    /**
     * Updates the position of balloons, removes off-screen ones, and checks for collisions with the player.
     */
    private void updateBalloons() {
        Iterator<Balloon> balloonIterator = balloons.iterator();

        while(balloonIterator.hasNext()) {
            Balloon balloon = balloonIterator.next();
            balloon.update();
            if (isBalloonOffScreen(balloon)) {
                gamePane.getChildren().remove(balloon.getBalloonIMG());
                balloonIterator.remove();
                scoreBoard.balloonPassed();
            }
            checkBalloonHitBatwing(balloon, balloonIterator);
        }
    }

    /**
     * Checks if a balloon made it off the bottom of the screen.
     *
     * @param balloon the Balloon to check
     * @return true if off-screen, otherwise false
     */
    private boolean isBalloonOffScreen(final Balloon balloon) {
        return balloon.isOffScreen(height);
    }

    /**
     * Checks if a balloon hits the Batwing. If so, the player loses a life and the Batwing may respawn.
     *
     * @param balloon the Balloon being checked
     * @param balloonIterator the iterator used to remove the balloon safely
     */
    private void checkBalloonHitBatwing(final Balloon balloon, final Iterator<Balloon> balloonIterator) {
        boolean isHit = balloon.getBalloonIMG().getBoundsInParent().intersects(player.getBatwingIMG().getBoundsInParent());
        if (player.isAlive() && isHit) {
            player.dead();
            scoreBoard.loseLife();

            if (scoreBoard.getLives() > ZERO) {
                player.respawn(BATWING_RESPAWN_TIME);
            }
            gamePane.getChildren().remove(balloon.getBalloonIMG());
            balloonIterator.remove();
        }
    }
}