package com.example.borrowapp.functions;

import com.example.borrowapp.Database;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BookFunctions {
   private List<Book> booklist = Book.bookList;

    //retrieve book -

    public static void borrowBook ( int userId ,Book book , int orderQuantity)
    {
       book.setId(userId);
       //Database db = new Database(null);
       // db.borrowBooks(book);
    }

    public void displayBookList(){

    }
    public void retrieBorrowList(){}

    public void reduceBookQuantity(Book book )
    {}

    //update booklist quantity- increment or decrement
}
