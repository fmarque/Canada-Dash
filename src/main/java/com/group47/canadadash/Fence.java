package com.group47.canadadash;
/**
 * This class represents a fence in the game,
 * so if the user touches it, they will lose a life
 * fence has both x, and y location
 * width, and height dimension
 * and an image address pointing to its relevant img file
 */
public class Fence extends GameUnit {

    /**
     * Initializing the height, width and imageAdd of fence
     * @param givenX the x location to be set for fence
     * @param givenY the y location to be set for fence
     */
    public Fence(int givenX, int givenY) {
        super(givenX, givenY);
        height = 30;
        width = 40;
        imageAdd = "src/main/resources/com/group47/canadadash/fence.png";
    }


}
