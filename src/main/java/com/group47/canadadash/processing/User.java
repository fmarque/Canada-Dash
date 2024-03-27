package com.group47.canadadash.processing;

import java.util.Scanner;
import com.google.gson.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;



public class User {
    private String userID;
    private String password;


    // Constructor
    public User(){
        this.userID = null;
        this.password = null;

    }


    public void saveUser() {
        try {
            String basePath = new File(".").getCanonicalPath();
            String relativePath = "/data/userData/user.json";
            String filePath = Paths.get(basePath, relativePath).toString();

            Gson gson = new Gson();
            JsonObject users = new JsonObject();

            File file = new File(filePath);
            if (file.exists()) {
                users = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
            }

            JsonObject userDetails = new JsonObject();
            userDetails.addProperty("password", this.password);
            users.add(this.userID, userDetails);

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(users, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void promptForUserInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your userID: ");
        this.userID = scanner.nextLine();

        System.out.print("Enter your password: ");
        this.password = scanner.nextLine();

    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public static void main(String[] args) {
        User newUser = new User();
        newUser.promptForUserInfo();
        newUser.saveUser();
    }


}



