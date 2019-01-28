package fall2018.csc2017.gamecentre.sudoku;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;

import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.MovementController;


public class SudokuMovementController extends Observable implements MovementController {

    /**
     * A SudokuManger.
     */
    private SudokuManager sudokuManager = null;

    /**
     * Initilize a SudokuMovementController.
     */
    public SudokuMovementController() {
    }

    @Override
    public void setGameManager(GameManager sudokuManger) {
        this.sudokuManager = (SudokuManager) sudokuManger;
    }

    @Override
    public void processTapMovement(Context context, int position, boolean display) {
        if (!(sudokuManager == null)) {
            sudokuManager.setUpBackground(position);
            if (sudokuManager.getNumberToFill().equals("")) {
                Toast.makeText(context, "Choose a number", Toast.LENGTH_SHORT).show();
            } else {
                if (sudokuManager.isValidTap(position)) {
                    if (sudokuManager.checkRepeated(position)) {
                        Toast.makeText(context, "There is repeated number!", Toast.LENGTH_SHORT).show();
                    } else {
                        sudokuManager.touchMove(position);
                        if (sudokuManager.isGameOver()) {
                            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
                }
            }
            sudokuManager.setOriginal(sudokuManager.getSudokuBoard().getPuzzleSudoku());
        }
    }
}
