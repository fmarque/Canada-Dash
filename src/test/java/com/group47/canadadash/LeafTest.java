package com.group47.canadadash;

import com.group47.canadadash.processing.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeafTest {

    @Test
    void getPoints() {
        System.out.println("getPoints");
        Leaf leaf = new Leaf(50, 50);
        int expResult = 100;
        int result = leaf.getPoints();
        assertEquals(expResult, result);
    }

    @Test
    void getQues() {
        System.out.println("getQues");
        Leaf leaf = new Leaf(50, 50);
        Question question = new Question("", "", "");
        assertEquals(question, leaf.getQues());
    }
}