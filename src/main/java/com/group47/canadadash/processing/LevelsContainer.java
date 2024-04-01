package com.group47.canadadash.processing;

import com.group47.canadadash.processing.Level;

import java.util.Map;


/**
 * A container class that holds a collection of levels within the application.
 * Each level is mapped by a unique string identifier, facilitating easy access
 * and management of different levels.
 * <p>
 * This class is designed to aggregate levels and provide methods for
 * retrieving and updating the entire collection.
 * </p>
 *
 * @author Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version: 1.0
 * @since: 1.0
 */
public class LevelsContainer {
    private Map<String, Level> levels;

    /**
     * Retrieves the map of levels contained within this container.
     * The map keys are unique identifiers for each level, and the values
     * are the {@link Level} objects themselves.
     *
     * @return A map of level identifiers to {@link Level} objects.
     */
    public Map<String, Level> getLevels() {
        return levels;
    }

    /**
     * Sets the map of levels for this container. This method allows for
     * the replacement of the current map of levels with a new one.
     *
     * @param levels A map of level identifiers to {@link Level} objects
     *               that will replace the current map.
     */
    public void setLevels(Map<String, Level> levels) {
        this.levels = levels;
    }
}
