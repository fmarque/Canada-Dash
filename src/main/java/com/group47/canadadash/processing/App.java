package com.group47.canadadash.processing;

import java.util.ArrayList;
import java.util.List;



public class App{

    private List<Level> levels;
    User user;
    private UserContainer userContainer;

    public App() {
        this.levels = new ArrayList<>();
        this.user = null;
    }

    public void loadData() {
        String relativePathContentFile = "/data/metaData/content.json";
        String relativePathUserFIle = "/data/userData/user.json";
        String rootPath = Util.getRootPath();


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
    }

    public static void main(String[] args) {
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
    }

}
