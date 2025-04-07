package com.example.comp2522202510termprojectpbspg;

import java.io.Serializable;

/**
 * A serializable data class used to store the state of the game.
 *
 * @author Peter Beretanos
 * @version 2025
 */
public class SaveData implements Serializable {
    public int lives;
    public int balloonsShot;
    public int peopleDead;
    public double playerX;
    public double playerY;
}