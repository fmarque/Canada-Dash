package com.group47.canadadash.processing;


import java.util.ArrayList;
import java.util.List;


public class Level {
    private List<Question> assessment;
    private List<Boulder> boulders;

    public Level() {
        this.assessment = new ArrayList<>();
        this.boulders = new ArrayList<>();
    }

    // Getter and Setter
    public List<Question> getQuestions() {
        return assessment;
    }

    public List<Boulder> getBoulders(){return boulders;}

    public void setQuestions(List<Question> questions) {
        this.assessment = questions;
    }

    public void setBoulders(List<Boulder> boulders) {
        this.boulders = boulders;
    }


}