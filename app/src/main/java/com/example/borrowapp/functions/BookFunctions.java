package com.example.borrowapp.functions;

import com.example.borrowapp.Database;
import com.example.borrowapp.models.Book;

import java.util.List;

public class BookFunctions {
   private List<Book> booklist = Book.bookList;

    //retrieve book -

    public boolean borrowBook (Book book , int orderQuantity)
    {
        if ( orderQuantity > book.getQuantity())
        {
            return false; // not enough quantity
        }
        Database.borrowBooks(book);

        return  true;

    }

    public void displayBookList(){

    }
    public void retrieBorrowList(){}

    public void reduceBookQuantity(Book book )
    {}

    //update booklist quantity- increment or decrement
}
