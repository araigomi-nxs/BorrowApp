package com.example.borrowapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import com.example.borrowapp.R;
import com.example.borrowapp.functions.BookArrayAdapter;
import com.example.borrowapp.functions.BookFunctions;
import com.example.borrowapp.models.Account;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BooklistActivity extends AppCompatActivity {

    private static List<Book> bookList ;
    private ListView listView;
    private int counter = 1;

    private Account accountInSession = new Account(1,"Juan Carlo","1234");
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
        Account account = (Account) getIntent().getSerializableExtra("ACCOUNT");
        Book.initializeBooklist();
        bookList = Book.bookList;

        Button returnButton = findViewById(R.id.btnBack);


        Log.d("BooklistActivity", "bookList size: " + bookList.size());

        listView = findViewById(R.id.listViewBooks);
          BookArrayAdapter bookArrayAdapter =new BookArrayAdapter(this, bookList);
        listView.setAdapter(bookArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Book selectedBook = bookList.get(position);
                view = getLayoutInflater().inflate(R.layout.bookinformation_activity,null);
                TextView titletv, desctv, authortv, quantitytv, orderquantitytv;
                Button borrowButton , addquantityButton, reduceQuantityButton;

                titletv = view.findViewById(R.id.tvTitle);
                desctv = view.findViewById(R.id.tvDesc);
                authortv= view.findViewById(R.id.tvAuthor);
                quantitytv= view.findViewById(R.id.tvQuantity);
                orderquantitytv= view.findViewById(R.id.tvOrderQuantity);
                borrowButton = view.findViewById(R.id.btnBorrowedplus);
                addquantityButton = view.findViewById(R.id.btnAdditon);
                reduceQuantityButton = view.findViewById(R.id.btnMinus);


                titletv.setText(selectedBook.getTitle());
                desctv.setText(selectedBook.getDescription());
                authortv.setText(selectedBook.getAuthor());
                quantitytv.setText(String.valueOf(selectedBook.getQuantity()));


                AlertDialog.Builder builder = new AlertDialog.Builder(BooklistActivity.this);
                builder.setView(view);

                addquantityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter++;
                        orderquantitytv.setText(String.valueOf( counter));
                    }
                });

                reduceQuantityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(counter !=1)
                        {
                            counter--;
                        }
                        orderquantitytv.setText(String.valueOf(counter) );
                    }
                });



                builder.show();
                borrowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if( counter > selectedBook.getQuantity())
                        {
                            Toast.makeText(BooklistActivity.this, "Not Enough Quantity Available", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            BookFunctions.borrowBook( BooklistActivity.this, accountInSession.getId() ,selectedBook, counter);
                            Toast.makeText(BooklistActivity.this, "Book added to borrow list", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(BooklistActivity.this, BorrowedListActivity.class);
                            intent.putExtra("ACCOUNT", account);
                            startActivity(intent);



                        }
                    }
                });


            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BooklistActivity.this, BorrowedListActivity.class );
                intent.putExtra("ACCOUNT", account);
                startActivity( intent);

            }
        });



    }
}