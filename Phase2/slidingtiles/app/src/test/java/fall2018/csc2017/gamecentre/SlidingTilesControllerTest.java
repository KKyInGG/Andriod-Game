package fall2018.csc2017.gamecentre;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.gamecentre.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.gamecentre.slidingtiles.SlidingTilesController;
import fall2018.csc2017.gamecentre.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.gamecentre.slidingtiles.Tile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the sliding tiles scoreboard.
 *
 * Following methods at:
 * - http://d.android.com/tools/testing
 * - https://developer.android.com/training/testing/unit-testing/local-unit-tests
 */
public class SlidingTilesControllerTest {

    /**
     * The SlidingTilesController being tested
     */
    SlidingTilesController controller = new SlidingTilesController();

    /**
     * Make a solved SlidingTilesBoard.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles(4,4);
        SlidingTilesBoard slidingTilesBoard = new SlidingTilesBoard(tiles, 4, 4);
        SlidingTilesManager slidingTilesManager = new SlidingTilesManager(slidingTilesBoard);
        controller.setGameManager(slidingTilesManager);
    }

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles(int row, int col) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = row * col;
        for (int tileNum = 0; tileNum != numTiles -1; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(24));

        return tiles;
    }


    /** Test this SlidingTilesController can properlly set up a game given the level of difficulty**/
    @Test
    public void testSetUpSlidingTiles(){
        controller.setUpBoard("Easy");
        int boardDimensionEasy = controller.getGameManager().getSlidingTilesBoard().getNUM_ROWS() * controller.getGameManager().getSlidingTilesBoard().getNUM_COLS();
        Assert.assertEquals(9, boardDimensionEasy);
        Assert.assertEquals(3, controller.getGameManager().getNumUndos());
        controller.setUpBoard("Medium");
        int boardDimensionMedium = controller.getGameManager().getSlidingTilesBoard().getNUM_ROWS() * controller.getGameManager().getSlidingTilesBoard().getNUM_COLS();
        Assert.assertEquals(16, boardDimensionMedium);
        controller.setUpBoard("Hard");
        int boardDimensionHard = controller.getGameManager().getSlidingTilesBoard().getNUM_ROWS() * controller.getGameManager().getSlidingTilesBoard().getNUM_COLS();
        Assert.assertEquals(25, boardDimensionHard);
    }

    /**
     * Test that slidingtiles will run out of undos
     */
    @Test
    public void testUndoCount(){
        SlidingTilesManager slidingTilesManager = mock(SlidingTilesManager.class);
        Random random = new Random();
        int numUndos = random.nextInt(6);
        slidingTilesManager.setNumUndos(numUndos);

        for (int i = 0; i < numUndos; i++) {
            slidingTilesManager.touchMove(random.nextInt(3));
        }
        for (int i = 0; i < numUndos; i++) {
            slidingTilesManager.tryUndo();
        }

        Assert.assertEquals(0, slidingTilesManager.getNumUndos());
    }

    /**
     * Test that this contoller will add a score to the scoreboard when game is finished
     */
    @Test
    public void testCheckToAddScore(){
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        SlidingTilesManager slidingTilesManager = mock(SlidingTilesManager.class);
        when(slidingTilesManager.isGameOver()).thenReturn(true);
        when(slidingTilesManager.getScore()).thenReturn(25);
        controller.setGameManager(slidingTilesManager);
        controller.checkToAddScore(scoreboard, user);
        ArrayList<Score> actual = scoreboard.getGlobalScores();
        ArrayList<Score> expected = new ArrayList<>();
        expected.add((new Score("player1",25)));
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test that this controller won't add the game's score if game is not finished
     */
    @Test
    public void testCheckToAddScoreNotFinished(){
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        SlidingTilesManager slidingTilesManager = mock(SlidingTilesManager.class);
        when(slidingTilesManager.isGameOver()).thenReturn(false);
        controller.setGameManager(slidingTilesManager);
        controller.checkToAddScore(scoreboard, user);
        Assert.assertEquals(scoreboard.getGlobalScoreboard().size(), 0);
    }

    /**
     * Test that this controller properly registers a PhaseTwoObserver
     */
    @Test
    public void testRegister(){
        controller = new SlidingTilesController();
        boolean thrownNull = false;
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        try{
            controller.register(observer);
            verify(observer).setSubject(controller);
            controller.register(null);
        }catch(NullPointerException e){
            thrownNull = true;
        }
        Assert.assertTrue(thrownNull);
    }

    /**
     * Test that the number of undos is properly set
     */
    @Test
    public void testSetNumUndos(){
        controller = new SlidingTilesController();
        controller.setNumUndos(4);
        Assert.assertEquals(controller.getNumUndos(), 4);
    }

    /**
     * Test that this controller properly notifies it's observers
     */
    @Test
    public void testNotifyObservers(){
        controller = new SlidingTilesController();
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        controller.register(observer);
        controller.notifyObservers();
        verify(observer).update();
    }

}
