package com.group47.canadadash.processing;

public class User {
    private String userID;
    private String password;

    // Constructor
    public User(){
        this.userID = null;
        this.password = null;

    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password){
        this.password = password;
    }


}



