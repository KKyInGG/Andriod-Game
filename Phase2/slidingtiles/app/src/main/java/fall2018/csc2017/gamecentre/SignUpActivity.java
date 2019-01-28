package fall2018.csc2017.gamecentre;

import android.accounts.AccountsException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    /**
     * A UserManager
     */
    private UserManager users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        users = RegisterActivity.userManager;
        setupSignUpListener();
    }

    /**
     * Activate the signUp button.
     */
    @SuppressLint("SetTextI18n")
    public void setupSignUpListener() {
        Button signUp = findViewById(R.id.signupa);
        signUp.setOnClickListener((v) -> {
            String userName = ((EditText)findViewById(R.id.usernamea)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.passworda)).getText().toString();
            TextView textBox = findViewById(R.id.textboxa);
            try{
                users.signUp(userName, passWord);
            }
            catch (DuplicateException e) {
                textBox.setText("This username has been registered.");
                return;
            }
            catch (AccountsException e) {
                textBox.setText("Please fill in your username.");
                return;
            }
            catch (NoPassWordException e) {
                textBox.setText("Please fill in your password.");
                return;
            }
            textBox.setText("Sign up Successfully!");
            Intent tmp = new Intent(this, LogInActivity.class);
            startActivity(tmp);
        });

    }
}
