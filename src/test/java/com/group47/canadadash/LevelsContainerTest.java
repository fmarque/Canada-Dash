package com.group47.canadadash;

import com.group47.canadadash.processing.Level;
import com.group47.canadadash.processing.LevelsContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for {@link LevelsContainer}. This class tests the functionality of adding,
 * retrieving, and modifying levels within a container that manages multiple game levels.
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version : 1.0
 * @since : 1.0
 */
class LevelsContainerTest {

    private LevelsContainer container;

    /**
     * Initializes the test environment before each test method.
     * This includes creating a new LevelsContainer instance for testing.
     */
    @BeforeEach
    void setUp() {
        container = new LevelsContainer();
    }

    /**
     * Tests the addition of levels to the LevelsContainer and retrieving them.
     * Verifies that levels can be added to the container and then retrieved both
     * as a whole map and individually by their level IDs, ensuring the container
     * accurately tracks and provides access to its managed levels.
     */
    @Test
    void testAddAndGetLevels() {
        Level level1 = new Level(); // Assuming default constructor sets up an empty level.
        Level level2 = new Level();

        Map<String, Level> levelsMap = new HashMap<>();
        levelsMap.put("level1", level1);
        levelsMap.put("level2", level2);

        // Test setting the levels map
        container.setLevels(levelsMap);

        // Test getting the entire levels map
        assertEquals(levelsMap, container.getLevels(), "The entire levels map should be retrievable.");

        // Test retrieving individual levels by ID
        assertEquals(level1, container.getLevels().get("level1"), "Level1 should be retrievable by its ID.");
        assertEquals(level2, container.getLevels().get("level2"), "Level2 should be retrievable by its ID.");
    }

    /**
     * Tests the immutability of the levels map within the LevelsContainer after it has been set.
     * Ensures that changes to the original map used to set the container's levels do not affect
     * the internal state of the LevelsContainer, verifying the encapsulation of the container's data.
     */
    @Test
    void testLevelsMapModification() {
        Level level1 = new Level();
        Map<String, Level> initialMap = new HashMap<>();
        initialMap.put("level1", level1);
        container.setLevels(initialMap);

        // Modify the map after setting
        Level level2 = new Level();
        initialMap.put("level2", level2); // This modification happens outside the LevelsContainer

        assertNull(container.getLevels().get("level2"), "Adding to the initialMap after setLevels should not affect the container's map.");
    }
}

