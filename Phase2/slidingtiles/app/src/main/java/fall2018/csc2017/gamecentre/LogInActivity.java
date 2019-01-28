package fall2018.csc2017.gamecentre;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity{

    /**
     * A UserManager.
     */
    private UserManager users;

    /**
     * The current player of the game.
     */
    public static User currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        users = RegisterActivity.userManager;
        setupSignInListener();
    }

    /**
     *  Activate the signIn button.
     */
    @SuppressLint("SetTextI18n")
    public void setupSignInListener() {
        Button signIn = findViewById(R.id.signina);
        signIn.setOnClickListener((v) -> {
            String userName = ((EditText)findViewById(R.id.usernamea)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.passworda)).getText().toString();
            TextView textBox = findViewById(R.id.textboxa);
            try {
                currentPlayer = users.signIn(userName, passWord);
            }
            catch (AuthenticatorException e){
                textBox.setText("Incorrect Password.");
                return;
            }
            catch (AccountsException e) {
                textBox.setText("Username is not found.");
                return;
            }
            textBox.setText("Sign In Sucessfully!");
            Intent tmp = new Intent(this, LaunchCenterActivity.class);
            startActivity(tmp);
        });
    }
}