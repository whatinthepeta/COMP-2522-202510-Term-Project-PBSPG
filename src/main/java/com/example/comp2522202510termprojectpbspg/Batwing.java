package com.example.comp2522202510termprojectpbspg;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.Objects;

/**
 * Represents the Batwing object.
 *
 * @author Peter Beretanos
 * @version 2025
 */
public class Batwing extends ImageView {
    private static final int BATWING_HEIGHT = 80;
    private static final int BATWING_WIDTH = 40;
    private static final String BATWING_IMAGE_PATH = "/images/BatwingIMG.png";
    private static final int BATWING_SPEED = 5;
    private static final int TWO = 2;
    private static final int HALF = 2;
    private static final int BATWING_BULLET_WIDTH = 4;
    private static final int BATWING_BULLET_HEIGHT = 10;
    private final ImageView batwingIMG;
    private boolean alive;
    private final double gameWidth;

    /**
     * Constructs a Batwing object.
     *
     * @param screenWidth a double of the screen width
     */
    public Batwing(double screenWidth) {
        this.batwingIMG = new ImageView(BATWING_IMAGE_PATH);
        this.gameWidth = screenWidth;
        this.batwingIMG.setFitWidth(BATWING_HEIGHT);
        this.batwingIMG.setFitHeight(BATWING_WIDTH);
        this.alive = true;
    }

    /**
     * Moves the Batwing Object to the left if it's alive and not out of bounds.
     */
    public void moveLeft() {
        if (alive) {
            double newX = batwingIMG.getLayoutX() - BATWING_SPEED;
            if (newX >= 0) {
                this.batwingIMG.setLayoutX(newX);
            }
        }
    }

    /**
     * Moves the Batwing Object to the right if it's alive and not out of bounds.
     */
    public void moveRight() {
        if (alive) {
            double newX = batwingIMG.getLayoutX() + BATWING_SPEED;
            if (newX + batwingIMG.getFitWidth() <= gameWidth) {
                batwingIMG.setLayoutX(newX);
            }
        }
    }

    /**
     * Creates a Rectangle object represented as a bullet at the current Batwing position if alive.
     *
     * @return a Rectangle representing the bullet or null if dead
     */
    public Rectangle shoot() {
        if (alive) {
            Rectangle bullet = new Rectangle(BATWING_BULLET_WIDTH, BATWING_BULLET_HEIGHT);
            bullet.setX(batwingIMG.getLayoutX() + batwingIMG.getFitWidth() / HALF - TWO);
            bullet.setY(batwingIMG.getLayoutY());
            return bullet;
        }
        return null;
    }

    /**
     * Marks the Batwing as dead and hides it from the view of the game.
     */
    public void dead() {
        alive = false;
        this.batwingIMG.setVisible(false);
    }

    /**
     * Respawns the Batwing after a specified delay in seconds.
     *
     * @param respawnDelay the number of seconds to wait before respawning
     */
    public void respawn(int respawnDelay) {
        PauseTransition pause = new PauseTransition(Duration.seconds(respawnDelay));
        pause.setOnFinished((_) -> {
            alive = true;
            batwingIMG.setVisible(true);
        });
        pause.play();
    }

    /**
     * Returns the image representing the Batwing.
     *
     * @return the ImageView of the Batwing
     */
    public ImageView getBatwingIMG() {
        return this.batwingIMG;
    }

    /**
     * Returns whether the Batwing is currently alive.
     *
     * @return true if alive, false otherwise
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * Determines if two Batwing objects are equal.
     *
     * @param other an object
     * @return true if they are equal, else false
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Batwing that)) return false;
        return Objects.equals(that.batwingIMG, batwingIMG) &&
                alive == that.alive &&
                Double.compare(that.gameWidth, gameWidth) == 0;
    }

    /**
     * Returns a hashcode of type Batwing.
     *
     * @return a hashcode of type Batwing
     */
    @Override
    public int hashCode() {
        return Objects.hash(batwingIMG, alive, gameWidth);
    }

    /**
     * Returns a String of this Batwing.
     *
     * @return a String of this Batwing
     */
    @Override
    public String toString() {
        return "Batwing{batwingIMG='" + batwingIMG + "', alive='" + alive +
                "', yVelocity=" + gameWidth +"}";
    }
}
