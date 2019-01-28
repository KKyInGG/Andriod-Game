package fall2018.csc2017.gamecentre;

import junit.framework.Assert;
import fall2018.csc2017.gamecentre.memory.MemoryController;
import fall2018.csc2017.gamecentre.memory.MemoryManager;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemoryControllerTest {


    /** MemoryController to be tested **/
    MemoryController controller;

    /**
     * Test if MemoryController can register a PhaseTwoObserver
     */
    @Test
    public void testRegister() {
        controller = new MemoryController();
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
     * Test if the MemoryController can set up the correct board given a difficulty
     */
    @Test
    public void testSeutUpBoard() {
        controller = new MemoryController();
        controller.setUpBoard("Easy");
        int boardDimensionEasy = controller.getGameManager().getMemoryBoard().getNUM_ROWS() * controller.getGameManager().getMemoryBoard().getNUM_COLS();
        Assert.assertEquals(4, boardDimensionEasy);
        controller.setUpBoard("Medium");
        int boardDimensionMedium = controller.getGameManager().getMemoryBoard().getNUM_ROWS() * controller.getGameManager().getMemoryBoard().getNUM_COLS();
        Assert.assertEquals(16, boardDimensionMedium);
        controller.setUpBoard("Hard");
        int boardDimensionHard = controller.getGameManager().getMemoryBoard().getNUM_ROWS() * controller.getGameManager().getMemoryBoard().getNUM_COLS();
        Assert.assertEquals(36, boardDimensionHard);
    }

    /**
     * Test that checkToAddScore won't add the score of an unfinished game
     */
    @Test
    public void testCheckToAddScoreNotFinished(){
        controller = new MemoryController();
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        MemoryManager memoryManager = mock(MemoryManager.class);
        when(memoryManager.isGameOver()).thenReturn(false);
        controller.setGameManager(memoryManager);
        controller.checkToAddScore(scoreboard, user);
        Assert.assertEquals(scoreboard.getGlobalScoreboard().size(), 0);
    }

    /**
     * Test that checkToAddScore will add score from a finished game
     */
    @Test
    public void testCheckToAddScore(){
        controller = new MemoryController();
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        MemoryManager memoryManager = mock(MemoryManager.class);
        when(memoryManager.isGameOver()).thenReturn(true);
        when(memoryManager.getScore()).thenReturn(25);
        controller.setGameManager(memoryManager);
        controller.checkToAddScore(scoreboard, user);
        ArrayList<Score> actual = scoreboard.getGlobalScores();
        ArrayList<Score> expected = new ArrayList<>();
        expected.add((new Score("player1",25)));
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test that MemoryController properly notifies observers
     */
    @Test
    public void testNotifyObservers(){
        controller = new MemoryController();
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        controller.register(observer);
        controller.notifyObservers();
        verify(observer).update();
    }
}
