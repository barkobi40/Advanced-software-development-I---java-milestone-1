package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// Implementation of the Least Frequently Used (LFU) cache replacement policy
public class LFU implements CacheReplacementPolicy {
    LinkedHashMap<String, Integer> cache = new LinkedHashMap<>(); // LinkedHashMap to store cached items with their access counts

    // Method to calculate the hash code for the LFU cache
    @Override
    public int hashCode() {
        return Objects.hash(cache);
    }

    // Method to add a word to the cache
    @Override
    public void add(String word) {
        // If word already exists in the cache, increment its access count
        if (cache.containsKey(word))
            cache.put(word, cache.get(word) + 1);
        // If word does not exist in the cache, add it with access count 1
        else
            cache.put(word, 1);
    }

    // Method to remove the least frequently used word from the cache
    @Override
    public String remove() {
        // Get the first key from the LinkedHashMap
        String test = cache.keySet().iterator().next();
        int minVal = cache.get(test);

        // Iterate through the entries of the cache to find the key with the minimum access count
        for (Map.Entry<String, Integer> entry : cache.entrySet()) {
            if (minVal > entry.getValue()) {
                minVal = entry.getValue(); // Update the minimum access count
                test = entry.getKey(); // Update the key corresponding to the minimum access count
            }
        }
        // Return the key corresponding to the least frequently used word
        return test;
    }
}
