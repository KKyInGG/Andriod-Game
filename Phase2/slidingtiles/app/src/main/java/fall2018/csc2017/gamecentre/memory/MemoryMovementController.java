package fall2018.csc2017.gamecentre.memory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.MovementController;


public class MemoryMovementController implements MovementController {

    /**
     * A MemoryManager
     */
    private MemoryManager memoryManager = null;

    /**
     * A Initializer of MovementController.
     */
    public MemoryMovementController() {
    }

    @Override
    public void setGameManager(GameManager memoryManager) {
        this.memoryManager = (MemoryManager) memoryManager;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void processTapMovement(Context context, int position, boolean display) {
        if (!(memoryManager == null)) {
            if (memoryManager.isValidTap(position)) {
                int numMatches = memoryManager.getNumMatches();
                memoryManager.touchMove(position);
                if (memoryManager.isGameOver()) {
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                } else if (memoryManager.getNumMatches() > numMatches) {
                    Toast.makeText(context, String.format("That's match #%d!",
                            memoryManager.getNumMatches()), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
