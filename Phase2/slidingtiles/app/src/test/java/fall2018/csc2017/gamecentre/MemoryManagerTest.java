package fall2018.csc2017.gamecentre;



import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.memory.MemoryBoard;
import fall2018.csc2017.gamecentre.memory.MemoryManager;
import fall2018.csc2017.gamecentre.memory.Pairs;

public class MemoryManagerTest {

    /** The memory manager for testing **/
    MemoryManager manager;

    /**
     * Set up an ordered memory board with given dimensions
     * @param rows dimension
     * @param cols dimension
     */
    private void setUp(int rows, int cols){
        List<Pairs> pairs = new ArrayList<>();
        int numTiles = rows * cols;
        int numPairs = numTiles / 2;
        for (int PairsId = 0; PairsId != numPairs; PairsId++) {
            pairs.add(new Pairs(PairsId));
            pairs.add(new Pairs(PairsId));
        }
        manager = new MemoryManager(new MemoryBoard(pairs, rows, cols));
    }


    /**
     * Test if the MemoryManager can differentiate between a valid and non valid tap
     */
    @Test
    public void testIsValidTap(){
        setUp(4,4);
        manager.getMemoryBoard().flipCard(1);
        Assert.assertFalse(manager.isValidTap(1));
        Assert.assertTrue(manager.isValidTap(2));
    }

    /**
     * Test the sequence of events for when the user selects two cards that are not a match.
     */
    @Test
    public void testTouchMoveNoMatch(){
        setUp(2,2);
        manager.touchMove(0);
        Assert.assertEquals(1, manager.getFlipCount());
        Assert.assertEquals(0, manager.getFirstFlippedPosition());
        manager.touchMove(2);
        Assert.assertEquals(2,manager.getFlipCount());
        Assert.assertEquals(2, manager.getSecondFlippedPosition());
        manager.touchMove(1);
        Pairs blank = new Pairs(18);
        Assert.assertEquals(blank.getId(), manager.getMemoryBoard().getPairs(0).getId());
        Assert.assertEquals(blank.getId(), manager.getMemoryBoard().getPairs(2).getId());
        Assert.assertEquals(1, manager.getScore());
    }

    /**
     * Test the sequence of events when the user selects two cards that are a match
     */
    @Test
    public void testTouchMoveMatch(){
        setUp(2,2);
        manager.touchMove(0);
        manager.touchMove(1);
        manager.touchMove(2);
        Assert.assertEquals(1, manager.getNumMatches());
    }

    /**
     * Test isGameOver on an unifished game should return false
     */
    @Test
    public void testGameNotOver(){
        setUp(2,2);
        Assert.assertFalse(manager.isGameOver());
    }

    /**
     * Test isGaveOver on a finished game should return true.
     */
    @Test
    public void testGameOver(){
        setUp(2,2);
        manager.touchMove(0);
        manager.touchMove(1);
        manager.touchMove(2);
        manager.touchMove(3);
        Assert.assertTrue(manager.isGameOver());
    }
}
