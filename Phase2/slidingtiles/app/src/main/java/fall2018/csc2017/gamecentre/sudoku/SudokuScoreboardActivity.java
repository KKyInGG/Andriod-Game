package fall2018.csc2017.gamecentre.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.gamecentre.LogInActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.User;

public class SudokuScoreboardActivity extends AppCompatActivity {
    /**
     * The quick reference for the currently logged in player.
     */
    private User currentPlayer = LogInActivity.currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        addReturnButtonListener();

        // Change to appropriate game title and score description
        TextView gameTitle = findViewById(R.id.GameTitle);
        TextView scoreDescription = findViewById(R.id.ScoreDescription);
        gameTitle.setText("Sudoku");
        scoreDescription.setText("Least moves taken");

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = SudokuStartingActivity.scoreboard.getScoreValues(false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = SudokuStartingActivity.scoreboard.getScoreValues(true, currentPlayer);
        userScoresText.setText(userScoreValues);
    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }

    /**
     * Switch to the sudoku starting activity page
     */
    private void switchToStarting(){
        Intent tmp = new Intent(this, SudokuStartingActivity.class);
        startActivity(tmp);
    }
}
