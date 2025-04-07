package com.example.comp2522202510termprojectpbspg;

import java.util.Objects;
import java.util.Random;
import javafx.scene.image.ImageView;

/**
 * Represents a Balloon object.
 *
 * @author Peter Beretanos
 * @version 2025
 */
public class Balloon {
    private static final int BALLOON_HEIGHT = 100;
    private static final int BALLOON_WIDTH = 84;
    private static final int THREE = 3;
    private static final double ONE_POINT_FIVE = 1.5;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int ZERO = 0;
    private static final Random random = new Random();
    private final ImageView balloonIMG;
    private double xVelocity;
    private final double yVelocity;
    private final double screenWidth;

    /**
     * Constructs a Balloon object with the specified image path and screen width.
     *
     * @param imagePath a String image path of the Balloon image
     * @param screenWidth a double of the screen width
     * @throws IllegalArgumentException if imagePath is null, empty, or whitespace-only
     */
    public Balloon(String imagePath, double screenWidth) {
        if (imagePath == null || imagePath.isBlank()){
            throw new IllegalArgumentException("Image path cannot be null, empty, or whitespace.");
        }
        balloonIMG = new ImageView(imagePath);
        this.screenWidth = screenWidth;
        this.balloonIMG.setFitWidth(BALLOON_HEIGHT);
        this.balloonIMG.setFitHeight(BALLOON_WIDTH);
        this.xVelocity = random.nextDouble() * THREE - ONE_POINT_FIVE;
        this.yVelocity = ONE + random.nextDouble() * TWO;
    }

    /**
     * Updates a Balloon objects position based on its x and y velocity.
     * Reverses horizontal direction if the balloon hits the game area boundaries.
     */
    public void update() {
        balloonIMG.setLayoutX(balloonIMG.getLayoutX() + xVelocity);
        balloonIMG.setLayoutY(balloonIMG.getLayoutY() + yVelocity);
        if (balloonIMG.getLayoutX() <= ZERO || balloonIMG.getLayoutX() + balloonIMG.getFitWidth() >= screenWidth) {
            xVelocity = -xVelocity;
        }
    }

}
