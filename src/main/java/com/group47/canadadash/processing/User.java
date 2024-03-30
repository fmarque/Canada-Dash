package com.group47.canadadash.processing;

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

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType(){
        return this.type;
    }

    public String getClassCode(){
        return this.classCode;
    }

    public int getTotalPoints() {return  this.totalPoints;}

    public int getPreviousTotalPoints() {return this.previousTotalPoints;}

    public int getHighestLevelReached() {return this.highestLevelReached;}

    public int getCheckpointX() {return checkpointX;}

    public int getCheckpointY() {return checkpointY;}

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setClassCode(String classCode){this.classCode = classCode;}

    public void setTotalPoints(int totalPoints){this.totalPoints = totalPoints;}

    public void setPreviousTotalPoints(int totalPoints){this.previousTotalPoints = totalPoints;}

    public void setHighestLevelReached(int highestLevelReached){this.highestLevelReached = highestLevelReached;}

    public void setCheckpointX(int checkpointX) {this.checkpointX = checkpointX;}

    public void setCheckpointY(int checkpointY) {this.checkpointY = checkpointY;}




}



