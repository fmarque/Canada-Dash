package com.group47.canadadash.processing;

import java.util.Scanner;
import org.json.*;
import com.google.gson.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;

public class Util{

    private static final String rootPath;

    static {
        String tmp = null;
        try {
            tmp = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(e.hashCode());
        }
        rootPath = tmp;
    }

    public static String getRootPath() {
        return rootPath;
    }

    }

