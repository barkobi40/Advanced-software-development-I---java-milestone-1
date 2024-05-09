package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// Class responsible for searching words in input files
public class IOSearcher {
    // Method to search for a word in the provided files
    public static boolean search(String word, String... fileNames) {
        Scanner scanner = null; // Declare a Scanner variable
        // Iterate through each file name in the array
        for (String file : fileNames) {
            try {
                // Create a Scanner to read the file
                scanner = new Scanner(new BufferedReader(new FileReader(file)));
                // Iterate through each word in the file
                while (scanner.hasNext()) {
                    // Check if the current word matches the search word
                    if (scanner.next().matches(word))
                        return true; // If match found, return true
                }
            } catch (FileNotFoundException e) {
                // If file not found, throw a runtime exception
                throw new RuntimeException(e);
            } finally {
                if (scanner != null)
                    scanner.close(); // Close the scanner to release resources
            }
        }
        return false; // Return false if word not found in any file
    }
}
