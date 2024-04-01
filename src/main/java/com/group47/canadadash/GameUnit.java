package com.group47.canadadash;

public class GameUnit {
    private int x;
    private int y;
    protected int width;
    protected int height;
    protected String imageAdd;

    public GameUnit(int givenX, int givenY){
        x = givenX;
        y = givenY;
        imageAdd = "";
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int givenX) {
        x = givenX;
    }

    public void setY(int givenY) {
        y = givenY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getImageAdd(){
        return imageAdd;
    }

    public void move(){
        x = x + 4;
    }
}
