package com.group47.canadadash;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getTotalPoints() {
        System.out.println("getTotalPoints()");
        GameState state = new GameState();
        int result = state.getTotalPoints();
        int expResult = 0;
        assertEquals(expResult, result);
    }

    @Test
    void decreasePoints() {
        System.out.println("decreasePoints()");
        GameState state = new GameState();
        state.increasePoints(300);
        state.decreasePoints(50);
        int result = state.getTotalPoints();
        int expResult = 250;
        assertEquals(expResult, result);
    }

    @Test
    void increasePoints() {
        System.out.println("increasePoints()");
        GameState state = new GameState();
        state.increasePoints(100);
        int result = state.getTotalPoints();
        int expResult = 100;
        assertEquals(expResult, result);
    }

    @Test
    void getLives() {
        System.out.println("getLives()");
        GameState state = new GameState();
        int expResult = 5;
        int result = state.getLives();
        assertEquals(expResult, result);
    }

    @Test
    void looseLife() {
        System.out.println("looseLife()");
        GameState state = new GameState();
        int expResult = 4;
        state.loseLife();
        int result = state.getLives();
        assertEquals(expResult, result);
    }


    @Test
    void getLeaves() {
        System.out.println("getLeaves()");
        GameState state = new GameState();
        int expResult = 0;
        int result = state.getLeaves().size();
        assertEquals(expResult, result);
    }

    @Test
    void addLeaves() {
        System.out.println("addLeaves()");
        GameState state = new GameState();
        state.addLeaves(new Leaf(50, 50));
        int expResult = 1;
        int result = state.getLeaves().size();
        assertEquals(expResult, result);
    }

    @Test
    void removeLeaf() {
        System.out.println("removeLeaf()");
        GameState state = new GameState();
        state.addLeaves(new Leaf(50, 50));
        Leaf tmp = new Leaf(30, 30);
        state.addLeaves(tmp);
        state.removeLeaf(tmp);
        int expResult = 1;
        int result = state.getLeaves().size();
        assertEquals(expResult, result);
    }

    @Test
    void getBoxes() {
        System.out.println("getBoxes()");
        GameState state = new GameState();
        int expResult = 0;
        int result = state.getBoxes().size();
        assertEquals(expResult, result);
    }

    @Test
    void addBox() {
        System.out.println("addBox()");
        GameState state = new GameState();
        state.addBox(new Box(50, 50));
        int expResult = 1;
        int result = state.getBoxes().size();
        assertEquals(expResult, result);
    }

    @Test
    void removeBox() {
        System.out.println("removeBox()");
        GameState state = new GameState();
        state.addBox(new Box(50, 50));
        Box tmp = new Box(30, 30);
        state.addBox(tmp);
        state.removeBox(tmp);
        int expResult = 1;
        int result = state.getBoxes().size();
        assertEquals(expResult, result);
    }

    @Test
    void getFences() {
        System.out.println("getFences()");
        GameState state = new GameState();
        int expResult = 0;
        int result = state.getFences().size();
        assertEquals(expResult, result);
    }

    @Test
    void addFence() {
        System.out.println("addFence()");
        GameState state = new GameState();
        state.addFence(new Fence(50, 50));
        int expResult = 1;
        int result = state.getFences().size();
        assertEquals(expResult, result);
    }

    @Test
    void removeFence() {
        System.out.println("removeFence()");
        GameState state = new GameState();
        state.addFence(new Fence(50, 50));
        Fence tmp = new Fence(30, 30);
        state.addFence(tmp);
        state.removeFence(tmp);
        int expResult = 1;
        int result = state.getFences().size();
        assertEquals(expResult, result);
    }


    @Test
    void setProvince() {
        System.out.println("setProvince()");
        GameState state = new GameState();
        state.setProvince("BC");
        String result = state.getProvince();
        String expResult = "BC";
        assertEquals(expResult, result);
    }

    @Test
    void getProvince() {
        System.out.println("setProvince()");
        GameState state = new GameState();
        state.setProvince("ON");
        String result = state.getProvince();
        String expResult = "ON";
        assertEquals(expResult, result);
    }


    @Test
    void isPause() {
        System.out.println("isPause()");
        GameState state = new GameState();
        state.setPause(false);
        boolean result = state.isPause();
        assertFalse(result);
    }

    @Test
    void setPause() {
        System.out.println("setPause()");
        GameState state = new GameState();
        state.setPause(true);
        boolean result = state.isPause();
        assertTrue(result);
    }
}