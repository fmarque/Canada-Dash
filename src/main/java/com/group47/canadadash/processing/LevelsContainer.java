package com.group47.canadadash.processing;

import com.group47.canadadash.processing.Level;

import java.util.HashMap;
import java.util.Map;


public class LevelsContainer {
    private Map<String, Level> levels;

    // Getters and Setters
    public Map<String, Level> getLevels() {
        return levels;
    }

    public void setLevels(Map<String, Level> levels) {
        this.levels = levels;
    }
}
