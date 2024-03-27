package com.group47.canadadash.processing;
import com.group47.canadadash.processing.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final String question;
    private final QuestionType type;
    private  List<String> options;
    private final String answer;
    private List<String> hints;

    public Question(String question, String type, String answer){

        this.question = question;
        this.type = QuestionType.valueOf(type);
        this.answer = answer;
        this.options = new ArrayList<>();
        this.hints = new ArrayList<>();

    }


    public String getQuestion() {
        return question;
    }


    public QuestionType getType() {
        return type;
    }


    public List<String> getOptions() {
        return options;
    }


    public String getAnswer() {
        return answer;
    }


    public List<String> getHints() {
        return hints;
    }


    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

}