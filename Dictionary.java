package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// Class representing a dictionary with caching mechanisms for efficient word lookups
public class Dictionary {
    CacheManager cacheLRU; // Cache manager for Least Recently Used (LRU) strategy
    CacheManager cacheLFU; // Cache manager for Least Frequently Used (LFU) strategy
    BloomFilter bloomFilter; // Bloom filter for efficient word existence checks
    String[] filenames; // Array of file names representing books

    // Constructor initializes caches, Bloom filter, and processes input files
    public Dictionary(String... fileNames) {
        filenames = fileNames; // Store the input file names

        // Initialize the cache managers with specified sizes and replacement policies
        this.cacheLRU = new CacheManager(400, new LRU());
        this.cacheLFU = new CacheManager(100, new LFU());

        // Initialize the Bloom filter with specified size and hash algorithms
        this.bloomFilter = new BloomFilter(256, "MD5", "SHA1");

        Scanner scanner = null;
        // Process each input file to populate the Bloom filter
        for (String file : fileNames) {
            try {
                // Create a Scanner to read the file
                scanner = new Scanner(new BufferedReader(new FileReader(file)));
                // Iterate through each word in the file and add it to the Bloom filter
                while (scanner.hasNext()) {
                    bloomFilter.add(scanner.next());
                }
            } catch (FileNotFoundException e) {
                // If file not found, throw a runtime exception
                throw new RuntimeException(e);
            } finally {
                if (scanner != null)
                    scanner.close(); // Close the scanner to release resources
            }
        }
    }

    // Method to query if a word exists in the dictionary
    public boolean query(String word) {
        // Check if word exists in the LRU cache
        if (cacheLRU.query(word))
            return true;
        // Check if word exists in the LFU cache
        else if (cacheLFU.query(word))
            return false;
        // If not found in caches, check Bloom filter
        else {
            // If word exists in Bloom filter, add it to the LRU cache and return true
            if (bloomFilter.contains(word)) {
                cacheLRU.add(word);
                return true;
            }
            // If word does not exist in Bloom filter, add it to the LFU cache and return false
            else {
                cacheLFU.add(word);
                return false;
            }
        }
    }

    // Method to challenge a word by searching in the provided files
    public boolean challenge(String word) {
        try {
            // Search for the word in the provided files using IOSearcher
            if (IOSearcher.search(word, filenames)) {
                // If word found, add it to the LRU cache and return true
                cacheLRU.add(word);
                return true;
            } else {
                // If word not found, add it to the LFU cache and return false
                cacheLFU.add(word);
                return false;
            }
        } catch (Exception e) {
            return false; // Return false if an exception occurs during searching
        }
    }
}
