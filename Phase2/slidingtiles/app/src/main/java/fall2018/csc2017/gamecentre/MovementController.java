package fall2018.csc2017.gamecentre;

import android.content.Context;


/**
 * A Movement Controller Interface.
 */
public interface MovementController {

    /**
     * Display a new View with context after processing a tap with position
     * @param context the context
     * @param position the position
     * @param display a display
     */
     void processTapMovement(Context context, int position, boolean display);

    /**
     * Set up the GameManager with manger
     * @param manager a GameManager
     */
    void setGameManager(GameManager manager);
}
