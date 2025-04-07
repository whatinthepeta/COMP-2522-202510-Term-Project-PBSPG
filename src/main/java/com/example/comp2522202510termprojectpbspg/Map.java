package com.example.comp2522202510termprojectpbspg;

import javafx.scene.image.Image;

/**
 * Represents the game map.
 *
 * @author Peter Beretanos
 * @version 2025
 */
public class Map {
    private static final String MAP_IMAGEPATH = "/images/cityIMG.png";
    private final Image map;

    /**
     * Constructs a Map object with an image file path.
     */
    public Map() {
        this.map = new Image(MAP_IMAGEPATH);
    }

    /**
     * Returns the background image of the game.
     *
     * @return an Image representing the game map
     */
    public Image getMap(){
        return map;
    }
}
