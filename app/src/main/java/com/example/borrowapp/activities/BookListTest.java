package com.example.borrowapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.borrowapp.R;
import com.example.borrowapp.functions.BooksDef;
import com.example.borrowapp.functions.RecyclerViewAdapter;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BookListTest extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_list_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        BooksDef booksDef = new BooksDef();
        booksDef.initializeBooklist();
        Log.d("DEBUG", "Book list size: " + Book.bookList.size());

        RecyclerView recyclerView = findViewById(R.id.book_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Book.bookList);
        recyclerView.setAdapter(adapter);


        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }
}