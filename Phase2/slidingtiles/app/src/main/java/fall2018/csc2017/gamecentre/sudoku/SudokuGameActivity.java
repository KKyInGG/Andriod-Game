package fall2018.csc2017.gamecentre.sudoku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.gamecentre.LogInActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.CustomAdapter;

public class SudokuGameActivity extends AppCompatActivity implements Observer, Serializable {

    /**
     * Grid View and calculated column height and width based on device size
     */
    private SudokuGestureDetectGridView gridView;
    private static int columnWidth, columnHeight;
    private ArrayList<Button> BoxButtons;

    /**
     * Set up the background image and number for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */

    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(getBoxButtons(), columnWidth, columnHeight));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SudokuStartingActivity.controller.getGameManager().setUpBackgrounds();
        setContentView(R.layout.activity_sudoku);
        initView();
        addUndoButtonListener();
        addOneButtonListener();
        addTwoButtonListener();
        addThreeButtonListener();
        addFourButtonListener();
        addFiveButtonListener();
        addSixButtonListener();
        addSevenButtonListener();
        addEightButtonListener();
        addNineButtonListener();
        addSaveButtonListerner();
    }

    /**
     * Activate Undo Button.
     */
    private void addSaveButtonListerner() {
        Button saveButton = findViewById(R.id.sudokusave);
        saveButton.setOnClickListener(v -> {
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate number1 Button.
     */
    private void addOneButtonListener() {
        Button button = findViewById(R.id.number1);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("1");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number2 Button.
     */
    private void addTwoButtonListener() {
        Button button = findViewById(R.id.number2);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("2");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number3 Button.
     */
    private void addThreeButtonListener() {
        Button button = findViewById(R.id.number3);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("3");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number4 Button.
     */
    private void addFourButtonListener() {
        Button button = findViewById(R.id.number4);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("4");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number5 Button.
     */
    private void addFiveButtonListener() {
        Button button = findViewById(R.id.number5);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("5");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number6 Button.
     */
    private void addSixButtonListener() {
        Button button = findViewById(R.id.number6);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("6");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number7 Button.
     */
    private void addSevenButtonListener() {
        Button button = findViewById(R.id.number7);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("7");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number8 Button.
     */
    private void addEightButtonListener() {
        Button button = findViewById(R.id.number8);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("8");
            makeToastChooseSpot();
        });
    }

    /**
     * Activate number9 Button.
     */
    private void addNineButtonListener() {
        Button button = findViewById(R.id.number9);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("9");
            makeToastChooseSpot();
        });
    }

    /**
     * Display to let player choose a empty spot
     */
    private void makeToastChooseSpot() {
        Toast.makeText(this, "Choose a empty spot", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate Undo Button.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.sudokuundo);
        undoButton.setOnClickListener(v -> {
            if (!(SudokuStartingActivity.controller.getGameManager() == null)) {
                if (SudokuStartingActivity.controller.getGameManager().getUndoPositionStack().empty()) {
                    makeToastNothingToUndo();
                } else {
                    SudokuStartingActivity.controller.getGameManager().tryUndo();
                    makeToastUndoSuccessful();
                }
            }
        });
    }

    /**
     * Display when no more undo
     */
    private void makeToastNothingToUndo() {
        Toast.makeText(this, "No Undo left", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display when undo is successful.
     */
    private void makeToastUndoSuccessful() {
        Toast.makeText(this, "Undo successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * Initialize the view for sudoku
     */
    public void initView() {
        //initView with gridView
        gridView = findViewById(R.id.sudokugrid);
        gridView.setNumColumns(9);
        gridView.setmController(new SudokuMovementController());
        gridView.setGameManager(SudokuStartingActivity.controller.getGameManager());
        createTileButtons(this);
        SudokuStartingActivity.controller.getGameManager().addObserver(this);
        SudokuMovementController movementController = (SudokuMovementController) gridView.getmController();
        movementController.addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / 9;
                        columnHeight = displayHeight / 9;
                        display();
                    }
                });
    }


    @Override
    public void update(Observable observable, Object o) {
        display();
        SudokuStartingActivity.controller.notifyObservers();
        if (SudokuStartingActivity.controller.checkToAddScore(SudokuStartingActivity.scoreboard, LogInActivity.currentPlayer.getAccount())) {
            switchToScoreBoard();
        }
    }

    /**
     * Switch to scoreboard activity.
     */
    private void switchToScoreBoard() {
        Intent tmp = new Intent(this, SudokuScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Create the tile Buttons with context
     * @param context the context
     */
    public void createTileButtons(Context context) {
        SudokuGrid[][] sudokuPuzzle = SudokuStartingActivity.controller.getGameManager().getSudokuBoard().getPuzzleSudoku();
        BoxButtons = new ArrayList<>();
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(sudokuPuzzle[row][col].getBackground());
                this.BoxButtons.add(tmp);
            }
        }
    }

    /**
     * Update the tile buttons.
     */
    public void updateTileButtons() {
        SudokuGrid[][] sudokuBoard = SudokuStartingActivity.controller.getGameManager().getSudokuBoard().getPuzzleSudoku();
        int buttonPosition = 0;
        for(int row = 0; row != 9; row ++){
            for(int col = 0; col != 9; col++){
                Button b = BoxButtons.get(buttonPosition);
                b.setText(sudokuBoard[row][col].getNumber());
                b.setTextSize(17);
                b.setBackgroundResource(sudokuBoard[row][col].getBackground());
                buttonPosition ++;
            }
        }
    }
    public ArrayList<Button> getBoxButtons(){
        return BoxButtons;
    }
}
