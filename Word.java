package test;

import java.util.Arrays;
import java.util.Objects;

public class Word {
    Tile[] tiles;  // Array of tiles representing the word
    int row;       // Row position of the word
    int col;       // Column position of the word
    boolean vertical;  // Indicates if the word is vertical or horizontal

    // Constructor for Word class
    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    // Getter for tiles
    public Tile[] getTiles() {
        return tiles;
    }

    // Getter for row
    public int getRow() {
        return row;
    }

    // Getter for column
    public int getCol() {
        return col;
    }

    // Getter for vertical
    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // If the objects are the same, they are equal
        if (!(o instanceof Word)) return false;  // If the object is not an instance of Word, they are not equal
        Word word = (Word) o;  // Casting the object to Word
        // Checking equality based on row, col, vertical, and the content of tiles array
        return getRow() == word.getRow() && getCol() == word.getCol() && isVertical() == word.isVertical() && Arrays.deepEquals(getTiles(), word.getTiles());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(row, col, vertical);
        result = 31 * result + Arrays.deepHashCode(tiles);
        return result;
    }
}
