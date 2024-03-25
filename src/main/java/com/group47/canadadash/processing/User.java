package com.group47.canadadash.processing;

import java.util.Scanner;
import org.json.*;
import com.google.gson.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;



public class User {
    private String userID;
    private String password;
    private boolean instructor;
    private String classCode;
    private int age;

    // Constructor
    public User(){
        this.userID = null;
        this.password = null;
        this.instructor = false;
        this.classCode = null;
        this.age = 0;

    }


    public void saveUser() {
        try {
            // Get the root path dynamically
            String basePath = new File(".").getCanonicalPath();
            String relativePath = "data/userData/user.json"; // Path relative to the root
            String filePath = Paths.get(basePath, relativePath).toString();

            Gson gson = new Gson();
            JsonObject users = new JsonObject();

            // Check if the file exists and read existing users into the users JsonObject
            File file = new File(filePath);
            if (file.exists()) {
                // Read the existing content into the users JsonObject
                users = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
            }

            // Add or update the current user's data
            JsonObject userDetails = new JsonObject();
            userDetails.addProperty("password", this.password);
            users.add(this.userID, userDetails);

            // Write the updated users object back to the file
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(users, writer); // Serialize the JsonObject and write it to the file
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

    public static void main(String[] args) {
        User newUser = new User();
        newUser.promptForUserInfo();
        newUser.saveUser();
    }


}



