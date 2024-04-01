package com.group47.canadadash.processing;
import com.group47.canadadash.processing.QuestionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a question in the application, containing all necessary
 * details such as the question text, type, options (if applicable),
 * correct answer, and hints.
 * <p>
 * This class supports different types of questions like multiple choice,
 * true or false, and fill in the blank, facilitating versatile quiz or
 * educational game development.
 * </p>
 *
 * @author Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version 1.0
 * @since 1.0
 */
public class Question {

    private final String question;
    private final QuestionType type;
    private  List<String> options;
    private final String answer;
    private List<String> hints;


    /**
     * Constructs a new Question with the specified details.
     *
     * @param question The text of the question.
     * @param type     The type of the question (e.g., "multipleChoice", "trueOrFalse", "fillInTheBlank").
     * @param answer   The correct answer to the question.
     */
    public Question(String question, String type, String answer){

        this.question = question;
        this.type = QuestionType.valueOf(type);
        this.answer = answer;
        this.options = new ArrayList<>();
        this.hints = new ArrayList<>();

    }


    // Getters

    /**
     * Gets the question text.
     *
     * @return The text of the question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the type of the question.
     *
     * @return The type of the question.
     */
    public QuestionType getType() {
        return type;
    }

    /**
     * Gets the list of options for this question.
     * This is primarily relevant for multiple choice questions.
     *
     * @return The list of options.
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Gets the correct answer for the question.
     *
     * @return The correct answer.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Gets the list of hints for this question.
     *
     * @return The list of hints.
     */
    public List<String> getHints() {
        return hints;
    }

    // Setters

    /**
     * Sets the list of options for this question.
     * This is primarily used for multiple choice questions.
     *
     * @param options The list of options to be set.
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Sets the list of hints for this question.
     *
     * @param hints The list of hints to be set.
     */
    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    /**
     * Determines if a given answer is correct for this question.
     *
     * @param userAnswer The answer provided by the user.
     * @return {@code true} if the answer is correct, {@code false} otherwise.
     */
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

    /**
     * Retrieves the next hint for this question based on the given index.
     *
     * @param hintIndex The index of the hint to retrieve.
     * @return The hint text or a message indicating no more hints are available.
     */
    public String getNextHint(int hintIndex) {
        if (hintIndex >= 0 && hintIndex < this.hints.size()) {
            return this.hints.get(hintIndex);
        }
        return "No more hints available.";
    }

    /**
     * Formats and displays the question along with its options (if applicable).
     *
     * @return A formatted string representation of the question and its options.
     */
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