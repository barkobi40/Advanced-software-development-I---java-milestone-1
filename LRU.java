package test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Implementation of the Least Recently Used (LRU) cache replacement policy
public class LRU implements CacheReplacementPolicy {
    HashSet<String> cache = new HashSet<>(); // HashSet to store cached items

    // Method to add a word to the cache
    @Override
    public void add(String word) {
        // If word already exists in the cache, remove it to update its access status
        if (cache.contains(word))
            cache.remove(word);
        // Add the word to the cache
        else
            cache.add(word);
    }

    // Method to remove the least recently used word from the cache
    @Override
    public String remove() {
        // Convert the cache HashSet to an array of strings
        String[] strings = new String[cache.size()];
        strings = cache.toArray(strings);
        // Return the first string in the array, representing the least recently used word
        return strings[0];
    }
}
