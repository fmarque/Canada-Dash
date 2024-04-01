package com.group47.canadadash;

/**
 * This class is the parent class for classes such as: Box, Fence, Hole, Heart, Leaf, and Star
 * Since all of these classes are using the height, width, x, y location for their images, therefore
 * they are all using the methods from this class
 */
public class GameUnit {
    private int x;
    private int y;
    protected int width;
    protected int height;
    protected String imageAdd;

    /**
     * Initializing the height, width and imageAdd of each object of this class
     * @param givenX the x location to be set for each object of this class
     * @param givenY the y location to be set for each object of this class
     */
    public GameUnit(int givenX, int givenY){
        x = givenX;
        y = givenY;
        imageAdd = "";
    }

    /**
     * getter of attribute x
     * @return x
     */
    public int getX(){
        return x;
    }

    /**
     * getter of attribute x
     * @return y
     */
    public int getY(){
        return y;
    }

    /**
     * setter of attribute x
     * @param givenX is the location x
     */
    public void setX(int givenX) {
        x = givenX;
    }

    /**
     * getter of attribute y
     * @param givenY is the location y
     */
    public void setY(int givenY) {
        y = givenY;
    }

    /**
     * getter of attribute height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * setter of attribute x
     * @param height is the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * getter of attribute width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * setter of attribute width
     * @param width is the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getter of attribute imageAddr
     * @return image address
     */
    public String getImageAdd(){
        return imageAdd;
    }

    /**
     * thi method is used to move each of the objects 4 units,
     * so the whole game looks like it is moving to the left
     */
    public void move(){
        x = x + 4;
    }
}
