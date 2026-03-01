package com.example.borrowapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.borrowapp.Database;
import com.example.borrowapp.R;
import com.example.borrowapp.functions.AccountOperations;
import com.example.borrowapp.functions.BooksDef;
import com.example.borrowapp.models.Account;

public class Login_activity extends AppCompatActivity {
    Database db;
    AccountOperations ao;
    Button register;
    Button login ;
    EditText inputUsername, inputPassword;
    private void start(){
        db.initializeUser();
        new BooksDef().initializeBooklist();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db=new Database(this);
        ao = new AccountOperations(this);
        start();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            inputUsername = findViewById(R.id.etUsername);
            inputPassword = findViewById(R.id.etPassword);
            login = findViewById(R.id.btnLogin);
        login.setOnClickListener(v -> {
            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString();
            if (ao.verifyAccount(username, password)) {
                Intent intent = new Intent(Login_activity.this, BorrowedListActivity.class);
                startActivity(intent);
                Account.setU_username(username);
            } else {
                inputUsername.setError("Invalid username");
                inputPassword.setError("Invalid password");
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