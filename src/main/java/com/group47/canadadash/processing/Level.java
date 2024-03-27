package com.group47.canadadash.processing;


import java.util.ArrayList;
import java.util.List;


public class Level {
    private List<Question> assessment;

    public Level() {
        this.assessment = new ArrayList<>();
    }

    // Getter and Setter
    public List<Question> getQuestions() {
        return assessment;
    }

    public void setQuestions(List<Question> questions) {
        this.assessment = questions;
    }
}