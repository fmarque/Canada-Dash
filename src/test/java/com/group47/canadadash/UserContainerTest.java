package com.group47.canadadash;

import com.group47.canadadash.processing.User;
import com.group47.canadadash.processing.UserContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for {@link UserContainer}. Ensures that user management operations such as
 * adding, replacing, and retrieving users function as expected.
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version: 1.0
 * @since: 1.0
 */
class UserContainerTest {

    private UserContainer userContainer;
    private User user1;
    private User user2;


    /**
     * Sets up the test environment before each test method.
     * Initializes a new UserContainer and two User instances with distinct properties.
     */
    @BeforeEach
    void setUp() {
        userContainer = new UserContainer();
        user1 = new User();
        user2 = new User();

        user1.setUserID("user1");
        user1.setPassword("pass1");
        user1.setType("student");

        user2.setUserID("user2");
        user2.setPassword("pass2");
        user2.setType("instructor");
    }


    /**
     * Tests adding users to the UserContainer and retrieving them by their userID.
     * Verifies that the retrieved user matches the one that was added.
     */
    @Test
    void addUserAndGetUser() {
        userContainer.addUser(user1);
        assertEquals(user1, userContainer.getUser("user1"), "The retrieved user should match the added user.");

        userContainer.addUser(user2);
        assertEquals(user2, userContainer.getUser("user2"), "The retrieved user should match the added user.");
    }

    /**
     * Tests the replacement of an existing user in the UserContainer.
     * Verifies that the new user information replaces the old one.
     */
    @Test
    void replaceExistingUser() {
        userContainer.addUser(user1);

        User modifiedUser1 = new User();
        modifiedUser1.setUserID("user1");
        modifiedUser1.setPassword("newPass");
        modifiedUser1.setType("admin");

        userContainer.addUser(modifiedUser1);
        assertEquals(modifiedUser1, userContainer.getUser("user1"), "The modified user should replace the original.");
        assertNotEquals(user1.getPassword(), userContainer.getUser("user1").getPassword(), "The password should have been updated.");
    }

    /**
     * Tests retrieving a user that does not exist in the UserContainer.
     * Verifies that the method returns null.
     */
    @Test
    void getUserThatDoesNotExist() {
        assertNull(userContainer.getUser("nonexistent"), "Retrieving a non-existing user should return null.");
    }

    /**
     * Tests setting a new collection of users and retrieving it from the UserContainer.
     * Verifies that the UserContainer's users map is updated with the new collection.
     */
    @Test
    void setUsersAndGetUsers() {
        Map<String, User> newUsers = new HashMap<>();
        newUsers.put(user1.getUserID(), user1);
        newUsers.put(user2.getUserID(), user2);

        userContainer.setUsers(newUsers);
        assertEquals(newUsers, userContainer.getUsers(), "The users map should be updated with the new collection.");
    }
}
