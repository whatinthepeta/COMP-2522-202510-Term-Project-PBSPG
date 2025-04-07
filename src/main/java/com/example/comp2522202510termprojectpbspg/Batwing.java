package com.example.comp2522202510termprojectpbspg;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

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
}