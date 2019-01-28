package fall2018.csc2017.gamecentre;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.gamecentre.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.gamecentre.slidingtiles.Tile;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SlidingTilesManagerTest {

    /** The board manager for testing. */
    SlidingTilesManager slidingTilesManager;

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

    /**
     * Set up a SlidingTilesBoard to manipulate in testing
     * @return SlidingTilesBoard
     */
    private SlidingTilesBoard setUpTestBoard(){
        List<Tile> tiles = new ArrayList<>();
        for(int tileNum = 0; tileNum !=3; tileNum++){
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(6));
        tiles.add(new Tile(3));
        tiles.add(new Tile(24));
        tiles.add(new Tile(5));
        tiles.add(new Tile(4));
        tiles.add(new Tile(7));
        return new SlidingTilesBoard(tiles, 3, 3);
    }

    /**
     * Make a solved SlidingTilesBoard.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles(4,4);
        SlidingTilesBoard slidingTilesBoard = new SlidingTilesBoard(tiles, 4, 4);
        slidingTilesManager = new SlidingTilesManager(slidingTilesBoard);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        slidingTilesManager.getSlidingTilesBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        Assert.assertEquals(true, slidingTilesManager.isGameOver());
        swapFirstTwoTiles();
        Assert.assertEquals(false, slidingTilesManager.isGameOver());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        Assert.assertEquals(1, slidingTilesManager.getSlidingTilesBoard().getTile(0, 0).getId());
        Assert.assertEquals(2, slidingTilesManager.getSlidingTilesBoard().getTile(0, 1).getId());
        slidingTilesManager.getSlidingTilesBoard().swapTiles(0, 0, 0, 1);
        Assert.assertEquals(2, slidingTilesManager.getSlidingTilesBoard().getTile(0, 0).getId());
        Assert.assertEquals(1, slidingTilesManager.getSlidingTilesBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        Assert.assertEquals(15, slidingTilesManager.getSlidingTilesBoard().getTile(3, 2).getId());
        Assert.assertEquals(25, slidingTilesManager.getSlidingTilesBoard().getTile(3, 3).getId());
        slidingTilesManager.getSlidingTilesBoard().swapTiles(3, 3, 3, 2);
        Assert.assertEquals(25, slidingTilesManager.getSlidingTilesBoard().getTile(3, 2).getId());
        Assert.assertEquals(15, slidingTilesManager.getSlidingTilesBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect();
        Assert.assertEquals(true, slidingTilesManager.isValidTap(11));
        Assert.assertEquals(true, slidingTilesManager.isValidTap(14));
        Assert.assertEquals(false, slidingTilesManager.isValidTap(10));
    }

    /**
     * Test that SlidingTilesManager can properly move a tile either up, down, left, right given it's position.
     */
    @Test
    public void testTouchMove(){
        slidingTilesManager = new SlidingTilesManager(setUpTestBoard());
        slidingTilesManager.touchMove(8);
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(1,2).getId(), 8);
        slidingTilesManager.touchMove(7);
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(2,2).getId(), 5);
        slidingTilesManager.touchMove(4);
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(2,1).getId(), 4);
        slidingTilesManager.touchMove(5);
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(1,1).getId(), 8);
    }

    /**
     * Test that SlidingTilesManager can properly undo a move either up, down, left, right.
     */
    @Test
    public void testTryUndo(){
        slidingTilesManager = new SlidingTilesManager(setUpTestBoard());
        slidingTilesManager.touchMove(8);
        slidingTilesManager.touchMove(7);
        slidingTilesManager.touchMove(4);
        slidingTilesManager.touchMove(5);
        slidingTilesManager.setNumUndos(4);
        slidingTilesManager.tryUndo();
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(1,2).getId(),8);
        slidingTilesManager.tryUndo();
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(1,1).getId(),4);
        slidingTilesManager.tryUndo();
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(2,1).getId(),5);
        slidingTilesManager.tryUndo();
        Assert.assertEquals(slidingTilesManager.getSlidingTilesBoard().getTile(2,2).getId(),8);
    }


    /**
     * Test that this SlidingTilesManager can return score
     */
    @Test
    public void testGetScore(){
        setUpCorrect();
        Assert.assertEquals(0,slidingTilesManager.getScore());
    }

}

