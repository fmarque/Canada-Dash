package com.group47.canadadash.processing;

// Gson library imports for JSON processing: serialization and deserialization
import com.google.gson.*;

// I/O related imports for file handling
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class leveraging Gson for JSON operations and file I/O.
 * Provides methods for reading and writing JSON data to files,
 * and converting between JSON strings and Java objects.
 * Aimed at simplifying data persistence and retrieval in JSON format.
 *
 * @author : Muhammad Affan Yasir [myasir2@uwo.ca]
 * @version : 1.1
 * @since : 1.0
 * @modified : 2023-04-12
 */
public class Util {

    /**
     * The root path of the application, determined at runtime.
     */
    private static final String rootPath;

    static {
        /*
          Default temporary root path set to the current directory.
         */
        String tmp = ".";
        /*
          Attempt to update 'tmp' with the canonical path of the application's directory.
         */
        try {
            tmp = new File("").getCanonicalPath();
        } catch (IOException e) {
            // Print stack trace and exit program if obtaining canonical path fails.
            e.printStackTrace();
            System.exit(e.hashCode());
        }
        /*
          Assign the determined path to 'rootPath'.
         */
        rootPath = tmp;
    }

    /**
     * Retrieves the root path of the application.
     *
     * @return The root directory path of the application.
     */
    public static String getRootPath() {
        return rootPath;
    }

    /**
     * Reads and returns the content of a specified data file.
     *
     * @param dataFile The file from which content is to be fetched.
     * @return The content of the file as a string.
     */
    public static String fetchFileContent(File dataFile) {
        /*
          Check if the file exists and is readable; exit with specific error codes if not.
         */
        if (!dataFile.exists()) {
            System.out.println("File does not exist");
            System.exit(13);
        }
        if (!dataFile.canRead()) {
            System.out.println("Cannot read the file");
            System.exit(14);
        }

        /*
          Attempt to read the file's bytes and convert them to a UTF-8 encoded string.
         */
        try {
            byte[] fileBytes = Files.readAllBytes(dataFile.toPath());
            return new String(fileBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Log the error reading the file and exit.
            System.out.println("Error reading the file: " + e.getMessage());
            System.exit(e.hashCode());
        }

        // This line is theoretically unreachable due to the System.exit calls above.
        return "";
    }

    /**
     * Converts JSON content from a file into an instance of a specified class.
     *
     * @param <T> The type of class to convert the JSON into.
     * @param filePath The path of the file containing the JSON data.
     * @param classOfT The Class object representing class T.
     * @return An instance of class T populated with the data from the JSON file; returns null if parsing fails.
     */
    public static <T> T readJsonFromFile(String filePath, Class<T> classOfT) {
        // Fetch the content from the specified file path.
        String json = fetchFileContent(new File(filePath));
        Gson gson = new Gson();
        // Attempt to parse the JSON string into an instance of class T.
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            // Log parsing error and return null.
            System.out.println("Error parsing JSON to object: " + e.getMessage());
            return null;
        }
    }

    /**
     * Writes data to a specified file in JSON format.
     *
     * @param data The data object to be serialized to JSON and written to the file.
     * @param fullPath The absolute path within the application directory where the file should be written.
     */
    public static void writeToFile(Object data, String fullPath){
        Gson gson = new Gson();

        // Attempt to write the JSON representation of 'data' to the file.
        try (FileWriter file = new FileWriter(fullPath)) {
            gson.toJson(data, file);
        } catch (IOException e) {
            // Log failure to write to the file.
            System.out.println("Could not write to file: " + fullPath);
        }
    }
}

