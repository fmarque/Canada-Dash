package com.group47.canadadash;

import com.group47.canadadash.processing.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
/**
 * Test class for {@link Question}. Ensures that various types of questions (multiple choice,
 * true or false, and fill in the blank) are correctly evaluated for correct answers, and
 * that hints are properly retrieved.
 *
 * @author: Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version: 1.0
 * @since: 1.0
 */
class QuestionTest {

    private Question multipleChoiceQuestion;
    private Question trueOrFalseQuestion;
    private Question fillInTheBlankQuestion;

    /**
     * Sets up the test environment before each test method.
     * Initializes question instances for multiple choice, true or false, and fill in the blank questions
     * with specific questions and answers.
     */
    @BeforeEach
    void setUp() {
        multipleChoiceQuestion = new Question("What is the capital of France?", "multipleChoice", "A");
        multipleChoiceQuestion.setOptions(Arrays.asList("A) Paris", "B) London", "C) Berlin", "D) Madrid"));

        trueOrFalseQuestion = new Question("The sky is blue.", "trueOrFalse", "True");

        fillInTheBlankQuestion = new Question("Complete this sentence: The Earth revolves around the ____.", "fillInTheBlank", "Sun");
    }

    /**
     * Tests the isCorrectAnswer method for a multiple choice question.
     * Verifies that the method correctly identifies the right and wrong answers.
     */
    @Test
    void testIsCorrectAnswerMultipleChoice() {
        assertTrue(multipleChoiceQuestion.isCorrectAnswer("A"));
        assertFalse(multipleChoiceQuestion.isCorrectAnswer("B"));
    }

    /**
     * Tests the isCorrectAnswer method for a true or false question.
     * Verifies that the method correctly identifies the right and wrong answers.
     */
    @Test
    void testIsCorrectAnswerTrueOrFalse() {
        assertTrue(trueOrFalseQuestion.isCorrectAnswer("True"));
        assertFalse(trueOrFalseQuestion.isCorrectAnswer("False"));
    }

    /**
     * Tests the isCorrectAnswer method for a fill in the blank question.
     * Verifies that the method correctly identifies the right and wrong answers.
     */
    @Test
    void testIsCorrectAnswerFillInTheBlank() {
        assertTrue(fillInTheBlankQuestion.isCorrectAnswer("Sun"));
        assertFalse(fillInTheBlankQuestion.isCorrectAnswer("Moon"));
    }

    /**
     * Tests the getNextHint method for a fill in the blank question.
     * Verifies that the method returns the correct hints in sequence and notifies when
     * no more hints are available.
     */
    @Test
    void testGetNextHint() {
        fillInTheBlankQuestion.setHints(Arrays.asList("It is not the Moon", "It lights up our day"));
        assertEquals("It is not the Moon", fillInTheBlankQuestion.getNextHint(0));
        assertEquals("It lights up our day", fillInTheBlankQuestion.getNextHint(1));
        assertEquals("No more hints available.", fillInTheBlankQuestion.getNextHint(2));
    }
}