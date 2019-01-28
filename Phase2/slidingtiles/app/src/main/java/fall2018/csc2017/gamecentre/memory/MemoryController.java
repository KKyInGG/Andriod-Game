package fall2018.csc2017.gamecentre.memory;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.GameController;
import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.PhaseTwoObserver;
import fall2018.csc2017.gamecentre.PhaseTwoSubject;
import fall2018.csc2017.gamecentre.Scoreboard;

public class MemoryController implements GameController, PhaseTwoSubject {

    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * The SlidingTilesManager that is being controlled
     */
    private MemoryManager memoryManager;

    /**
     * Create a new SlidingTilesController
     */
    public MemoryController(){
        observers = new ArrayList<>();
    }

    /**
     * Return the current game manager
     * @return memoryManager
     */
    @Override
    public MemoryManager getGameManager() {
        return memoryManager;
    }

    /**
     * Set memory game manager
     * @param manager the game manager
     */
    @Override
    public void setGameManager(GameManager manager) {
        this.memoryManager = (MemoryManager) manager;
    }

    /**
     * Set up Memory game according to level
     * @param level given level
     */
    @Override
    public void setUpBoard(String level) {
        switch (level) {
            case "Easy":
                memoryManager = new MemoryManager(2, 2);
                break;
            case "Medium":
                memoryManager = new MemoryManager(4, 4);
                break;
            default:
                memoryManager = new MemoryManager(6, 6);
                break;
        }
        notifyObservers();
    }


    /**
     * Add a score to the scoreboard iff the game is finished
     * @param scoreboard memory controller scoreboard
     * @param user name of current player
     */
    @Override
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if (memoryManager.isGameOver()) {
            scoreboard.addScore(user, memoryManager.getScore());
            memoryManager = null;
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




