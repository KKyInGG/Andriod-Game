
package fall2018.csc2017.gamecentre.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.gamecentre.LogInActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.User;

/**
 * The score board display activity.
 */
public class SlidingTilesScoreBoardActivity extends AppCompatActivity {

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
        gameTitle.setText("Sliding Tiles");
        scoreDescription.setText("Least moves taken");

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = SlidingTilesStartingActivity.scoreboard.getScoreValues(false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = SlidingTilesStartingActivity.scoreboard.getScoreValues(true, currentPlayer);
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
     * Switch to SlidingTilesStartingActivity.
     */
    private void switchToStarting(){
        Intent tmp = new Intent(this, SlidingTilesStartingActivity.class);
        startActivity(tmp);
    }
}


