package fall2018.csc2017.gamecentre.slidingtiles;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.GameController;
import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.PhaseTwoObserver;
import fall2018.csc2017.gamecentre.PhaseTwoSubject;
import fall2018.csc2017.gamecentre.Scoreboard;


/**
 * Control the view and model of a Sliding tiles game.
 */
public class SlidingTilesController implements PhaseTwoSubject, GameController {


    /**
     * The amount of undos in a sliding tiles game
     */
    private int NumUndos = 3;

    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * The SlidingTilesManager that is being controlled
     */
    private SlidingTilesManager slidingTilesManager;

    /**
     * Create a new SlidingTilesController
     */
    public SlidingTilesController (){
        observers = new ArrayList<>();
    }

    /**
     * Set the amount of undos available in the slidingtiles game
     * @param numUndos the amount of undos
     */
    public void setNumUndos(int numUndos) {
        NumUndos = numUndos;
    }

    /**
     * Return the number of undos left
     * @return numUndos
     */
    public int getNumUndos() {
        return NumUndos;
    }


    /**
     * Return the manager for sliding tiles
     * @return SlidingTilesManager
     */
    @Override
    public SlidingTilesManager getGameManager() {
        return slidingTilesManager;
    }

    /**
     * Set a new slidingTilesManager for this class
     * @param slidingTilesManager
     */
    @Override
    public void setGameManager(GameManager slidingTilesManager) {
        this.slidingTilesManager = (SlidingTilesManager) slidingTilesManager;
    }

    /**
     * Set up a slidingtiles game in accordance with the selected level
     * @param level the level the user has selected
     */
    @Override
    public void setUpBoard(String level) {
        switch (level) {
            case "Easy":
                slidingTilesManager = new SlidingTilesManager(3, 3);
                break;
            case "Medium":
                slidingTilesManager = new SlidingTilesManager(4, 4);
                break;
            default:
                slidingTilesManager = new SlidingTilesManager(5, 5);
                break;
        }
        notifyObservers();
    }


    /**
     * Add a score to the scoreboard iff the game is finished
     * @param scoreboard for sliding tiles
     * @param user name of current player
     */
    @Override
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if (slidingTilesManager.isGameOver()) {
            scoreboard.addScore(user, slidingTilesManager.getScore());
            slidingTilesManager = null;
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Add an observer, obj, to this class
     * @param obj The observer to be added
     */
    @Override
    public void register(PhaseTwoObserver obj){
        if(!observers.contains(obj))
        {observers.add(obj);
            obj.setSubject(this);}
    }

    /**
     * Update the observers of this class
     */
    @Override
    public void notifyObservers(){
        for (PhaseTwoObserver obj : observers) {
            obj.update();
        }
    }
}
