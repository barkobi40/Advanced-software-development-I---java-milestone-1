package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

// Class representing a Bloom filter data structure
public class BloomFilter {
    private final BitSet bitSets; // BitSet to store Bloom filter bits
    List<MessageDigest> arrFunc = new ArrayList<>(); // List of hash functions

    // Constructor initializes the Bloom filter with specified size and hash functions
    public BloomFilter(int size, String... args) {
        bitSets = new BitSet(size); // Initialize BitSet with specified size
        // Iterate through each hash function name provided as arguments
        for (String funcName : args) {
            try {
                // Get instance of MessageDigest for the given hash function
                MessageDigest md = MessageDigest.getInstance(funcName);
                // Add the MessageDigest instance to the list of hash functions
                arrFunc.add(md);
            } catch (NoSuchAlgorithmException e) {
                // Throw a runtime exception if the hash function is not found
                throw new RuntimeException(e);
            }
        }
    }

    // Method to add a word to the Bloom filter
    void add(String word) {
        // Apply each hash function to the word and set the corresponding bit in the BitSet
        for (MessageDigest funcName : arrFunc) {
            byte[] bts = funcName.digest(word.getBytes()); // Get the hash value of the word
            BigInteger bi = new BigInteger(bts); // Convert the hash value to a BigInteger
            int num = bi.intValue(); // Convert the BigInteger to an integer
            num = Math.abs(num); // Ensure the integer is positive
            num = num % bitSets.size(); // Map the integer to a bit position in the BitSet
            if (!(bitSets.get(num))) // If the bit is not set, flip it to set it
                bitSets.flip(num);
        }
    }

    // Method to check if a word may be in the Bloom filter
    boolean contains(String word) {
        // Apply each hash function to the word and check if the corresponding bit is set in the BitSet
        for (MessageDigest funcName : arrFunc) {
            byte[] bts = funcName.digest(word.getBytes()); // Get the hash value of the word
            BigInteger bi = new BigInteger(bts); // Convert the hash value to a BigInteger
            int num = bi.intValue(); // Convert the BigInteger to an integer
            num = Math.abs(num); // Ensure the integer is positive
            num = num % bitSets.size(); // Map the integer to a bit position in the BitSet
            if (!bitSets.get(num)) // If the bit is not set, return false
                return false;
        }
        return true; // If all bits are set, return true
    }

    // Method to convert the Bloom filter BitSet to a string representation
    @Override
    public String toString() {
        StringBuilder st = new StringBuilder(bitSets.size());
        // Append '1' or '0' based on the value of each bit in the BitSet
        for (int i = 0; i < bitSets.length(); i++) {
            st.append(bitSets.get(i) ? "1" : "0");
        }
        return st.toString(); // Return the string representation of the BitSet
    }
}
