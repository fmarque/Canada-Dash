package com.group47.canadadash;

/**
 * This class represents character's life in the game
 * heart has both x, and y location
 * width, and height dimension
 * and an image address pointing to its relevant img file
 */
public class Heart extends GameUnit {

    private boolean heart;

    /**
     * Initializing the height, width and imageAdd of heart
     * @param givenX the x location to be set for heart
     * @param givenY the y location to be set for heart
     */
    public Heart(int givenX, int givenY) {
        super(givenX, givenY);
        height = 10;
        width = 10;
        imageAdd = ""; //TODO: write the address which points to the heart image
        heart = true;
    }

    /**
     * setting the heart as full/empty
     * @param type true, if the heart is full,
     *             and false if the heart is empty
     */
    public void setHeart(boolean type) {
        this.heart = type;
        setImageAdd();
    }

    /**
     * getter of the heart type
     * @return true, if the heart is full,
     *         and false if the heart is empty
     */
    public boolean isFullHeart() {
        return heart;
    }

    /**
     * setting the address of the heart image
     */
    public void setImageAdd() {
        if (this.isFullHeart()){
            imageAdd = "";  //TODO: pointing to the full heart image
        }else {
            imageAdd = "";  //TODO: pointing to the empty heart image
        }
    }
}
