package com.example.borrowapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.borrowapp.R;
import com.example.borrowapp.functions.BookArrayAdapter;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BooklistActivity extends AppCompatActivity {

    private static List<Book> bookList ;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.booklist_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Book.initializeBooklist();
        bookList = Book.bookList;

        Log.d("BooklistActivity", "bookList size: " + bookList.size());

        listView = findViewById(R.id.listViewBooks);
          BookArrayAdapter bookArrayAdapter =new BookArrayAdapter(this, bookList);
        listView.setAdapter(bookArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Book selectedBook = bookList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(BooklistActivity.this);
                view = getLayoutInflater().inflate(R.layout.bookinformation_activity,null);
                builder.setView(view);
                builder.show();


            }
        });


    }
}