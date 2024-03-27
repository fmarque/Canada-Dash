package com.group47.canadadash.processing;

public class Boulder{

    public int x;
    public int y;
    public int height;
    public int width;
    public BoulderType type;
    public String imagePath;


    public Boulder(int x, int y , int height, int width){

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.imagePath = type.getImagePath();

    }

    public String getImagePath() {
        return imagePath;
    }


}




