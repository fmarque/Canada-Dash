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


    public boolean isCorrectAnswer(String userAnswer) {

        String cleanedUserAnswer = userAnswer.replaceAll("[^A-Za-z]", "").toLowerCase();

        return switch (this.type) {
            case multipleChoice -> {
                if (!this.options.isEmpty() && !cleanedUserAnswer.isEmpty()) {

                    if (cleanedUserAnswer.length() == 1) {
                        char optionChar = cleanedUserAnswer.charAt(0);
                        if (optionChar >= 'a' && optionChar < 'a' + this.options.size()) {
                            int index = optionChar - 'a';
                            yield this.answer.equalsIgnoreCase(String.valueOf((char) ('A' + index)));
                        }
                    } else {

                        String cleanedCorrectOption = this.options.get(this.answer.charAt(0) - 'A').replaceAll("[^A-Za-z]", "").toLowerCase();
                        yield cleanedCorrectOption.equals(cleanedUserAnswer);
                    }
                }
                yield false;
            }
            case trueOrFalse -> this.answer.equalsIgnoreCase(cleanedUserAnswer);
            case fillInTheBlank -> {
                String cleanedCorrectAnswer = this.answer.replaceAll("[^A-Za-z]", "").toLowerCase();
                yield cleanedCorrectAnswer.equals(cleanedUserAnswer);
            }
        };
    }

    public String getNextHint(int hintIndex) {
        if (hintIndex >= 0 && hintIndex < this.hints.size()) {
            return this.hints.get(hintIndex);
        }
        return "No more hints available.";
    }
    public String displayQuestion() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.question);
        if ("multipleChoice".equalsIgnoreCase(String.valueOf(this.type)) && !this.options.isEmpty()) {
            char label = 'A';
            for (String option : this.options) {
                sb.append("\n\t").append(label++).append(". ").append(option);
            }
        }
        return sb.toString();
    }

}