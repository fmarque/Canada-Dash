package com.group47.canadadash.processing;

/**
 * Represents a user within the application, encapsulating user-specific information
 * such as credentials, role, progress, and performance metrics.
 * <p>
 * This class is designed to hold user details including their ID, password, type (role),
 * class code for instructors, total points scored, highest level reached in the game,
 * and the coordinates of the last checkpoint reached.
 * </p>
 *
 * @author Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version 1.0
 * @since 1.0
 */


public class User {
    private String userID;
    private String password;
    private String type;
    private String classCode;
    private int totalPoints;
    private int previousTotalPoints;
    private int highestLevelReached;
    private int currentCheckpoint;
    private int checkpointX;
    private int checkpointY;

    // Constructor
    /**
     * Constructs a new User with default values.
     * All string fields are initialized to {@code null} and integers to 0.
     */
    public User(){
        this.userID = null;
        this.password = null;
        this.type = null;
        this.classCode = null;
        this.totalPoints = 0;
        this.previousTotalPoints = 0;
        this.highestLevelReached = 0;
        this.checkpointX = 0;
        this.checkpointY = 0;
    }

    // Getter and setter methods follow

    /**
     * Gets the user's ID.
     *
     * @return The user's unique identifier.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the user's ID.
     *
     * @param userID The unique identifier for the user.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's type (role).
     *
     * @return The type of the user, indicating their role within the application.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the user's type (role).
     *
     * @param type The type indicating the user's role.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the class code associated with the user (if applicable).
     *
     * @return The class code for the user, primarily relevant for instructors.
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Sets the class code for the user.
     *
     * @param classCode The class code to be associated with the user.
     */
    /**
     * Gets the total points scored by the user.
     *
     * @return The total points scored.
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Sets the total points scored by the user.
     *
     * @param totalPoints The total points to be set.
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * Gets the total points scored by the user before the current session.
     *
     * @return The total points scored before the current session.
     */
    public int getPreviousTotalPoints() {
        return previousTotalPoints;
    }

    /**
     * Sets the total points scored by the user before the current session.
     *
     * @param previousTotalPoints The total points before the current session.
     */
    public void setPreviousTotalPoints(int previousTotalPoints) {
        this.previousTotalPoints = previousTotalPoints;
    }

    /**
     * Gets the highest level reached by the user.
     *
     * @return The highest level reached.
     */
    public int getHighestLevelReached() {
        return highestLevelReached;
    }

    /**
     * Sets the highest level reached by the user.
     *
     * @param highestLevelReached The highest level to be set.
     */
    public void setHighestLevelReached(int highestLevelReached) {
        this.highestLevelReached = highestLevelReached;
    }

    /**
     * Gets the X coordinate of the last checkpoint reached by the user.
     *
     * @return The X coordinate of the last checkpoint.
     */
    public int getCheckpointX() {
        return checkpointX;
    }

    /**
     * Sets the X coordinate of the last checkpoint reached by the user.
     *
     * @param checkpointX The X coordinate of the checkpoint.
     */
    public void setCheckpointX(int checkpointX) {
        this.checkpointX = checkpointX;
    }

    /**
     * Gets the Y coordinate of the last checkpoint reached by the user.
     *
     * @return The Y coordinate of the last checkpoint.
     */
    public int getCheckpointY() {
        return checkpointY;
    }

    /**
     * Sets the Y coordinate of the last checkpoint reached by the user.
     *
     * @param checkpointY The Y coordinate of the checkpoint.
     */
    public void setCheckpointY(int checkpointY) {
        this.checkpointY = checkpointY;
    }

    /**
     * Sets the class code associated with the user
     *
     * @param classCode
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}



