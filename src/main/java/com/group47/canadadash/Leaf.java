package com.group47.canadadash;

import java.util.ArrayList;

public class Leaf extends GameUnit {

    private String question;
    private ArrayList<String> options;
    private ArrayList<String> hints;
    private String rightAnswer;

    private int points;

    public Leaf(int givenX, int givenY) {
        super(givenX, givenY);
        question = "";
        rightAnswer = "";
        options = new ArrayList<>();
        hints = new ArrayList<>();
        points = 0;
        height = 20;
        width = 20;
        imageAdd = "";    //TODO: write the address which points to the leaf image
    }

    public void setQuestion(String givenQuestion) {
        question = givenQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void addOptions(String newOption){
        options.add(newOption);
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void addHint(String newHint){
        hints.add(newHint);
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

}
