package com.group47.canadadash;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeartTest {

    @Test
    void setHeart() {
        System.out.println("setHeart");
        Heart heart = new Heart(50, 50);
        heart.setHeart(false);
        assertFalse(heart.isFullHeart());
    }

    @Test
    void isFullHeart() {
        System.out.println("isFullHeart");
        Heart heart = new Heart(50, 50);
        assertTrue(heart.isFullHeart());
    }

    @Test
    void getImageAdd() {
        System.out.println("getImageAdd");
        Heart heart = new Heart(50, 50);
        String expResult = "src/main/resources/images/fullHeart.png";
        String result = heart.getImageAdd();
        assertEquals(expResult, result);
    }

    @Test
    void setImageAdd() {
        System.out.println("setImageAdd");
        Heart heart = new Heart(50, 50);
        String expResult = "src/main/resources/images/emptyHeartIcon.png";
        heart.setHeart(false);
        String result = heart.getImageAdd();
        assertEquals(expResult, result);
    }
}