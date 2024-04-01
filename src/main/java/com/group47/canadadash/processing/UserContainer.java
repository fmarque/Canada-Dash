package com.group47.canadadash.processing;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a container for managing users within the application.
 * It encapsulates a collection of {@link User} objects, allowing for
 * adding, retrieving, and managing users by their ID.
 * <p>
 * This class serves as a central point for user management, offering
 * methods to add new users, fetch a user by ID, and access the entire
 * collection of users.
 * </p>
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version : 1.0
 * @since : 1.0
 */
public class UserContainer {
    private Map<String, User> users;

    /**
     * Constructs a new, empty UserContainer.
     */
    public UserContainer() {
        this.users = new HashMap<>();
    }

    /**
     * Adds a {@link User} to the container. If a user with the same ID
     * already exists, it will be replaced.
     *
     * @param user The {@link User} to add to the container.
     */
    public void addUser(User user) {
        this.users.put(user.getUserID(), user);
    }

    /**
     * Retrieves a {@link User} by their unique ID.
     *
     * @param userID The ID of the user to retrieve.
     * @return The {@link User} associated with the given ID, or {@code null} if
     *         no such user exists in the container.
     */
    public User getUser(String userID) {
        return this.users.get(userID);
    }

    /**
     * Provides access to the entire collection of users.
     *
     * @return A {@link Map} representing the collection of users, keyed by user ID.
     */
    public Map<String, User> getUsers() {
        return this.users;
    }

    /**
     * Sets the internal collection of users to the provided map.
     * <p>
     * This method can be used to replace the entire set of users with
     * a new collection.
     * </p>
     *
     * @param users A {@link Map} representing the new collection of users, keyed by user ID.
     */
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}

