package com.example.borrowapp.functions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.borrowapp.R;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }


    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView =LayoutInflater.from(context).inflate(R.layout.book_list_item, parent,false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.titleTextView);
            holder.description = convertView.findViewById(R.id.descriptionTextView);
            holder.author = convertView.findViewById(R.id.authortv);
            holder.quantity = convertView.findViewById(R.id.quantitytv);

            convertView.setTag(holder);
        }

        else
        {
            holder = (ViewHolder) convertView.getTag();

        }

        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.description.setText(book.getDescription());
        holder.author.setText(book.getAuthor());
        holder.quantity.setText(String.valueOf(book.getQuantity()));


        return  convertView;
    }

    static class ViewHolder{
        TextView title;
        TextView description;
        TextView author;
        TextView quantity;
    }



}
