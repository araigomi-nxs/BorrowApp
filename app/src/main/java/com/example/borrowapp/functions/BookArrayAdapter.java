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

import java.util.List;

public class BookArrayAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> bookList;

    public BookArrayAdapter(@NonNull Context context, List<Book> bookList) {
        super(context, 0, bookList);
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        }

        Book currentBook = bookList.get(position);

        TextView title = listItem.findViewById(R.id.titleTextView);
        TextView description = listItem.findViewById(R.id.descriptionTextView);
        TextView author = listItem.findViewById(R.id.authortv);
        TextView quantity = listItem.findViewById(R.id.quantitytv);


        title.setText(currentBook.getTitle());
        author.setText("By: " + currentBook.getAuthor());
        description.setText(currentBook.getDescription());
        quantity.setText("Available: " + currentBook.getQuantity());


        return listItem;
    }
}