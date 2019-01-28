package fall2018.csc2017.gamecentre.memory;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.gamecentre.GameManager;

public class MemoryManager implements GameManager, Serializable {

    /**
     * the memory board to be managed
     */
    private MemoryBoard board;

    /**
     * the amount of cards being flipped
     */
    private int flipCount;

    /**
     * the position of the flipped first card
     */
    private int firstFlippedPosition;

    /**
     * the position of the second flipped card
     */
    private int secondFlippedPosition;

    /**
     * the score of the current Memory game
     */
    private int score;

    /**
     * the amount of matches on the memory board
     */
    private int numMatches = 0;

    /**
     * Create a new Memory Manager with given board dimesnions rows and cols
     * @param rows amount of rows on the board
     * @param cols amount of cols on the board
     */
    public MemoryManager(int rows, int cols) {
        List<Pairs> pairs = new ArrayList<>();
        int numTiles = rows * cols;
        int numPairs = numTiles / 2;
        for (int PairsId = 0; PairsId != numPairs; PairsId++) {
            pairs.add(new Pairs(PairsId));
            pairs.add(new Pairs(PairsId));
        }
        Collections.shuffle(pairs);
        this.board = new MemoryBoard(pairs, rows, cols);
        flipCount = 0;
    }

    /**
     * Create a MemoryManager with given board
     * @param board to manage
     */
    public MemoryManager(MemoryBoard board){
        this.board = board;
        flipCount = 0;
    }

    /**
     * Return the position of the first card that got flipped
     * @return firstFlippedPosition
     */
    public int getFirstFlippedPosition() {
        return firstFlippedPosition;
    }

    /**
     * Return the position of the second card that got flipped
     * @return secondFlippedPosition
     */
    public int getSecondFlippedPosition() {
        return secondFlippedPosition;
    }

    /**
     * Return the amount of cards that have been flipped
     * @return flipCount
     */
    public int getFlipCount() {
        return flipCount;
    }

    /**
     * Return true iff the card has not already been flipped and the flipcount is under 3
     * @param position of card that user wants to flip
     * @return boolean
     */
    @Override
    public boolean isValidTap(int position) {
        return board.getPairs(position).getId() == 19 && flipCount < 3;
    }

    /**
     * Flip over the selected card.
     * @param position of card to flip
     */
    @Override
    public void touchMove(int position) {
        board.flipCard(position);
        flipCount = flipCount + 1;
        if (flipCount == 1) {
            this.firstFlippedPosition = position;
        } else if(flipCount == 2) {
            this.secondFlippedPosition = position;
        }
        else{
            if (board.getPairs(firstFlippedPosition).getId() != board.getPairs(secondFlippedPosition).getId()) {
                board.unFlipCard(firstFlippedPosition);
                board.unFlipCard(secondFlippedPosition);
            }
            else { numMatches++; }
            flipCount = 0;
            score++;
            touchMove(position);
        }
    }

    /**
     * Return the number of matches
     * @return numMatches
     */
    public int getNumMatches() {
        return numMatches;
    }

    /**
     * Return true iff all cards have found it's pair
     * @return boolean
     */
    @Override
    public boolean isGameOver(){
        Pairs[][] solution = board.getSolutionPairs();
        Pairs[][] puzzle = board.getPairsPuzzle();
        boolean over = true;
        for(int i = 0; i<board.getPairsPuzzle().length; i++){
            for(int j = 0; j<board.getPairsPuzzle()[0].length; j++)
            if(solution[i][j] != puzzle[i][j]){
                over = false;
            }
        }
        return over;
    }

    /**
     * Return the score of the current Memory game
     * @return score
     */
    @Override
    public int getScore(){
        return this.score;
    }

    /**
     * Return the board of the cureent emory game
     * @return MemoryBoard
     */
    public MemoryBoard getMemoryBoard(){return this.board;}

}




