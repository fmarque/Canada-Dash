package com.group47.canadadash;

/**
 * This class represents the deep hole where the user's character
 * might fall into and loose a life for it
 */
public class Hole extends GameUnit {

    /**
     * Initializing the height, width and imageAdd of hole
     * @param givenX the x location to be set for hole
     * @param givenY the y location to be set for hole
     */
    public Hole(int givenX, int givenY) {
        super(givenX, givenY);
        height  = 130;
        width = 70;
        imageAdd = "";  //TODO: write the address which points to the hole image
    }


}
