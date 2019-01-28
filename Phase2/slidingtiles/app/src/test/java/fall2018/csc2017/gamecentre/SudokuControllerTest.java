package fall2018.csc2017.gamecentre;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;


import fall2018.csc2017.gamecentre.sudoku.SudokuController;
import fall2018.csc2017.gamecentre.sudoku.SudokuManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SudokuControllerTest {

    /** The SudukoController to be tested**/
    SudokuController controller;

    /**
     * Test that this controller properly registers a PhaseTwoObserver
     */
    @Test
    public void testSetUpBoard(){
        controller = new SudokuController();
        controller.setUpBoard("Easy");
        int easy = 0;
        for(int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                if (controller.getGameManager().getEmptySpot(row, col)) {
                    easy++;
                }
            }
        }
        Assert.assertTrue(easy <= 27);
        Assert.assertTrue(easy >= 9);
        controller.setUpBoard("Medium");
        int medium = 0;
        for(int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                if (controller.getGameManager().getEmptySpot(row, col)) {
                    medium++;
                }
            }
        }
        Assert.assertTrue(medium <= 45);
        Assert.assertTrue(medium >= 9);
        controller.setUpBoard("Hard");
        int hard = 0;
        for(int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                if (controller.getGameManager().getEmptySpot(row, col)) {
                    hard++;
                }
            }
        }
        Assert.assertTrue(hard <= 63);
        Assert.assertTrue(hard >= 9);

    }

    @Test
    public void testRegister() {
        controller = new SudokuController();
        boolean thrownNull = false;
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        try {
            controller.register(observer);
            verify(observer).setSubject(controller);
            controller.register(null);
        } catch (NullPointerException e) {
            thrownNull = true;
        }
        Assert.assertTrue(thrownNull);
    }

    /**
     * Test that this contoller will not add a score to the scoreboard when game is not finished
     */
    @Test
    public void testCheckToAddScoreNotFinished(){
        controller = new SudokuController();
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        SudokuManager sudokuManager = mock(SudokuManager.class);
        when(sudokuManager.isGameOver()).thenReturn(false);
        controller.setGameManager(sudokuManager);
        controller.checkToAddScore(scoreboard, user);
        Assert.assertEquals(scoreboard.getGlobalScoreboard().size(), 0);
    }

    /**
     * Test that this contoller will add a score to the scoreboard when game is finished
     */
    @Test
    public void testCheckToAddScore(){
        controller = new SudokuController();
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        SudokuManager sudokuManager = mock(SudokuManager.class);
        when(sudokuManager.isGameOver()).thenReturn(true);
        when(sudokuManager.getScore()).thenReturn(25);
        controller.setGameManager(sudokuManager);
        controller.checkToAddScore(scoreboard, user);
        ArrayList<Score> actual = scoreboard.getGlobalScores();
        ArrayList<Score> expected = new ArrayList<>();
        expected.add((new Score("player1",25)));
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test that this controller properly notifies it's observers
     */
    @Test
    public void testNotifyObservers(){
        controller = new SudokuController();
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        controller.register(observer);
        controller.notifyObservers();
        verify(observer).update();
    }
}
