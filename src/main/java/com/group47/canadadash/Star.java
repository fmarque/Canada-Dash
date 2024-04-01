package com.group47.canadadash;

/**
 * This class represents each checkpoint in CanadaDash,
 * Which is collected in the form of a star,
 * star has both x, and y location
 * width, and height dimension
 * and an image address pointing to its relevant img file
 */
public class Star extends GameUnit {

    /**
     * Initializing the height, width and imageAdd of star
     * @param givenX the x location to be set for star
     * @param givenY the y location to be set for star
     */
    public Star(int givenX, int givenY) {
        super(givenX, givenY);
        height = 20;
        width = 20;
        imageAdd = "src/main/resources/com/group47/canadadash/star.png";
    }

}
