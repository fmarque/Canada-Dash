package com.group47.canadadash;

import com.group47.canadadash.processing.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for {@link User}.
 * Verifies the correct behavior of getters and setters for all user attributes.
 * Each test case focuses on a single attribute, ensuring its correct manipulation
 * through its respective setter and retrieval via its getter.
 *
 * @author : Muhammad Affan Yasir
 * @version: 1.0
 * @since : 1.0
 */
class UserTest {

    private User user;


    /**
     * Setup method to initialize a {@link User} object before each test.
     */
    @BeforeEach
    void setUp() {
        user = new User();
    }

    /**
     * Tests setting and getting the user ID.
     */
    @Test
    void testUserID() {
        String expectedUserID = "user123";
        user.setUserID(expectedUserID);
        assertEquals(expectedUserID, user.getUserID(), "User ID does not match.");
    }

    /**
     * Tests setting and getting the user's password.
     */
    @Test
    void testPassword() {
        String expectedPassword = "password";
        user.setPassword(expectedPassword);
        assertEquals(expectedPassword, user.getPassword(), "Password does not match.");
    }

    /**
     * Tests setting and getting the user's type.
     */
    @Test
    void testType() {
        String expectedType = "student";
        user.setType(expectedType);
        assertEquals(expectedType, user.getType(), "User type does not match.");
    }

    /**
     * Tests setting and getting the class code associated with the user.
     */
    @Test
    void testClassCode() {
        String expectedClassCode = "code123";
        user.setClassCode(expectedClassCode);
        assertEquals(expectedClassCode, user.getClassCode(), "Class code does not match.");
    }

    /**
     * Tests setting and getting the total points scored by the user.
     */
    @Test
    void testTotalPoints() {
        int expectedTotalPoints = 100;
        user.setTotalPoints(expectedTotalPoints);
        assertEquals(expectedTotalPoints, user.getTotalPoints(), "Total points do not match.");
    }

    /**
     * Tests setting and getting the total points scored by the user before the current session.
     */
    @Test
    void testPreviousTotalPoints() {
        int expectedPreviousTotalPoints = 50;
        user.setPreviousTotalPoints(expectedPreviousTotalPoints);
        assertEquals(expectedPreviousTotalPoints, user.getPreviousTotalPoints(), "Previous total points do not match.");
    }

    /**
     * Tests setting and getting the highest level reached by the user.
     */
    @Test
    void testHighestLevelReached() {
        int expectedHighestLevelReached = 10;
        user.setHighestLevelReached(expectedHighestLevelReached);
        assertEquals(expectedHighestLevelReached, user.getHighestLevelReached(), "Highest level reached does not match.");
    }

    /**
     * Tests setting and getting the X coordinate of the last checkpoint reached by the user.
     */
    @Test
    void testCheckpointX() {
        int expectedCheckpointX = 5;
        user.setCheckpointX(expectedCheckpointX);
        assertEquals(expectedCheckpointX, user.getCheckpointX(), "Checkpoint X does not match.");
    }

    /**
     * Tests setting and getting the Y coordinate of the last checkpoint reached by the user.
     */
    @Test
    void testCheckpointY() {
        int expectedCheckpointY = 10;
        user.setCheckpointY(expectedCheckpointY);
        assertEquals(expectedCheckpointY, user.getCheckpointY(), "Checkpoint Y does not match.");
    }
}
