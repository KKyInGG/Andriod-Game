package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    public static UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupSignInListener();
        setupSignUpListener();
        userManager = new UserManager();
        UserFileSaver fileSaver = new UserFileSaver(this);
        userManager.register(fileSaver);
        userManager.setAllUsers(fileSaver.getAllUsers());

    }

    /**
     * Activate the signIn button.
     */
    public void setupSignInListener(){
        Button signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, LogInActivity.class);
            startActivity(tmp);
        });

    }

    /**
     * Activate the signUp button.
     */
    public void setupSignUpListener(){
        Button signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, SignUpActivity.class);
            startActivity(tmp);
        });

    }
}