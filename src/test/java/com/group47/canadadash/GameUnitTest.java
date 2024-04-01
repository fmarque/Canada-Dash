package com.group47.canadadash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUnitTest {

    @Test
    void getX() {
        System.out.println("getX");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        int result = tmp.getX();
        assertEquals(expResult, result);
    }

    @Test
    void getY() {
        System.out.println("getY");
        int expResult = 50;
        GameUnit tmp = new GameUnit(75, 50);
        int result = tmp.getY();
        assertEquals(expResult, result);
    }

    @Test
    void setX() {
        System.out.println("setX");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        tmp.setX(50);
        int result = tmp.getX();
        assertEquals(expResult, result);
    }

    @Test
    void setY() {
        System.out.println("setY");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        tmp.setY(75);
        int result = tmp.getX();
        assertEquals(expResult, result);
    }

    @Test
    void getHeight() {
        System.out.println("getHeight");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        tmp.setHeight(100);
        int result = tmp.getHeight();
        assertEquals(expResult, result);
    }

    @Test
    void setHeight() {
        System.out.println("setHeight");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        tmp.setHeight(100);
        int result = tmp.getHeight();
        assertEquals(expResult, result);
    }

    @Test
    void getWidth() {
        System.out.println("getWidth");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        tmp.setWidth(100);
        int result = tmp.getWidth();
        assertEquals(expResult, result);
    }

    @Test
    void setWidth() {
        System.out.println("setWidth");
        int expResult = 75;
        GameUnit tmp = new GameUnit(75, 50);
        tmp.setWidth(100);
        int result = tmp.getWidth();
        assertEquals(expResult, result);
    }

    @Test
    void move() {
        System.out.println("move");
        int expResult = 175;
        GameUnit tmp = new GameUnit(75, 50);
        for (int i = 0; i < 25; i++) {
            tmp.move();
        }
        int result = tmp.getX();
        assertEquals(expResult, result);
    }
}