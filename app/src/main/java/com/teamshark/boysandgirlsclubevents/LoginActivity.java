package com.teamshark.boysandgirlsclubevents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamshark.boysandgirlsclubevents.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // Firebase Authentication.
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    // Activity UI elements.
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        // Get UI elements.
        mEmailField = findViewById(R.id.editText_email);
        mPasswordField = findViewById(R.id.editText_password);
        mLoginButton = findViewById(R.id.button_login);

        // Get Firebase instance.
        mAuth = FirebaseAuth.getInstance();

        // Ensure that UI reflects current status.
        mUser = mAuth.getCurrentUser();
        updateUI();
    }

    public boolean validateInput() {
        // Get email and password as strings.
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        // Ensure that input is not empty.
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and/or password cannot be blank.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Ensure that email is a valid email.
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void login(View v) {
        // Log out if currently logged in.
        if (mUser != null) {
            mAuth.signOut();
            mUser = null;
            updateUI();
            return;
        }

        // Validate input.
        if (!validateInput()) {
            return;
        }

        // Get email and password as strings.
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        // Clear password field.
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
                        mUser = mAuth.getCurrentUser();
                        updateUI();
                    }
                });
    }

    private void updateUI() {
        if (mUser == null) {
            // Enable both fields.
            mEmailField.setEnabled(true);
            mPasswordField.setEnabled(true);

            // Clear both fields.
            mEmailField.setText("");
            mPasswordField.setText("");

            // Update login button text.
            mLoginButton.setText(getResources().getString(R.string.button_login));
        } else {
            // Disable both fields.
            mEmailField.setEnabled(false);
            mPasswordField.setEnabled(false);

            // Show the current user's email.
            mEmailField.setText(mUser.getEmail());

            // Update login button text.
            mLoginButton.setText(getResources().getString(R.string.button_logout));
        }
        invalidateOptionsMenu();
    }
}
