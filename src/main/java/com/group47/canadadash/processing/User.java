package com.group47.canadadash.processing;

public class User {
    private String userID;
    private String password;
    private String type;
    private String classCode;

    // Constructor
    public User(){
        this.userID = null;
        this.password = null;
        this.type = null;
        this.classCode = null;

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


}



