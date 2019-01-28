package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import fall2018.csc2017.gamecentre.memory.MemoryStartingActivity;
import fall2018.csc2017.gamecentre.slidingtiles.SlidingTilesStartingActivity;
import fall2018.csc2017.gamecentre.sudoku.SudokuStartingActivity;

/**
 * The launch center activity.
 */
public class LaunchCenterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launchcenter);

        addGameButtonListeners();
        setupLogOutListener();
    }

    /**
     * Activate the buttons for each game.
     */
    private void addGameButtonListeners() {
        ImageButton slidingTilesButton = findViewById(R.id.SlidingTilesImageButton);
        slidingTilesButton.setOnClickListener(v -> switchToGame("slidingtiles"));

        ImageButton sudokuButton = findViewById(R.id.SudokuImageButton);
        sudokuButton.setOnClickListener(v -> switchToGame("sudoku"));

        ImageButton memoryButton = findViewById(R.id.MemoryImageButton);
        memoryButton.setOnClickListener(v -> switchToGame("memory"));
    }

    /**
     * Switch to the desired game.
     * @param gameTitle a string referring to the type of game.
     */
    public void switchToGame(String gameTitle){
        switch(gameTitle){
            case "slidingtiles":
                startActivity(new Intent(this, SlidingTilesStartingActivity.class));
                break;
            case "sudoku":
                startActivity(new Intent(this, SudokuStartingActivity.class));
                break;
            case "memory":
                startActivity(new Intent(this, MemoryStartingActivity.class));
        }
    }

    /**
     * Activate the logout button.
     */
    public void setupLogOutListener(){
        Button signIn = findViewById(R.id.logout);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });
    }
}