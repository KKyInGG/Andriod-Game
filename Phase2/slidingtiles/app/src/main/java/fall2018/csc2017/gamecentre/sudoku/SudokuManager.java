package fall2018.csc2017.gamecentre.sudoku;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Observable;

import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.R;

public class SudokuManager extends Observable implements GameManager,Serializable{

    /**
     * A SudokuBoard
     */
    private SudokuBoard sudokuBoard;

    /**
     * The Score
     */
    private int score = 0;

    /**
     *  The Stack that restores all the undoPositions
     */
    private Stack<Integer> undoPositionStack;

    /**
     * The number to fill in the puzzleSudoku.
     */
    private String numberToFill = "";

    /**
     * A list of the original background.
     */
    private ArrayList<Integer> backgrounds = new ArrayList<>();


    /**
     * Constrcutor for SudokuManger depends on the level.
     * @param num the level of Sudoku puzzle.
     */
    public SudokuManager(int num){
        super();
        this.sudokuBoard = new SudokuBoard(num);
        this.undoPositionStack = new Stack<>();
    }

    /**
     * Return the number the fill
     * @return int number to fill
     */
    public String getNumberToFill() {
        return this.numberToFill;
    }


    /**
     * Return the backgrounds' id.
     * @return backgrounds
     */
    public ArrayList<Integer> getBackgrounds() {
        return backgrounds;
    }

    /**
     * Return the undoPositionStack
     */
    public Stack<Integer> getUndoPositionStack() {
        return undoPositionStack;
    }

    /**
     * Set the String number to fill
     * @param numberToFill
     */
    public void setNumberToFill(String numberToFill) {
        this.numberToFill = numberToFill;
    }

    /**
     * Return true iff get the empty spot with index int row x and int column y in the puzzles.
     * @param x the row
     * @param y the col
     */
    public boolean getEmptySpot(int x, int y){
        return (sudokuBoard.getClonePuzzle()[x][y]).getNumber().equals("");
    }

    /**
     * Return the score of the game
     * @return score
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * Return whether or not the sudoku board is all filled out
     * @return is the sudoku board full
     */
    @Override
    public boolean isGameOver() {
        int correct = 0;
        for (int i = 0; i != 9; i++){
            for (int j = 0; j != 9; j++){
                if (this.sudokuBoard.getPuzzleSudoku()[i][j].getNumber().equals(sudokuBoard.getSolutionSudoku()[i][j].getNumber())){
                    correct ++;
                }
            }
        }
        return correct == 81;
    }

    /**
     * Return whether the position is an empty spot.
     * @param position spot to check
     * @return whether the spot is empty
     */
    @Override
    public boolean isValidTap(int position) {
        int row = position / 9;
        int col = position % 9;
        return getEmptySpot(row,col);
    }

    /**
     * Make the move according to int position
     * @param position int position
     */
    @Override
    public void touchMove(int position) {
        int x = position / 9;
        int y = position % 9;
        (sudokuBoard.getPuzzleSudoku()[x][y]).setNumber(this.numberToFill);
        this.undoPositionStack.push(x);
        this.undoPositionStack.push(y);
        this.numberToFill = "";
        score++;
        setChanged();
        notifyObservers();
    }

    /**
     * Return the puzzle sudoku to the state one step before
     */
    public void tryUndo() {
        int y = undoPositionStack.pop();
        int x = undoPositionStack.pop();
        this.sudokuBoard.getPuzzleSudoku()[x][y].setNumber("");
        score++;
        setChanged();
        notifyObservers();
    }

    /**
     * Return true iff there are no numbers equals to the number to fill in the row
     * @param row the row selected
     */
    private boolean checkSelectedRow(int row){
        int wrongRow = 0;
        for (SudokuGrid sudokuGrid : sudokuBoard.getPuzzleSudoku()[row]){
            if (sudokuGrid.getNumber().equals(numberToFill)){
                wrongRow ++;
            }
        }
        return wrongRow != 0;
    }

    /**
     * Return true iff there are no numbers equals to the number to fill in the column
     * @param col the column selected
     */
    private boolean checkSelectedColumn(int col){
        int wrongCol = 0;
        for (int i = 0; i != 9; i++){
            if (sudokuBoard.getPuzzleSudoku()[i][col].getNumber().equals(numberToFill)){
                wrongCol ++;
            }
        }
        return wrongCol != 0;
    }


    /**
     * Return the sudokuBoard.
     * @return sudokuBoard
     */
    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    /**
     * Return true iff there are no numbers equals to the number to fill in the corresponding square
     * @param row,col the row and column selected
     */
    private boolean checkSelectedSquare(int row, int col){
        int beginRow = (row/3) * 3;
        int beginCol = (col/3) * 3;
        int wrongSquare = 0;
        for(int i = beginRow; i < (beginRow + 3); i++){
            for(int j = beginCol ;j < (beginCol + 3); j++){
                if (sudokuBoard.getPuzzleSudoku()[i][j].getNumber().equals(numberToFill)){
                    wrongSquare ++;
                }
            }
        }
        return wrongSquare != 0;
    }

    /**
     * Return true iff the number to fill in can fill in the selected position.
     * @param position the position selected
     */
    public boolean checkRepeated(int position){
        int x = position / 9;
        int y = position % 9;
        return checkSelectedColumn(y) || checkSelectedRow(x) || checkSelectedSquare(x, y);
    }

    /**
     * Set the background to the selectedBackground
     * @param row int row
     * @param col int column
     * @param puzzle the puzzle sudoku
     */

    private void setSelectedBackground(int row, int col, SudokuGrid[][] puzzle){
        setSelectedRow(row,puzzle);
        setSelectedColoumn(col,puzzle);
        setSelectedSquare(row,col,puzzle);
        sudokuBoard.getPuzzleSudoku()[row][col].setBackground(R.drawable.button_selected);
    }


    /**
     * Set up the background of the index row of the puzzle
     * @param row int row
     * @param puzzle the sudokugrid puzzle
     */
    private void setSelectedRow(int row, SudokuGrid[][] puzzle){
        for (SudokuGrid sudokuGrid : puzzle[row]){
            sudokuGrid.setBackground(R.drawable.button_pressed);
        }
    }

    /**
     * Set up the background of the index column of the puzzle
     * @param col int column
     * @param puzzle the sudokugrid puzzle
     */
    private void setSelectedColoumn(int col, SudokuGrid[][] puzzle){
        for (int i = 0; i != 9; i++){
            puzzle[i][col].setBackground(R.drawable.button_pressed);
        }
    }

    /**
     * Set up the selected square according to the row and the col with the sudokupuzzle
     * @param row int row
     * @param col int col
     * @param puzzle the sudokugrid puzzle
     */
    private void setSelectedSquare(int row, int col, SudokuGrid[][] puzzle){
        int beginRow = (row/3) * 3;
        int beginCol = (col/3) * 3;
        for(int i = beginRow; i < (beginRow + 3); i++){
            for(int j = beginCol ;j < (beginCol + 3); j++){
                puzzle[i][j].setBackground(R.drawable.button_pressed);
            }

        }
    }

    /**
     * Set up the backgrounds instance variable.
     */
    public void setUpBackgrounds(){
        if (backgrounds.size() == 0) {
            for (int i = 0; i != 9; i++) {
                for (int j = 0; j != 9; j++) {
                    int background = sudokuBoard.getPuzzleSudoku()[i][j].getBackground();
                    backgrounds.add(background);

                }
            }
        }
    }

    /**
     * Set up the background according to position
     * @param position the posiiton
     */
    public void setUpBackground(int position){
        int row = position / 9;
        int col = position % 9;
        setSelectedBackground(row,col,sudokuBoard.getPuzzleSudoku());
        setChanged();
        notifyObservers();
    }

    /**
     * Set the background to original puzzle's background.
     * @param puzzle the  puzzle
     */
    public void setOriginal(SudokuGrid[][] puzzle){
        for (int i = 0; i != 9; i++){
            for(int j = 0; j != 9; j++){
                puzzle[i][j].setBackground(backgrounds.get(i * 9 + j));
            }
        }
    }
}