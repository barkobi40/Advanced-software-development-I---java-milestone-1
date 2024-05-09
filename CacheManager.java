package test;

import java.util.HashSet;

// Class representing a cache manager
public class CacheManager {
    HashSet<String> cache = new HashSet<>(); // HashSet to store cached items
    int size; // Maximum size of the cache
    CacheReplacementPolicy crp; // Cache replacement policy

    // Constructor initializes the cache manager with size and replacement policy
    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size; // Set maximum size of the cache
        this.crp = crp; // Set cache replacement policy
    }

    // Method to check if a word is in the cache
    public boolean query(String word) {
        return cache.contains(word); // Return true if word is in the cache
    }

    // Method to add a word to the cache
    public void add(String word) {
        crp.add(word); // Add the word to the cache using the cache replacement policy
        cache.add(word); // Add the word to the cache set
        // If the size of the cache exceeds the maximum size, remove a word based on the replacement policy
        if (cache.size() > size)
            cache.remove(crp.remove());
    }
}
