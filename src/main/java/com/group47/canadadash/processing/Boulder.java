package com.group47.canadadash.processing;

/**
 * Represents a boulder object within a level, encapsulating its position, dimensions,
 * type, and associated image path.
 * <p>
 * This class serves as a foundational element for obstacles or environment features
 * in a game or simulation, providing a visual and spatial representation of boulders
 * that can interact with other entities or serve specific roles within the game's mechanics.
 * </p>
 *
 * @author Muhammad Affan Yasir
 * @version: 1.0
 * @since: 1.0
 */
public class Boulder {
    public int x;
    public int y;
    public int height;
    public int width;
    public BoulderType type;
    private final String imagePath;

    /**
     * Constructs a new Boulder with specified spatial dimensions, location, and type.
     * The image path is determined based on the boulder's type.
     *
     * @param x      The x-coordinate of the boulder's position.
     * @param y      The y-coordinate of the boulder's position.
     * @param height The height of the boulder.
     * @param width  The width of the boulder.
     * @param type   The {@link BoulderType} of the boulder, which determines its appearance.
     */
    public Boulder(int x, int y, int height, int width, BoulderType type) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.type = type;
        this.imagePath = type.getImagePath();
    }

    /**
     * Gets the image path associated with this boulder.
     *
     * @return A string representing the path to the image for this boulder, based on its type.
     */
    public String getImagePath() {
        return imagePath;
    }
}



