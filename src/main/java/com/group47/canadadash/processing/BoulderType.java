package com.group47.canadadash.processing;

public enum BoulderType {
    BOX("src/main/resources/com/group47/canadadash/object.png"),
    FENCE("src/main/resources/com/group47/canadadash/fence.png");

    private final String imagePath;

    BoulderType(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}