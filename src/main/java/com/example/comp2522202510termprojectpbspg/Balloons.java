package com.example.comp2522202510termprojectpbspg;

/**
 * Represents an army of Balloons.
 *
 * @author Peter Beretanos
 * @version 2025
 */
public class Balloons {
    private static final double ZERO_POINT_FIVE = 0.5;
    private static final int TWENTY = 20;

    public Balloons(){}

    /**
     * Creates an array of Balloon objects with evenly distributed positions.
     *
     * @param screenWidth a double of the width of the screen
     * @param startY a double of the starting y-coordinate for all balloons
     * @param amount an int of the number of balloons to create
     * @return an array of Balloon objects
     */
    public static Balloon[] createArmyOfBalloons(final double screenWidth, final double startY, final int amount) {
        Balloon[] balloons = new Balloon[amount];
        for(int i = 0; i < amount; ++i) {
            String imageFilePath = Balloon.getRandomBalloonIMG();
            double xPos = screenWidth * (i + ZERO_POINT_FIVE) / amount - TWENTY;
            balloons[i] = new Balloon(imageFilePath, screenWidth);
            balloons[i].setBalloonX(xPos);
            balloons[i].setBalloonY(startY);        }
        return balloons;
    }
}