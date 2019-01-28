package fall2018.csc2017.gamecentre.slidingtiles;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.gamecentre.GameManager;

/**
 * Manage a slidingTilesBoard, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingTilesManager implements GameManager, Serializable {

    /**
     * The slidingTilesBoard being managed.
     */
    private int score;
    private SlidingTilesBoard slidingTilesBoard;
    private int numUndos;
    private Stack<Integer> undoDirectionStack;
    private Stack<Integer> undoPositionStack;

    /**
     * Manage a slidingTilesBoard that has been pre-populated.
     * @param slidingTilesBoard the slidingTilesBoard
     */
    public SlidingTilesManager(SlidingTilesBoard slidingTilesBoard) {
        this.slidingTilesBoard = slidingTilesBoard;
        this.numUndos = 3;
        this.undoDirectionStack = new Stack<>();
        this.undoPositionStack = new Stack<>();
    }


    /**
     * Return the current slidingTilesBoard.
     */
    public SlidingTilesBoard getSlidingTilesBoard() {
        return slidingTilesBoard;
    }

    /**
     * Manage a new shuffled slidingTilesBoard.
     */
    public SlidingTilesManager(int rows, int cols) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = rows * cols;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(24));

        Collections.shuffle(tiles);
        this.slidingTilesBoard = new SlidingTilesBoard(tiles, rows, cols);
        while (!(this.slidingTilesBoard.isSolvable(tiles))){
            Collections.shuffle(tiles);
            this.slidingTilesBoard = new SlidingTilesBoard(tiles, rows, cols);
        }

        this.numUndos = 3;
        this.undoDirectionStack = new Stack<>();
        this.undoPositionStack = new Stack<>();
    }

    /**
     * Return the score of a slidingtiles game.
     * @return score
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Return the numUndos left of a slidingtiles game.
     * @return numUndos
     */
    public int getNumUndos() {
        return numUndos;
    }

    /**
     * Set the numUndos of a slidingtiles game.
     */
    public void setNumUndos(int num) { this.numUndos = num; }

    /**
     * Return whether the tiles are in row-major order.
     * @return whether the tiles are in row-major order
     */
    @SuppressLint("DefaultLocale")
    @Override
    public boolean isGameOver() {
        boolean solved = true;
        int i = 1;
        for (Tile t : slidingTilesBoard) {
            if (t.getId() != i) {
                solved = false;
            }
            i++;
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    public boolean isValidTap(int position) {

        int row = position / slidingTilesBoard.getNUM_COLS();
        int col = position % slidingTilesBoard.getNUM_COLS();
        int blankId = 25;
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : slidingTilesBoard.getTile(row - 1, col);
        Tile below = row == slidingTilesBoard.getNUM_ROWS() - 1 ? null : slidingTilesBoard.getTile(row + 1, col);
        Tile left = col == 0 ? null : slidingTilesBoard.getTile(row, col - 1);
        Tile right = col == slidingTilesBoard.getNUM_COLS() - 1 ? null : slidingTilesBoard.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);

    }

    /**
     * Process a touch at position in the slidingTilesBoard, swapping tiles as appropriate.
     * Also adds an integer to the undo stack. 0,1,2,3 correspond to a tile swapped to
     * the above, left, below, and right respectively.
     * @param position the position
     */
    @Override
    public void touchMove(int position) {

        int row = position / slidingTilesBoard.getNUM_ROWS();
        int col = position % slidingTilesBoard.getNUM_COLS();
        int blankId = 25;

        Tile above = row == 0 ? null : slidingTilesBoard.getTile(row - 1, col);
        Tile left = col == 0 ? null : slidingTilesBoard.getTile(row, col - 1);
        Tile below = row == slidingTilesBoard.getNUM_ROWS() - 1 ? null : slidingTilesBoard.getTile(row + 1, col);

        if (above != null && above.getId() == blankId) {
            slidingTilesBoard.swapTiles(row, col, row - 1, col);
            this.undoDirectionStack.push(0);
        } else if (left != null && left.getId() == blankId) {
            slidingTilesBoard.swapTiles(row, col, row, col - 1);
            this.undoDirectionStack.push(1);
        } else if (below != null && below.getId() == blankId) {
            slidingTilesBoard.swapTiles(row, col, row + 1, col);
            this.undoDirectionStack.push(2);
        }  else { // the tile to the right is the blank tile
            slidingTilesBoard.swapTiles(row, col, row, col + 1);
            this.undoDirectionStack.push(3);
        }
        this.undoPositionStack.push(position);
        score++;
    }

    /**
     * Checks if there are undo moves left, and if so, swaps tiles in the reverse order of
     * the last move.
     */
    public void tryUndo() {
        if (this.numUndos > 0 && !(this.undoDirectionStack.empty() && this.undoPositionStack.empty())) {
            numUndos--;
            int position = undoPositionStack.pop();
            int direction = undoDirectionStack.pop();
            int row = position / slidingTilesBoard.getNUM_ROWS();
            int col = position % slidingTilesBoard.getNUM_COLS();
            score--;
            switch (direction) {
                case 0: // Swap blank tile with ABOVE.
                    slidingTilesBoard.swapTiles(row, col, row - 1, col);
                    break;
                case 1: // Swap blank tile with LEFT.
                    slidingTilesBoard.swapTiles(row, col, row, col - 1);
                    break;
                case 2: // Swap blank tile with BELOW.
                    slidingTilesBoard.swapTiles(row, col, row + 1, col);
                    break;
                default: // Swap blank tile with RIGHT.
                    slidingTilesBoard.swapTiles(row, col, row, col + 1);
                    break;
            }
        }
    }

}
