package com.group47.canadadash;
/**
 * This class represents a box in the game, in which the user can jump on it or over it
 * box has both x, and y location
 * width, and height dimension
 * and an image address pointing to its relevant img file
 */
public class Box extends GameUnit {

    /**
     * Initializing the height, width and imageAdd of box
     * @param givenX the x location to be set for box
     * @param givenY the y location to be set for box
     */
    public Box(int givenX, int givenY) {
        super(givenX, givenY);
        height = 40;
        width = 40;
        imageAdd = "src/main/resources/images/object.png";
    }

}
