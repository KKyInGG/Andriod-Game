package fall2018.csc2017.gamecentre.slidingtiles;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class SlidingTilesBoard extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    private int NUM_ROWS;

    /**
     * The number of rows.
     */
    private int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    public SlidingTilesBoard(List<Tile> tiles, int r, int c) {
        Iterator<Tile> iter = tiles.iterator();
        this.NUM_ROWS = r;
        this.NUM_COLS = c;
        this.tiles = new Tile[NUM_ROWS][NUM_COLS];

        for (int row = 0; row != this.NUM_ROWS; row++) {
            for (int col = 0; col != this.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return NUM_COLS.
     */
    public int getNUM_COLS() {
        return NUM_COLS;
    }

    /**
     * Return NUM_ROWS.
     */
    public int getNUM_ROWS() {
        return NUM_ROWS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tile1 = tiles[row1][col1];
        Tile tile2 = tiles[row2][col2];

        tiles[row1][col1] = tile2;
        tiles[row2][col2] = tile1;

        setChanged();
        notifyObservers();
    }

    /**
     * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     * @param tiles
     */
    public boolean isSolvable(List<Tile> tiles) {
        if (this.NUM_COLS == 3 || this.NUM_COLS == 5) {
            return getNumInversion(tiles) % 2 == 0;
        } else {
            if (checkEmptyOnOdd()) {
                return getNumInversion(tiles) % 2 == 1;
            } else {
                return getNumInversion(tiles) % 2 == 0;
            }
        }
    }

    /**
     * return number of inversions.
     * @param tiles
     */
    private int getNumInversion(List<Tile> tiles){
        int sum = 0;
        for (int i = 0; i != tiles.size(); i++){
            int sub = 0;
            int currentId = tiles.get(i).getId();
            if (currentId != 25) {
                for (int j = i; j != tiles.size(); j++) {
                    if (currentId > tiles.get(j).getId()) {
                        sub++;
                    }
                }
            }
            sum += sub;
        }
        return sum;
    }

    /**
     * return whether the empty tile is on the odd rows.
     */
    private boolean checkEmptyOnOdd(){
        boolean onOdd = false;
        for (int i = 0; i != 4; i++){
            if (this.getTile(0, i).getId() == 25 ||
                    this.getTile(2, i).getId() == 25){
                onOdd = true;
            }
        }
        return onOdd;
    }

    @Override
    public String toString() {
        return "SlidingTilesBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    private class TileIterator implements Iterator<Tile> {
        /**
         * The current row index.
         */
        private int currentRow;
        /**
         * The current column index.
         */
        private int currentColumn;

        /**
         * An iterator over the tiles in the board.
         * Starts the indices pointing to the top left tile in the 2D tiles array.
         */
        public TileIterator() {
            this.currentRow = 0;
            this.currentColumn = 0;
        }

        @Override
        public boolean hasNext() {
            return (currentRow < (NUM_ROWS - 1) || currentColumn < (NUM_COLS - 1));
        } // Using constants minus 1 to match indices starting from 0.

        @Override
        public Tile next() {
            Tile thisTile = tiles[currentRow][currentColumn];
            if (hasNext()) {
                if (currentColumn == (NUM_COLS - 1)) {
                    currentRow++;
                    currentColumn = 0;
                } else {
                    currentColumn++;
                }
                return thisTile;
            }
            throw new NoSuchElementException();
        }
    }
}

