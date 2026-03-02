package com.example.borrowapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.borrowapp.Database;
import com.example.borrowapp.DatabaseTest;
import com.example.borrowapp.R;
import com.example.borrowapp.functions.BookArrayAdapter;
import com.example.borrowapp.functions.BorrowedBookAdapter;
import com.example.borrowapp.models.Account;

public class BorrowedListActivity extends AppCompatActivity {

    ListView borrowedBookList;
    Button addBookButton, logoutButton;

    TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.borrowed_list_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvUsername = findViewById(R.id.tvUsername);
        addBookButton = findViewById(R.id.btnAddBook);
        logoutButton = findViewById(R.id.btnLogout);

       Account account = (Account) getIntent().getSerializableExtra("ACCOUNT");
        tvUsername.setText(account.getUsername());

        borrowedBookList = findViewById(R.id.listViewBooks);
        DatabaseTest databasetest = new DatabaseTest(BorrowedListActivity.this);

        BorrowedBookAdapter borrowedBookAdapter = new BorrowedBookAdapter(this, databasetest.getBorrowedBooklist() );
        borrowedBookList.setAdapter(borrowedBookAdapter);



        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowedListActivity.this, BooklistActivity.class );
                intent.putExtra("ACCOUNT", account);
                startActivity( intent);
            }
        });

        logoutButton.setOnClickListener(view -> {
            new AlertDialog.Builder(BorrowedListActivity.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout, " + account.getUsername() + "?")
                    .setPositiveButton("YES, LOGOUT", (dialog, which) -> {
                        Toast.makeText(BorrowedListActivity.this,
                                "okie.",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BorrowedListActivity.this, Login_activity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("CANCEL", (dialog, which) -> {
                        Toast.makeText(BorrowedListActivity.this,
                                "Logout cancelled",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .show();
        });



    }

}