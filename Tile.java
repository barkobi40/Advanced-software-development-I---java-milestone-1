package test;

import java.util.Objects;
import java.util.Random;

public class Tile {
    public final char letter;  // Letter represented by the tile
    public final int score;    // Score value of the tile

    // Private constructor for Tile class
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    // Override equals method to check equality of Tile objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // If the objects are the same, they are equal
        if (!(o instanceof Tile)) return false;  // If the object is not an instance of Tile, they are not equal
        Tile tile = (Tile) o;  // Casting the object to Tile
        // Checking equality based on letter and score
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    // Bag class for managing a set of tiles
    public static class Bag {
        int[] arr;          // Array to store quantities of each tile
        Tile[] tiles;       // Array of Tile objects
        int[] original;     // Original array of quantities
        private static Bag instance = null;  // Singleton instance of Bag
        private Random random = new Random();

        // Private constructor for Bag class
        private Bag() {
            // Initializing quantities of each tile
            this.arr = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            this.original = this.arr.clone();  // Keeping a copy of the original quantities
            // Array of Tile objects, each representing a letter and score
            this.tiles = new Tile[]{
                    new Tile('A', 1),
                    new Tile('B', 3),
                    new Tile('C', 3),
                    new Tile('D', 2),
                    new Tile('E', 1),
                    new Tile('F', 4),
                    new Tile('G', 2),
                    new Tile('H', 4),
                    new Tile('I', 1),
                    new Tile('J', 8),
                    new Tile('K', 5),
                    new Tile('L', 1),
                    new Tile('M', 3),
                    new Tile('N', 1),
                    new Tile('O', 1),
                    new Tile('P', 3),
                    new Tile('Q', 10),
                    new Tile('R', 1),
                    new Tile('S', 1),
                    new Tile('T', 1),
                    new Tile('U', 1),
                    new Tile('V', 4),
                    new Tile('W', 4),
                    new Tile('X', 8),
                    new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }

        // Method to get a random tile from the bag
        public Tile getRand() {
            int sum = 0;
            for (int count : this.arr) {
                sum += count;
            }
            if (sum == 0) {
                return null; // Bag is empty
            }
            int num;
            do {
                num = random.nextInt(26);
            } while (this.arr[num] == 0);
            this.arr[num]--;
            return this.tiles[num];
        }

        // Method to get a specific tile by letter from the bag
        public Tile getTile(char c) {
            if (c >= 'A' && c <= 'Z') {
                int num = c - 'A';
                if (this.arr[num] > 0) {
                    this.arr[num]--;
                    return this.tiles[num];
                }
            }
            return null;
        }

        // Method to put a tile back into the bag
        public void put(Tile t) {
            char c = t.letter;
            int num = c - 'A';
            if (this.original[num] > this.arr[num])
                this.arr[num]++;
        }

        // Method to get the total number of tiles in the bag
        public int size() {
            int sum = 0;
            for (int count : this.arr) {
                sum += count;
            }
            return sum;
        }

        // Method to get the quantities of each tile in the bag
        public int[] getQuantities() {
            return this.arr.clone();
        }

        // Singleton method to get the Bag instance
        public static Bag getBag() {
            if (instance == null)
                instance = new Bag();
            return instance;
        }

        // Method to reset the bag to its original state
        public void reset() {
            this.arr = this.original.clone();
        }

        // Method to shuffle the tiles in the bag
        public void shuffle() {
            for (int i = this.tiles.length - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                Tile temp = this.tiles[index];
                this.tiles[index] = this.tiles[i];
                this.tiles[i] = temp;
            }
        }
    }
}
