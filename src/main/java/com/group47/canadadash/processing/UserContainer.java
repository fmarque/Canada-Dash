package com.group47.canadadash.processing;

import java.util.HashMap;
import java.util.Map;

public class UserContainer {
    private Map<String, User> users;

    public UserContainer() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        this.users.put(user.getUserID(), user);
    }

    public User getUser(String userID) {
        return this.users.get(userID);
    }

    public Map<String, User> getUsers() {
        return this.users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
