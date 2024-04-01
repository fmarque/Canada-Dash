package com.group47.canadadash;

import com.group47.canadadash.processing.Boulder;
import com.group47.canadadash.processing.BoulderType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Boulder}. This class verifies the initialization and property
 * setting of the Boulder objects, ensuring they correctly represent obstacles within
 * a game environment with specified dimensions, location, and type.
 */
class BoulderTest {

    /**
     * Tests the initialization of a {@link Boulder} object to verify that all provided
     * properties (x-coordinate, y-coordinate, height, width, and type) are correctly
     * set upon creation. It also checks that the image path associated with the boulder's
     * type matches an expected value, ensuring the boulder's visual representation can
     * be correctly located and rendered.
     *
     * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
     * @version: 1.0
     * @since : 1.0
     */
    @Test
    void boulderInitialization() {
        // Define the properties for the test boulder
        int x = 100;
        int y = 200;
        int height = 50;
        int width = 30;
        BoulderType type = BoulderType.BOX; // Assuming BOX is a valid enum value

        // Create a new boulder instance with the properties
        Boulder boulder = new Boulder(x, y, height, width, type);

        // Assert that the properties were correctly set
        assertEquals(x, boulder.x, "The x-coordinate does not match.");
        assertEquals(y, boulder.y, "The y-coordinate does not match.");
        assertEquals(height, boulder.height, "The height does not match.");
        assertEquals(width, boulder.width, "The width does not match.");
        assertEquals(type, boulder.type, "The boulder type does not match.");

        /*
        Assuming BoulderType.BOX.getImagePath() returns a known, fixed path
        such as "src/main/resources/com/group47/canadadash/object.png"
        */
        String expectedImagePath = "src/main/resources/com/group47/canadadash/object.png";
        assertEquals(expectedImagePath, boulder.getImagePath(), "The image path does not match.");
    }
}
