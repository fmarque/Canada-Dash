package com.group47.canadadash.processing;

/**
 * Enumerates the types of questions available in the application.
 * This classification helps in managing question presentation and response validation.
 * <p>
 * This helps ensure that each question is treated appropriately based on its type,
 * whether it's evaluating responses, rendering UI components, or providing hints.
 * </p>
 *
 * @author Muhammad Affan Yasir [myasir2@uwo.ca]
 */
public enum QuestionType {
    /**
     * Represents questions that require the user to fill in a missing piece of information.
     * The answer to these questions is usually a word or a short phrase.
     */
    fillInTheBlank,

    /**
     * Represents questions that provide the user with a set of predefined options.
     * The user must select the correct option(s) from the available choices.
     */
    multipleChoice,

    /**
     * Represents questions that have a binary response: true or false.
     * The user must decide whether the statement presented is correct or incorrect.
     */
    trueOrFalse
}