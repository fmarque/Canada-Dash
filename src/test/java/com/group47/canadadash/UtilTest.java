package com.group47.canadadash;

import com.group47.canadadash.processing.User;
import com.group47.canadadash.processing.Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.*;


/**
 * A test suite for the {@link Util} class, focusing on its file handling and JSON processing capabilities.
 * This class tests various functionalities including fetching the root path, reading file contents,
 * reading JSON data into objects, and writing objects as JSON data into files.
 * <p>
 * These tests ensure that the {@link Util} class methods behave as expected under various scenarios,
 * including handling of temporary files for testing file read/write operations.
 * </p>
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo]
 * @version : 1.0
 * @since : 1.0
 */
class UtilTest {


    /**
     * Tests the {@link Util#getRootPath()} method to verify if it correctly returns the application's current directory.
     */
    @Test
    void testGetRootPath() {
        String expected = System.getProperty("user.dir");
        String actual = Util.getRootPath();
        assertEquals(expected, actual, "The rootPath should match the current directory.");
    }


    /**
     * Tests the {@link Util#fetchFileContent(File)} method to verify if it can correctly read the contents of a file.
     * A temporary file is created with predefined content to test this functionality.
     *
     * @throws IOException if an I/O error occurs writing to or creating the file
     */
    @Test
    void testFetchFileContent() throws IOException {
        // Create a temporary file and write some content
        File tempFile = File.createTempFile("test", ".txt");
        String expectedContent = "Hello, World!";
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(expectedContent);
        }

        String actualContent = Util.fetchFileContent(tempFile);
        assertEquals(expectedContent, actualContent, "The fetched content should match the file content.");

        // Clean up the temporary file
        tempFile.deleteOnExit();
    }


    /**
     * Tests the {@link Util#readJsonFromFile(String, Class)} method to verify if it can correctly parse JSON data from a file into a {@link User} object.
     * A temporary JSON file is created with a simple JSON representation of a User to test this functionality.
     *
     * @throws IOException if an I/O error occurs writing to or creating the file
     */
    @Test
    void testReadJsonFromFile() throws IOException {
        // Create a temporary JSON file and write a simple JSON object for User
        File tempFile = File.createTempFile("testUser", ".json");
        String jsonContent = "{\"userID\":\"john.doe\",\"password\":\"password123\",\"type\":\"student\",\"classCode\":\"CS101\",\"totalPoints\":100,\"highestLevelReached\":5}";
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(jsonContent);
        }

        User user = Util.readJsonFromFile(tempFile.getAbsolutePath(), User.class);
        assertNotNull(user, "The method should successfully parse the JSON content into a User object.");
        assertEquals("john.doe", user.getUserID(), "The userID should be correctly parsed.");
        assertEquals("password123", user.getPassword(), "The password should be correctly parsed.");
        assertEquals("student", user.getType(), "The type should be correctly parsed.");
        assertEquals("CS101", user.getClassCode(), "The classCode should be correctly parsed.");
        assertEquals(100, user.getTotalPoints(), "The totalPoints should be correctly parsed.");
        assertEquals(5, user.getHighestLevelReached(), "The highestLevelReached should be correctly parsed.");

        // Clean up the temporary file
        tempFile.deleteOnExit();
    }


    /**
     * Tests the {@link Util#writeToFile(Object, String)} method to verify if it can correctly write a {@link User} object as JSON data into a file.
     * The file content is then read back to validate the write operation.
     *
     * @throws IOException if an I/O error occurs writing to or creating the file
     */
    @Test
    void testWriteToFile() throws IOException {
        // Create a User object
        User user = new User();
        user.setUserID("jane.doe");
        user.setPassword("securePassword");
        user.setType("instructor");
        user.setClassCode("CS102");
        user.setTotalPoints(200);
        user.setHighestLevelReached(10);

        // Create a temporary file for output
        File tempFile = File.createTempFile("testWriteUser", ".json");

        Util.writeToFile(user, tempFile.getAbsolutePath());

        // Read back the file content
        String content = Util.fetchFileContent(tempFile);
        assertFalse(content.isEmpty(), "The file should have content after writing.");

        // Validate if the written file content can be parsed back to a User object
        User readUser = new Gson().fromJson(content, User.class);
        assertEquals("jane.doe", readUser.getUserID(), "The written and read back userID should match.");
        assertEquals("securePassword", readUser.getPassword(), "The written and read back password should match.");
        assertEquals("instructor", readUser.getType(), "The written and read back type should match.");
        assertEquals("CS102", readUser.getClassCode(), "The written and read back classCode should match.");
        assertEquals(200, readUser.getTotalPoints(), "The written and read back totalPoints should match.");
        assertEquals(10, readUser.getHighestLevelReached(), "The written and read back highestLevelReached should match.");

        // Clean up
        tempFile.deleteOnExit();
    }
}
