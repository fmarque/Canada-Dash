package com.group47.canadadash;
import com.group47.canadadash.processing.Question;
/**
 * This class represents a leaf object in the game, which consists of the
 * question itself, the right answer, hints, and options
 * leaf has both x, and y location
 * width, and height dimension
 * and an image address pointing to its relevant img file
 */
public class Leaf extends GameUnit {

    private Question ques;
    private int points;

    /**
     * Initializing the height, width and imageAdd of leaf
     * @param givenX the x location to be set for leaf
     * @param givenY the y location to be set for leaf
     */
    public Leaf(int givenX, int givenY) {
        super(givenX, givenY);
        ques = new Question("", "", "");
        points = 100;
        height = 20;
        width = 20;
        imageAdd = "src/main/resources/images/leaf.png";
    }

    /**
     * getter of attribute points
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * so it can modify later
     * @return the question object
     */
    public Question getQues() {
        return ques;
    }

}
