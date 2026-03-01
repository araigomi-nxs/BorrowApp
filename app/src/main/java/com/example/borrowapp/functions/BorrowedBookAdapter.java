package com.example.borrowapp.functions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.borrowapp.R;
import com.example.borrowapp.models.Book;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BorrowedBookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> bookList;

    public BorrowedBookAdapter(@NonNull Context context, List<Book> bookList) {
        super(context, 0, bookList);
        this.context = context;
        this.bookList = bookList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.borrowed_books_card, parent, false);
        }

        Book currentBook = bookList.get(position);

        TextView title = listItem.findViewById(R.id.txtTitle);
        TextView description = listItem.findViewById(R.id.txtDesc);
        TextView date = listItem.findViewById(R.id.txtDate);
        TextView quantity = listItem.findViewById(R.id.txtQuantity);


        title.setText(currentBook.getTitle());
        description.setText(currentBook.getDescription());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDateTime = dateFormat.format(calendar.getTime());


        date.setText(currentDateTime);
        quantity.setText("Available: " + currentBook.getQuantity());

        return listItem;
    }
}