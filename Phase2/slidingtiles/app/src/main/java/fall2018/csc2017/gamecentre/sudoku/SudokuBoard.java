package fall2018.csc2017.gamecentre.sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import fall2018.csc2017.gamecentre.R;

public class SudokuBoard implements Serializable {

    /**
     * A random class
     */
    private Random rand = new Random();

    /**
     * The solution for the sudoku
     */
    private SudokuGrid[][] solutionSudoku;

    /**
     * A puzzle created from the solution of the sudoku.
     */
    private SudokuGrid[][] puzzleSudoku;

    /**
     * A clone initial puzzle
     */
    private SudokuGrid[][] clonePuzzle;

    /**
     * A string list of the sudokus that will be used to create solution
     */
    private final String[][][] seedSudokus = {{
            {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
            {"4", "5", "6", "7", "8", "9", "1", "2", "3"},
            {"7", "8", "9", "1", "2", "3", "4", "5", "6"},
            {"2", "1", "4", "3", "6", "5", "8", "9", "7"},
            {"3", "6", "5", "8", "9", "7", "2", "1", "4"},
            {"8", "9", "7", "2", "1", "4", "3", "6", "5"},
            {"5", "3", "1", "6", "4", "2", "9", "7", "8"},
            {"6", "4", "2", "9", "7", "8", "5", "3", "1"},
            {"9", "7", "8", "5", "3", "1", "6", "4", "2"}},
            {{"3", "9", "4", "5", "1", "7", "6", "2", "8"},
                    {"5", "1", "7", "6", "2", "8", "3", "9", "4"},
                    {"6", "2", "8", "3", "9", "4", "5", "1", "7"},
                    {"9", "3", "5", "4", "7", "1", "2", "8", "6"},
                    {"4", "7", "1", "2", "8", "6", "9", "3", "5"},
                    {"2", "8", "6", "9", "3", "5", "4", "7", "1"},
                    {"1", "4", "3", "7", "5", "9", "8", "6", "2"},
                    {"7", "5", "9", "8", "6", "2", "1", "4", "3"},
                    {"8", "6", "2", "1", "4", "3", "7", "5", "9"}}};

    /**
     * Constrcut a SudokuBoard
     * @param level a int that indicates how many empty spot will be in the puzzle sudoku
     */
    public SudokuBoard(int level) {
        this.solutionSudoku = getSudoku(getNewSudoku());
        this.puzzleSudoku = createPuzzle(level, this.solutionSudoku);
        this.clonePuzzle = cloneSudoku(puzzleSudoku);
    }

    /**
     * Return the puzzleSudoku.
     * @return the puzzleSudoku
     */
    public SudokuGrid[][] getPuzzleSudoku() {
        return puzzleSudoku;
    }

    /**
     * Return the solutionSudoku
     * @return the solutionSudoku
     */
    public SudokuGrid[][] getSolutionSudoku() {
        return solutionSudoku;
    }

    /**
     * Return  a new sudoku by randomly getting a sudoku in the sudokulists.
     * @return a new Sudoku
     */
    private String[][] getNewSudoku() {
        ArrayList<String[][]> matrixs = new ArrayList<>();
        String[][] sample = getSampleSudoku();
        for (int times = 0; times != 10; times++) {
            matrixs.add(swapColumn(sample));
            matrixs.add(swapDia(sample));
            matrixs.add(swapLine(sample));
            matrixs.add(swapNumber(sample));
        }
        return matrixs.get(rand.nextInt(matrixs.size()));
    }

    /**
     * Return a String lists that swap two number in the diagnal of the sample String lists.
     * @param sample a sample String lists
     */
    private String[][] swapDia(String[][] sample) {
        String[][] dia = new String[9][9];
        for (int i = 0; i != 9; i++) {
            for (int j = 0; j != 9; j++) {
                dia[i][j] = sample[j][i];
            }
        }
        return dia;
    }

    /**
     * Return a String lists that swap two number of the sample String lists.
     * @param sample a sample String lists
     */
    private String[][] swapNumber(String[][] sample) {
        for (int times = 0; times != 8; times++) {
            String changeNum = Integer.toString(rand.nextInt(9) + 1);
            String Num = Integer.toString(rand.nextInt(9) + 1);
            for (int i = 0; i != 9; i++) {
                for (int j = 0; j != 9; j++) {
                    if (sample[i][j].equals(Num)) {
                        sample[i][j] = changeNum;
                    } else if (sample[i][j].equals(changeNum)) {
                        sample[i][j] = Num;
                    }
                }
            }
        }
        return sample;
    }


    /**
     * Return a String lists that swap the number in valid row of the sample String lists.
     * @param sample a sample String lists
     */
    private String[][] swapLine(String[][] sample) {
        for (int i = 0; i != 5; i++) {
            int row = rand.nextInt(9);
            int changeRow = validChange(row);
            String[] a = sample[row];
            sample[row] = sample[changeRow];
            sample[changeRow] = a;
        }
        return sample;
    }

    /**
     * Return a String lists that swap the number in valid column of the sample String lists.
     * @param sample a sample String lists
     */
    private String[][] swapColumn(String[][] sample) {
        for (int i = 0; i != 5; i++) {
            int col = rand.nextInt(9);
            int changeCol = validChange(col);
            for (int row = 0; row != 9; row++) {
                String a = sample[row][col];
                sample[row][col] = sample[row][changeCol];
                sample[row][changeCol] = a;
            }
        }
        return sample;
    }


    /**
     * Return a int that represents the changable index of column or row depends on i.
     * @param i the index of rows or columns
     */
    private int validChange(int i) {
        if (i % 3 == 0) {
            return rand.nextInt(2) + (i + 1);
        } else if ((i + 2) % 3 == 0) {
            return rand.nextInt(3) + (i - 1);
        } else {
            return rand.nextInt(2) + (i - 2);
        }
    }


    /**
     * Return a String lists of sudoku by choosing randomly from listsudokus.
     * @return String lists of sudoku
     */
    private String[][] getSampleSudoku() {
        int random = rand.nextInt(seedSudokus.length);
        return seedSudokus[random].clone();
    }

    /**
     * Return a lists of Sudokugrids by clone the number in clone in the corresponding position
     * and set up the default background.
     * @param clone the String lists of sudoku to clone
     */
    private static SudokuGrid[][] getSudoku(String[][] clone) {
        SudokuGrid[][] sample = new SudokuGrid[9][9];
        for (int i = 0; i != 9; i++) {
            for (int j = 0; j != 9; j++) {
                SudokuGrid grid = new SudokuGrid(R.drawable.custom_button, clone[i][j]);
                sample[i][j] = grid;
            }
        }
        return sample;
    }

    /**
     * Return the implemented sudoku board a puzzle.
     * @param num the number of boxes that are empty.
     */

    public SudokuGrid[][] createPuzzle(int num, SudokuGrid[][] solution) {
        SudokuGrid[][] puzzle = cloneSudoku(solution);
        for (int i = 0; i != 9; i++) {
            for (int j = 0; j != num; j++) {
                int x = rand.nextInt(9);
                puzzle[i][x].setNumber("");
                puzzle[i][x].setBackground(R.drawable.custom_button_empty);
            }
        }
        return puzzle;
    }

    public SudokuGrid[][] getClonePuzzle() {
        return clonePuzzle;
    }

    /**
     * Return a clone sudoku based on given sudoku
     * @param sudoku original sudoku
     * @return a clone sudoku
     */
    private SudokuGrid[][] cloneSudoku(SudokuGrid[][] sudoku){
        SudokuGrid[][] clone = new SudokuGrid[9][9];
        for (int i = 0; i != 9; i++) {
            for (int j = 0; j != 9; j++){
                SudokuGrid current = sudoku[i][j];
                clone[i][j] = new SudokuGrid(current.getBackground(), current.getNumber());
            }
        }
        return clone;
    }
}


