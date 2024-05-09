package test;

import java.util.HashMap;
import java.util.Map;

// Class responsible for managing multiple dictionaries
public class DictionaryManager {
    private static DictionaryManager dm = null; // Singleton instance of DictionaryManager
    public Map<String, Dictionary> map = new HashMap<>(); // Map to store dictionaries

    // Private constructor to prevent direct instantiation
    private DictionaryManager() {
    }

    // Method to get the singleton instance of DictionaryManager
    public static DictionaryManager get() {
        if (dm == null)
            dm = new DictionaryManager(); // Create a new instance if it doesn't exist
        return dm; // Return the singleton instance
    }

    // Method to query if a word exists in any of the dictionaries
    public boolean query(String... books) {
        String last = books[books.length - 1]; // Get the last book name from the input
        boolean flag = false; // Initialize flag to indicate word existence
        for (int i = 0; i < books.length - 1; i++) {
            // Add dictionaries for missing books
            if (!map.containsKey(books[i]))
                map.put(books[i], new Dictionary(books[i])); // Create a new dictionary for the book
        }
        // Check if the word exists in any of the dictionaries
        for (Dictionary d : map.values())
            if (d.query(last))
                flag = true; // Set flag to true if word exists
        return flag; // Return the final flag value
    }

    // Method to challenge a word by searching in any of the dictionaries
    public boolean challenge(String... books) {
        String last = books[books.length - 1]; // Get the last book name from the input
        boolean flag = false; // Initialize flag to indicate word existence
        for (int i = 0; i < books.length - 1; i++) {
            // Add dictionaries for missing books
            if (!map.containsKey(books[i]))
                map.put(books[i], new Dictionary(books[i])); // Create a new dictionary for the book
        }
        // Check if the word exists in any of the dictionaries
        for (Dictionary d : map.values())
            if (d.challenge(last))
                flag = true; // Set flag to true if word exists
        return flag; // Return the final flag value
    }

    // Method to get the size of the dictionary manager (number of dictionaries)
    public int getSize() {
        return map.size(); // Return the size of the map
    }
}
