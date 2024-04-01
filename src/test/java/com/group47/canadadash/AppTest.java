package com.group47.canadadash;

import com.group47.canadadash.processing.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

/**
 * Test class for {@link App}. This class conducts unit tests to verify the core functionalities
 * of the application, including account creation, sign-in process, point updates, and class code
 * validation, ensuring the application behaves as expected under various scenarios.
 *
 * @author: Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version: 1.0
 * @since: 1.0
 */
class AppTest {
    private App app;

    /**
     * Prepares the testing environment before each test case. This setup includes getting the
     * singleton instance of the App class and loading initial data, preparing the application
     * for operation.
     */
    @BeforeEach
    void setUp() {
        app = App.getInstance();
        app.loadData();
    }

    /**
     * Tests the account creation process within the app. Verifies that an account can be
     * successfully created with unique credentials and that attempting to create another
     * account with the same credentials fails, ensuring uniqueness of user accounts.
     */
    @Test
    void testCreateAccount() {
        assertTrue(app.createAccount("newUser", "password", "student"));
        assertFalse(app.createAccount("newUser", "password", "student"));
    }

    /**
     * Tests the sign-in functionality of the app. This test ensures that a user can sign in
     * with correct credentials and that sign-in attempts with incorrect passwords fail,
     * validating the security of the sign-in process.
     */
    @Test
    void testSignIn() {
        app.createAccount("testUser", "testPass", "student");
        assertTrue(app.signIn("testUser", "testPass", "student"));
        assertFalse(app.signIn("testUser", "wrongPass", "student"));
    }

    /**
     * Tests the update points feature for a user account. Verifies that points can be added
     * to a user's account following a successful sign-in, ensuring the application correctly
     * tracks and updates user points.
     */
    @Test
    void testUpdatePoints() {
        app.createAccount("pointUser", "pointPass", "student");
        app.signIn("pointUser", "pointPass", "student");
        app.updatePoints(100);
        assertEquals(100, app.user.getTotalPoints());
    }

    /**
     * Tests the validity of a class code generated for an instructor account. Verifies that
     * the class code associated with an instructor's account is considered valid by the
     * application, ensuring the app supports classroom management functionalities.
     */
    @Test
    void testIsValidClassCode() {
        app.createAccount("instructorUser", "instructorPass", "instructor");
        assertTrue(app.isValidClassCode(app.user.getClassCode()));
    }
}
