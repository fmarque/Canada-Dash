package com.group47.canadadash.processing;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.stream.Collectors;


public class App{

    private List<Level> levels;
    User user;

    public App() {
        this.levels = new ArrayList<>();
        this.user = null;
    }

    public void loadData() {
        String relativePath = "data/metaData/content.json";

        LevelsContainer levelsContainer = Util.readJsonFromFile(Util.getRootPath() + relativePath, LevelsContainer.class);

        if (levelsContainer != null && levelsContainer.getLevels() != null) {

            System.out.println("Levels loaded: " + levelsContainer.getLevels().size());
            this.levels = new ArrayList<>(levelsContainer.getLevels().values());

        } else {
            System.out.println("No levels were loaded from JSON.");
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
