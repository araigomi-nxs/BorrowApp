package com.example.borrowapp.functions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.borrowapp.R;
import com.example.borrowapp.models.Book;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private final List<Book> books;

    public RecyclerViewAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
            return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        Book book = books.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.descriptionTextView.setText(book.getDescription());
        holder.authortv.setText(book.getAuthor());
        holder.quantitytv.setText(String.valueOf(book.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView, authortv, quantitytv;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            authortv = itemView.findViewById(R.id.authortv);
            quantitytv = itemView.findViewById(R.id.quantitytv);
        }
    }
}
