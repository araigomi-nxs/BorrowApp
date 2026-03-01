package com.example.borrowapp.functions;

import android.content.Context;
import android.util.Log;

import com.example.borrowapp.Database;
import com.example.borrowapp.DatabaseTest;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BookFunctions {
   private static List<Book> booklist = Book.bookList;

    //retrieve book -

    public static void borrowBook (Context context, int userId , Book book , int orderQuantity)
    {
       booklist.get(book.getId()).setQuantity(orderQuantity,0);
       book.setId(userId);
       book.setQuantity(orderQuantity,2);
       DatabaseTest db = new DatabaseTest(context);
       db.borrowBooks(book);
       Log.d("BookFunctions", "borrowBook: " + book.getId());
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
