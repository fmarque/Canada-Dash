package com.group47.canadadash.processing;

/**
 * Defines the types of boulders that can appear within a level, each associated with a specific image path.
 * This enumeration facilitates the differentiation of boulder appearances based on their type.
 * <p>
 * It provides a means to associate a unique image with each type of boulder, allowing for visual distinction
 * in the game's user interface. The paths to the images are specified as constants within the enum.
 * </p>
 *
 * @author Muhammad Affan Yasir
 * @version: 1.0
 * @since: 1.0
 */
public enum BoulderType {
    /**
     * Represents a box type boulder with an associated image path.
     */
    BOX("src/main/resources/com/group47/canadadash/object.png"),

    /**
     * Represents a fence type boulder with an associated image path.
     */
    FENCE("src/main/resources/com/group47/canadash/fence.png");

    private final String imagePath;

    /**
     * Constructs a BoulderType enum with the specified image path.
     *
     * @param imagePath The path to the image representing this type of boulder.
     */
    BoulderType(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Retrieves the image path associated with this boulder type.
     *
     * @return A string representing the path to the image for this boulder type.
     */
    public String getImagePath() {
        return imagePath;
    }
}