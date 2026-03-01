package com.example.borrowapp.activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.borrowapp.models.Book;

public class Register_activity extends AppCompatActivity {

    TextView already,usernameTv,passwordTv;
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


        already  = findViewById(R.id.tvAlready);
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
                account = new Account(usernameTv.getText().toString(), passwordTv.getText().toString());
                AccountOperationsTest accountOperationsTest = new AccountOperationsTest();

                if (accountOperationsTest.registerAccount(Register_activity.this, account)) {
                    Toast.makeText(Register_activity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}

