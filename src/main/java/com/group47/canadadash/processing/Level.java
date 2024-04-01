package com.group47.canadadash.processing;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a level within the game or application, containing both
 * an assessment (a list of questions) and obstacles or challenges (represented as boulders).
 * <p>
 * This class serves as a container for the components that make up a level,
 * offering functionality to manage these components through getters and setters.
 * </p>
 *
 * @author Muhammad Affan Yasir
 * @version: 1.0
 * @since: 1.0
 */
public class Level {
    private List<Question> assessment;
    private List<Boulder> boulders;

    /**
     * Constructs a new Level instance with empty lists for both the assessment
     * questions and the boulders. This allows for the addition of components
     * to the level after instantiation.
     */
    public Level() {
        this.assessment = new ArrayList<>();
        this.boulders = new ArrayList<>();
    }

    /**
     * Retrieves the list of questions (assessment) for this level.
     *
     * @return A list of {@link Question} objects representing the assessment for this level.
     */
    public List<Question> getQuestions() {
        return assessment;
    }

    /**
     * Retrieves the list of boulders (obstacles or challenges) for this level.
     *
     * @return A list of {@link Boulder} objects representing the obstacles or challenges within this level.
     */
    public List<Boulder> getBoulders() {
        return boulders;
    }

    /**
     * Sets the list of questions (assessment) for this level.
     *
     * @param questions A list of {@link Question} objects to be used as the assessment for this level.
     */
    public void setQuestions(List<Question> questions) {
        this.assessment = questions;
    }

    /**
     * Sets the list of boulders (obstacles or challenges) for this level.
     *
     * @param boulders A list of {@link Boulder} objects to represent the obstacles or challenges within this level.
     */
    public void setBoulders(List<Boulder> boulders) {
        this.boulders = boulders;
    }
}