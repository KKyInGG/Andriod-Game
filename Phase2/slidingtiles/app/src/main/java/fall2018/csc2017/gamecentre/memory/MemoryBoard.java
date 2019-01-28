package fall2018.csc2017.gamecentre.memory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class MemoryBoard extends Observable implements Serializable {

    /**
     * The number of rows on the board
     **/
    private int NUM_ROWS;

    /**
     * The number of columns on the board
     */
    private int NUM_COLS;

    /**
     * The array containg the solution to the Memory game
     */
    private Pairs[][] pairsSolution;

    /**
     * The array of the memory game being played
     */
    private Pairs[][] pairsPuzzle;

    /**
     * Creat a new Memory board with given list of tiles and board dimension r and c.
     * @param tiles given arrangment of cards
     * @param r row number
     * @param c column number
     */
    public MemoryBoard(List<Pairs> tiles, int r, int c) {
        Iterator<Pairs> iter = tiles.iterator();
        this.NUM_ROWS = r;
        this.NUM_COLS = c;
        this.pairsSolution = new Pairs[NUM_ROWS][NUM_COLS];
        this.pairsPuzzle = new Pairs[NUM_ROWS][NUM_COLS];
        for (int row = 0; row != this.NUM_ROWS; row++) {
            for (int col = 0; col != this.NUM_COLS; col++) {
                this.pairsSolution[row][col] = iter.next();
                this.pairsPuzzle[row][col] = new Pairs(18);
            }
        }
    }

    /**
     * Return this boards number of rows
     * @return NUM_ROWS
     */
    public int getNUM_ROWS() {
        return NUM_ROWS;
    }

    /**
     * Return this boards number of columns
     * @return NUM_COLS
     */
    public int getNUM_COLS() {
        return NUM_COLS;
    }

    /**
     * Return the array representing the current memory puzzle
     * @return pairsPuzzle
     */
    public Pairs[][] getPairsPuzzle() {
        return pairsPuzzle;
    }

    /**
     * Reveal the value at given position
     * @param position of value to reveal
     */
    public void flipCard(int position){
        int row = position / this.getNUM_COLS();
        int col = position % this.getNUM_COLS();
        pairsPuzzle[row][col] = pairsSolution[row][col];
        setChanged();
        notifyObservers();
    }

    /**
     * Conceal the value at the given position
     * @param position of value to coneal
     */
    public void unFlipCard(int position){
        int row = position / this.getNUM_COLS();
        int col = position % this.getNUM_COLS();
        pairsPuzzle[row][col] = new Pairs(18);
        setChanged();
        notifyObservers();
    }


    /**
     * Return the Pairs at (row, col)
     *
     * @param position the position
     * @return the tile at (row, col)
     */
    public Pairs getPairs(int position) {
        int row = position / this.getNUM_COLS();
        int col = position % this.getNUM_COLS();
        return pairsPuzzle[row][col];
    }

    /**
     * Return the pairs at selected row and column
     * @param row selcted
     * @param col selcted
     * @return Pairs
     */
    public Pairs getPairs(int row, int col){
        return pairsPuzzle[row][col];
    }

    /**
     * Return the array representing the solutionto memory game
     * @return pairsSolution
     */
    public Pairs[][] getSolutionPairs() {
        return pairsSolution;
    }


}
