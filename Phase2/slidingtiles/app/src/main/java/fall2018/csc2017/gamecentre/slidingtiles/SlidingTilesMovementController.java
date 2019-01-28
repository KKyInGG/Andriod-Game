package fall2018.csc2017.gamecentre.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.MovementController;

/**
 * The movement controller for the sliding tiles game.
 * Sets up the game manager and processes taps on the screen.
 */
public class SlidingTilesMovementController implements MovementController {

    /**
     * A SudokuManager
     */
    private SlidingTilesManager slidingTilesManager  = null;

    /**
     * Initialize the SlidingTilesMovementController.
     */
    public SlidingTilesMovementController() {
    }

    @Override
    public void setGameManager(GameManager slidingTilesManager) {
        this.slidingTilesManager = (SlidingTilesManager) slidingTilesManager;
    }

    @Override
    public void processTapMovement(Context context, int position, boolean display) {
        if (!(slidingTilesManager == null)) {
            if (slidingTilesManager.isValidTap(position)) {
                slidingTilesManager.touchMove(position);
                if (slidingTilesManager.isGameOver()) {
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
