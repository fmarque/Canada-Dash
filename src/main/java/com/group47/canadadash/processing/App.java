package com.group47.canadadash.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.Scanner;

public class App{

    final String relativePathContentFile = "/data/metaData/content.json";
    final String relativePathUserFIle = "/data/userData/user.json";
    final String rootPath = Util.getRootPath();

    private List<Level> levels;

    public User user;
    private UserContainer userContainer;
    private Set<String> instructorClassCodes;
    private List<User> studentsForInstructor;


    public App() {
        this.levels = new ArrayList<>();
        this.user = null;
        this.instructorClassCodes = new HashSet<>();
    }

    public void loadData() {

        LevelsContainer levelsContainer = Util.readJsonFromFile(rootPath + relativePathContentFile, LevelsContainer.class);
        UserContainer userContainer = Util.readJsonFromFile(rootPath + relativePathUserFIle, UserContainer.class);

        if (levelsContainer != null && levelsContainer.getLevels() != null) {

            System.out.println("Levels loaded: " + levelsContainer.getLevels().size());
            this.levels = new ArrayList<>(levelsContainer.getLevels().values());

        } else {
            System.out.println("No levels were loaded from JSON.");
        }

        if (userContainer != null && userContainer.getUsers() != null) {
            System.out.println("Users loaded: " + userContainer.getUsers().size());

            this.userContainer = userContainer;

        } else {
            System.out.println("No users were loaded from JSON.");
        }

        if (userContainer != null && userContainer.getUsers() != null) {
            this.instructorClassCodes.clear();
            userContainer.getUsers().values().forEach(user -> {
                if (user != null && "instructor".equalsIgnoreCase(user.getType()) && user.getClassCode() != null && !user.getClassCode().isEmpty()) {
                    this.instructorClassCodes.add(user.getClassCode());
                }
            });
        }
    }

    public Boolean createAccount(String username, String password, String type) {

        if (this.userContainer.getUsers().containsKey(username)) {
            return false;
        } else {

            User newUser = new User();
            newUser.setUserID(username);
            newUser.setPassword(password);
            newUser.setType(type);

            if ("instructor".equalsIgnoreCase(type)) {
                String classCode = generateClassCode();
                newUser.setClassCode(classCode);
                this.instructorClassCodes.add(newUser.getClassCode());
            }

            this.user = newUser;
            this.userContainer.addUser(newUser);

            return true;
        }
    }

    private String generateClassCode() {

        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        String numberString = String.valueOf(number);
        char firstChar = (char) ('a' + random.nextInt(26));
        StringBuilder classCode = new StringBuilder().append(firstChar);

        for (int i = 0; i < numberString.length(); i++) {
            classCode.append(numberString.charAt(i));
            if (random.nextBoolean()) {
                char randomChar = (char) ('a' + random.nextInt(26));
                classCode.append(randomChar);
            }
        }

        return classCode.length() > 6 ? classCode.substring(0, 6) : classCode.toString();
    }

    public Boolean signIn(String username, String password, String type){

        User existingUser = this.userContainer.getUser(username);
        if (existingUser != null && existingUser.getPassword().equals(password) && existingUser.getType().equalsIgnoreCase(type)) {
            this.user = existingUser;
            return true;
        }

        return false;

    }

   public void userSave() {

        if (user == null){
            return;
        }

       this.userContainer.addUser(this.user);
       Util.writeToFile(this.userContainer, rootPath + relativePathUserFIle);

   }

    public void updatePoints(int currentPoints) {
        if (user == null) {
            return;
        }

        int newTotalPoints = user.getPreviousTotalPoints() + currentPoints;

        if (newTotalPoints > user.getTotalPoints()) {
            user.setPreviousTotalPoints(user.getTotalPoints());
            user.setTotalPoints(newTotalPoints);
        }
    }

    public boolean isValidClassCode(String classCode) {
        return instructorClassCodes.contains(classCode);
    }

    public boolean[] getUnlockedLevelsStatus() {
        if (user == null || levels.isEmpty()) {
            return new boolean[0];
        }

        boolean[] unlockedStatus = new boolean[levels.size()];
        int highestLevelReached = user.getHighestLevelReached();

        for (int i = 0; i < levels.size(); i++) {
            unlockedStatus[i] = i <= highestLevelReached;
        }

        return unlockedStatus;
    }

    public void updateStudentsForLoggedInInstructor() {
        this.studentsForInstructor = new ArrayList<>();

        if (this.user != null && "instructor".equalsIgnoreCase(this.user.getType()) && this.user.getClassCode() != null) {
            String instructorClassCode = this.user.getClassCode();

            for (User potentialStudent : this.userContainer.getUsers().values()) {
                if ("student".equalsIgnoreCase(potentialStudent.getType()) && instructorClassCode.equals(potentialStudent.getClassCode())) {
                    this.studentsForInstructor.add(potentialStudent);
                }
            }
        }
    }

    public List<Level> getLevels() {
        return levels;
    }


    public static void main(String[] args) {
        //testing purposes for now, formal testing files will be developed later
        Scanner scanner = new Scanner(System.in);
        App app = new App();
        app.loadData();

        if (app.levels != null && !app.levels.isEmpty()) {
            int levelNum = 1;
            for (Level level : app.levels) {
                System.out.println("Level " + levelNum + ":");
                if (level.getQuestions() != null && !level.getQuestions().isEmpty()) {
                    int questionNum = 1;
                    for (Question question : level.getQuestions()) {
                        System.out.println("\tQuestion " + questionNum + ": " + question.getQuestion());
                        System.out.println("\tType: " + question.getType());
                        System.out.println("\tAnswer: " + question.getAnswer());
                        System.out.println("\tOptions: " + (question.getOptions() != null ? String.join(", ", question.getOptions()) : "No options"));
                        System.out.println("\tHints: " + (question.getHints() != null ? String.join(", ", question.getHints()) : "No hints"));
                        questionNum++;
                    }
                } else {
                    System.out.println("\tNo questions in this level.");
                }
                levelNum++;
            }
        } else {
            System.out.println("No levels loaded.");
        }

        System.out.println("Welcome! Please choose an option:");
        System.out.println("1: Sign Up");
        System.out.println("2: Sign In");
        System.out.print("Option (1/2): ");
        String option = scanner.nextLine();

        if ("1".equals(option)) {

            System.out.print("Create a username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Create a password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Are you an instructor or a student? (instructor/student): ");
            String type = scanner.nextLine().toLowerCase();

            boolean accountCreated = false;
            if ("instructor".equals(type)) {
                accountCreated = app.createAccount(newUsername, newPassword, type);
                System.out.println(accountCreated ? "Instructor account created successfully. Your class code is: " + app.user.getClassCode() : "Account creation failed.");
            } else if ("student".equals(type)) {
                System.out.print("Enter class code (if you have one): ");
                String classCode = scanner.nextLine();

                // Check if the class code is valid or if the student doesn't have one
                if (classCode.isEmpty() || app.isValidClassCode(classCode)) {
                    accountCreated = app.createAccount(newUsername, newPassword, type);
                    app.user.setClassCode(classCode);
                    System.out.println(accountCreated ? "Student account created successfully." : "Account creation failed.");
                } else {
                    System.out.println("Invalid class code. Account creation failed.");
                }
            } else {
                System.out.println("Invalid type selected. Account creation failed.");
            }

            if (accountCreated) {
                app.userSave();
            }
        } else if ("2".equals(option)) {

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            boolean success = app.signIn(username, password, "student");
            System.out.println(success ? "Sign-in successful." : "Sign-in failed.");
            app.userSave();
        } else {
            System.out.println("Invalid option selected.");
        }

        scanner.close();
    }

}
