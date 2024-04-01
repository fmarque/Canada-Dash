package com.group47.canadadash;

import com.group47.canadadash.processing.Boulder;
import com.group47.canadadash.processing.BoulderType;
import com.group47.canadadash.processing.Level;
import com.group47.canadadash.processing.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link Level}. Verifies the functionality for setting and getting both
 * questions and boulders within a level. This ensures that levels can be dynamically
 * configured with educational content and interactive obstacles.
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version : 1.0
 * @since : 1.0
 */
class LevelTest {

    private Level level;

    /**
     * Initializes the test environment before each test method.
     * This setup includes the creation of a new Level instance for use in subsequent tests.
     */
    @BeforeEach
    void setUp() {
        level = new Level();
    }

    /**
     * Tests the ability to set a collection of questions on a level and then retrieve them.
     * Verifies that the set of questions added to the level is the same as the set retrieved,
     * ensuring the level's questions are correctly managed.
     */
    @Test
    void testSetAndGetQuestions() {
        Question question1 = new Question("Q1", "multipleChoice", "A");
        Question question2 = new Question("Q2", "trueOrFalse", "True");

        List<Question> questions = Arrays.asList(question1, question2);
        level.setQuestions(questions);

        assertEquals(questions, level.getQuestions(), "The questions set and retrieved should be the same.");
    }

    /**
     * Tests the ability to set a collection of boulders on a level and then retrieve them.
     * Verifies that the set of boulders added to the level is the same as the set retrieved,
     * ensuring the level's boulders are correctly managed and can be used to create interactive
     * obstacles within the game.
     */
    @Test
    void testSetAndGetBoulders() {
        Boulder boulder1 = new Boulder(0, 0, 10, 10, BoulderType.BOX);
        Boulder boulder2 = new Boulder(10, 10, 20, 20, BoulderType.FENCE);

        List<Boulder> boulders = Arrays.asList(boulder1, boulder2);
        level.setBoulders(boulders);

        assertEquals(boulders, level.getBoulders(), "The boulders set and retrieved should be the same.");
    }
}