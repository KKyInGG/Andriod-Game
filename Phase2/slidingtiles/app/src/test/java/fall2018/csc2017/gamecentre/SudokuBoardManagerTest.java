package fall2018.csc2017.gamecentre;

import org.junit.Assert;
import org.junit.Test;

import fall2018.csc2017.gamecentre.sudoku.SudokuManager;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SudokuBoardManagerTest {

    /** The sudokuManager for testing. */
    private SudokuManager sudokuManager;


    /**
     * Set up the sudokuManager for testing
     */
    private void setUpSudokuManager(){
            sudokuManager = new SudokuManager(3);
    }


    /**
     * Test if the get empty spot return true iff we have an empty spot in sudokuPuzzle.
     */
    @Test
    public void testGetEmptySpot(){
        setUpSudokuManager();
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                if(sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].getNumber().equals(""))
                {
                    Assert.assertTrue(sudokuManager.getEmptySpot(row,col));
                }
                else{
                    Assert.assertFalse(sudokuManager.getEmptySpot(row,col));
                }
            }
        }
    }


    /**
     * Test if the number that customer given is filled in the puzzle sudoku after calling
     * touchMove() method. Also, the UndoStack will  record the position and the score will
     * add one.(one more step)
     */
    @Test
    public void testTouchMove() {
        setUpSudokuManager();
        sudokuManager.setNumberToFill("3");
        Assert.assertEquals(0, sudokuManager.getUndoPositionStack().size());
        Assert.assertEquals(0, sudokuManager.getScore());
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                int position = row * 9 + col;
                if (sudokuManager.getEmptySpot(row, col) && !(sudokuManager.checkRepeated(position))) {
                    sudokuManager.touchMove(position);
                    Assert.assertEquals("3", sudokuManager.
                            getSudokuBoard().getPuzzleSudoku()[row][col].getNumber());
                    Assert.assertEquals(2, sudokuManager.getUndoPositionStack().size());
                    Assert.assertEquals(col, (int) sudokuManager.getUndoPositionStack().pop());
                    Assert.assertEquals(row, (int) sudokuManager.getUndoPositionStack().pop());
                    Assert.assertEquals(1, sudokuManager.getScore());
                    Assert.assertEquals("", sudokuManager.getNumberToFill());
                    break;
                }
            }
        }

    }


    /**
     * Test if checkRepeated return true iff there is the same number
     * in the same row, col or square.
     */
    @Test
    public void testCheckRepeated(){
        setUpSudokuManager();
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                if (!(sudokuManager.getEmptySpot(row, col))) {
                    String num = sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].getNumber();
                    sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].setNumber("");
                    sudokuManager.setNumberToFill(num);
                    Assert.assertFalse(sudokuManager.checkRepeated(row * 9 + col));
                }
            }
        }
    }

    /**
     * Test if the instance variable backgrounds in sudokuManager will store all the original
     * background id for sudokuBoard after calling setupbackgrounds().
     */
    @Test
    public void testSetUpBackgrounds(){
        setUpSudokuManager();
        sudokuManager.setUpBackgrounds();
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                Assert.assertEquals(sudokuManager.getSudokuBoard().
                        getPuzzleSudoku()[row][col].getBackground(), (int) sudokuManager.
                        getBackgrounds().get(row * 9 + col));
            }
        }
    }

    /**
     * Test if the background id of each grid in puzzle sudoku is seted back to the
     * same as the original background id.
     */
    @Test
    public void testSetOriginal(){
        setUpSudokuManager();
        sudokuManager.setUpBackgrounds();
        sudokuManager.setUpBackground(3 * 9 + 3);
        sudokuManager.setOriginal(sudokuManager.getSudokuBoard().getPuzzleSudoku());
        for(int row = 0 ; row != 9; row++){
            for(int col = 0; col != 9; col++){
                Assert.assertEquals( sudokuManager.getSudokuBoard().
                        getClonePuzzle()[row][col].getBackground(),
                        sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].getBackground());
            }
        }

    }

    /**
     * Test if the background id changes according to which row, col and squares is the position
     * in.
     */
    @Test
    public void testSetUpBackground(){
        setUpSudokuManager();
        sudokuManager.setUpBackground(3 * 9 + 3);
        Assert.assertEquals( R.drawable.button_selected
                ,sudokuManager.getSudokuBoard().
                getPuzzleSudoku()[3][3].getBackground());
        for(int line = 0; line != 9; line ++){
            if(line != 3){
            Assert.assertEquals( R.drawable.button_pressed, sudokuManager.getSudokuBoard().
                    getPuzzleSudoku()[3][line].getBackground());
            Assert.assertEquals( R.drawable.button_pressed, sudokuManager.getSudokuBoard().
                    getPuzzleSudoku()[line][3].getBackground());}
        }
        for(int row = 3; row != 6; row ++){
            for(int col = 3; col != 6; col++){
                if(!(row == 3 && col == 3)){
                Assert.assertEquals(R.drawable.button_pressed, sudokuManager.getSudokuBoard().
                        getPuzzleSudoku()[row][col].getBackground());
                }
            }
        }
    }


    /**
     * Test if the isValidTap() method return true iff the position is an empty spot.
     */
    @Test
    public void testIsValidTap() {
        setUpSudokuManager();
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                if(sudokuManager.getEmptySpot(row,col)){
                    Assert.assertTrue(sudokuManager.isValidTap(row * 9 + col));
                }
                else{
                Assert.assertFalse(sudokuManager.isValidTap(row * 9 + col));}
            }
        }
    }

    /**
     * Test if the isGameOver() method return true iff the game finish.
     */
    @Test
    public void testIsGameOver(){
        setUpSudokuManager();
        Assert.assertFalse(sudokuManager.isGameOver());
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                if(sudokuManager.getEmptySpot(row,col)){
                    String number = sudokuManager.getSudokuBoard().getSolutionSudoku()[row][col].getNumber();
                    sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].setNumber(number);
                }
            }
        }
        Assert.assertTrue(sudokuManager.isGameOver());
    }

    /**
     * Test if the TryUndo() method will undo one step before.
     */
    @Test
    public void testTryUndo(){
        setUpSudokuManager();
        Assert.assertEquals(0, sudokuManager.getUndoPositionStack().size());
        sudokuManager.setNumberToFill("3");
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                int position = row * 9 + col;
                if (sudokuManager.getEmptySpot(row, col) && !(sudokuManager.checkRepeated(position))) {
                    sudokuManager.touchMove(position);
                    Assert.assertEquals(2, sudokuManager.getUndoPositionStack().size());
                    sudokuManager.tryUndo();
                    Assert.assertEquals(0, sudokuManager.getUndoPositionStack().size());
                    Assert.assertEquals("", sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].getNumber());
                }
            }
        }
    }
}
