package com.example.borrowapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.borrowapp.R;
import com.example.borrowapp.functions.AccountOperationsTest;
import com.example.borrowapp.models.Account;

public class Register_activity extends AppCompatActivity {

    TextView already, usernameTv, passwordTv;
    Button registerButton;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        already = findViewById(R.id.tvAlready);
        passwordTv = findViewById(R.id.etPassword);
        usernameTv = findViewById(R.id.etUsername);
        registerButton = findViewById(R.id.btnRegister);

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_activity.this, Login_activity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameTv.getText().toString().trim();
                String password = passwordTv.getText().toString().trim();

                if (validateInputs(username, password)) {
                    account = new Account(username, password);

                    if (AccountOperationsTest.registerAccount(Register_activity.this, account)) {
                        Intent intent = new Intent(Register_activity.this, Login_activity.class);
                        startActivity(intent);
                        Toast.makeText(Register_activity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private boolean validateInputs(String username, String password) {

        if (TextUtils.isEmpty(username)) {
            usernameTv.setError("Username is required");
            usernameTv.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordTv.setError("Password is required");
            passwordTv.requestFocus();
            return false;
        }

        return validatePassword(password);
    }
    
    private boolean validatePassword(String password) {

        if (password.length() < 8) {
            passwordTv.setError("Password must be at least 8 characters long");
            passwordTv.requestFocus();
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            passwordTv.setError("Password must contain at least one uppercase letter");
            passwordTv.requestFocus();
            return false;
        }

        if (!password.matches(".*[0-9].*")) {
            passwordTv.setError("Password must contain at least one number");
            passwordTv.requestFocus();
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            passwordTv.setError("Password must contain at least one lowercase letter");
            passwordTv.requestFocus();
            return false;
        }

        // if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
        //     passwordTv.setError("Password must contain at least one special character");
        //     passwordTv.requestFocus();
        //     return false;
        // }

        // Optional: Check if password contains only allowed characters (alphanumeric and special chars)
        // if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+$")) {
        //     passwordTv.setError("Password contains invalid characters");
        //     passwordTv.requestFocus();
        //     return false;
        // }

        return true;
    }

    private boolean validatePasswordWithPattern(String password) {
        if (password.length() < 8) {
            passwordTv.setError("Password must be at least 8 characters long");
            passwordTv.requestFocus();
            return false;
        }

        java.util.regex.Pattern upperCasePattern = java.util.regex.Pattern.compile("[A-Z]");
        java.util.regex.Pattern numberPattern = java.util.regex.Pattern.compile("[0-9]");
        java.util.regex.Pattern lowerCasePattern = java.util.regex.Pattern.compile("[a-z]");

        if (!upperCasePattern.matcher(password).find()) {
            passwordTv.setError("Password must contain at least one uppercase letter");
            passwordTv.requestFocus();
            return false;
        }

        if (!numberPattern.matcher(password).find()) {
            passwordTv.setError("Password must contain at least one number");
            passwordTv.requestFocus();
            return false;
        }

        if (!lowerCasePattern.matcher(password).find()) {
            passwordTv.setError("Password must contain at least one lowercase letter");
            passwordTv.requestFocus();
            return false;
        }

        return true;
    }
}