package com.example.borrowapp.functions;

import android.content.Context;
import android.util.Log;

import com.example.borrowapp.Database;
import com.example.borrowapp.DatabaseTest;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BookFunctions {


    //retrieve book -

    public static void borrowBook (Context context, int userId , Book book , int orderQuantity)
    {
        DatabaseTest db = new DatabaseTest(context);
       db.updateBookStock(book.getId(), book.getQuantity()-orderQuantity);
       book.setQuantity(orderQuantity,0);
       db.borrowBooks(book);

        Log.d( "BookFunctions", "bookquant: " + book.getQuantity());
    }

    public static void returnBook(Context context,int userId ,Book book, int orderQuantity)
    {
    }

    public void displayBookList(){

    }
    public void retrieBorrowList(){}

    public void reduceBookQuantity(Book book )
    {}

    //update booklist quantity- increment or decrement
}
