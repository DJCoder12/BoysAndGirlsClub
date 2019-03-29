package com.example.boysandgirlsclubevents;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // Firebase Authentication.
    private FirebaseAuth mAuth;


    // Activity UI elements.
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get UI elements.
        mEmailField = findViewById(R.id.editText_email);
        mPasswordField = findViewById(R.id.editText_password);
        mLoginButton = findViewById(R.id.button_login);

        // Get Firebase instance.
        mAuth = FirebaseAuth.getInstance();

        // Wire up the button.
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void register() {
        // TODO: Should users be allowed to register through the app? Or other mechanism?
    }

    public void login() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        // Clear the password field.
        mPasswordField.setText("");

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,
                                    "Login successful. You are now able to modify events.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Login failed. Check your credentials.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
