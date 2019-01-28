package fall2018.csc2017.gamecentre.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import fall2018.csc2017.gamecentre.GameFileSaver;
import fall2018.csc2017.gamecentre.LaunchCenterActivity;
import fall2018.csc2017.gamecentre.LogInActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.Scoreboard;
import fall2018.csc2017.gamecentre.ScoreboardFileSaver;


public class SudokuStartingActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    /**
     * A SudokuController.
     */
    public static SudokuController controller;

    /**
     * A ScoreBoard.
     */
    public static Scoreboard scoreboard;

    /**
     * A file with sudokuscores.
     */
    private static final String fileName = "sudokuscores.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Game MVC setup!!
        GameFileSaver gameFileSaver = new GameFileSaver(this, LogInActivity.currentPlayer.getSudokuGameFile());
        controller = new SudokuController();
        if(gameFileSaver.getGameManager() != null){
            controller.setGameManager(gameFileSaver.getGameManager());
        }
        controller.register(gameFileSaver);

        //Scoreboard MVC setup
        scoreboard = new Scoreboard();
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboard.register(scoreboardFileSaver);
        scoreboard.setGlobalScores(scoreboardFileSaver.getGlobalScores());
        gameFileSaver.saveToFile();

        setContentView(R.layout.activity_sudoku_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addViewScoreButtonListener();
        addQuitButtonListener();
    }

    /**
     * Activate the quit button.
     */
    private void addQuitButtonListener() {
        Button QuitButton = findViewById(R.id.sudokuQuit);
        QuitButton.setOnClickListener(v -> switchToLauchCnter());
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.sudokustart);
        startButton.setOnClickListener(this::showPopup);
    }

    /**
     * Pop up a menu of complexities.
     */
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.complexity_menu);
        popup.show();
    }

    /**
     * Activate the items in the menu.
     */
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                controller.setUpBoard("Easy");
                switchToGame();
                return true;

            case R.id.item2:
                controller.setUpBoard("Medium");
                switchToGame();
                return true;

            case R.id.item3:
                controller.setUpBoard("Hard");
                switchToGame();
                return true;

            default:
                return false;
        }
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.sudokuload);
        loadButton.setOnClickListener(v -> {
            if (controller.getGameManager() != null){
                makeToastLoadedText();
                switchToGame();
            }
            else{
                makeToastNoLoadedText();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that there are no saved games.
     */
    private void makeToastNoLoadedText() {
        Toast.makeText(this, "No Saved Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Switch to the SudookuGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SudokuGameActivity.class);
        startActivity(tmp);
    }

    /**
     * Activate the view score button.
     */
    private void addViewScoreButtonListener() {
        Button ScoreBoardButton = findViewById(R.id.sudokuViewScore);
        ScoreBoardButton.setOnClickListener(v -> switchToScoreBoard());
    }

    /**
     * Switch to the SlidingTilesScoreBoardActivity view.
     */
    private void switchToScoreBoard(){
        Intent tmp = new Intent(this, SudokuScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTilesSetNumundoActivity view.
     */
    private void switchToLauchCnter(){
        Intent tmp = new Intent(this, LaunchCenterActivity.class);
        startActivity(tmp);
    }
}
