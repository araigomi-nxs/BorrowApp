package com.example.borrowapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.borrowapp.R;

public class Login_activity extends AppCompatActivity {

    Button register;
    Button login ;

    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            login = findViewById(R.id.btnLogin);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String username = etUsername.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();

                    if (username.isEmpty()) {
                        Toast.makeText(Login_activity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // You can add password validation here
                    if (password.isEmpty()) {
                        Toast.makeText(Login_activity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Pass the username to BorrowedListActivity
                    Intent intent = new Intent(Login_activity.this, BorrowedListActivity.class);
                    intent.putExtra("USERNAME", username); // Send username
                    startActivity(intent);
                    finish(); // Optional: close login activity
                }

            });

            register = findViewById(R.id.btnCreate);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login_activity.this, Register_activity.class);
                    startActivity(intent);
                }
            });





    }
}