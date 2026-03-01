package com.example.borrowapp.functions;

import com.example.borrowapp.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksDef {
    private static List<String> title = new ArrayList<String>();
    private static List<String> description = new ArrayList<String>();
    private static List<String> author = new ArrayList<String>();
    private static List<Integer> quantity = new ArrayList<Integer>();

    public static void initializeBooklist()
    {
        Book.addBook(new Book(1,"Book1", "Description1", "Author1", 1));
        Book.addBook(new Book(2,"Book2", "Description2", "Author2", 1));
        Book.addBook(new Book(3,"Book3", "Description3", "Author3", 1));
        Book.addBook(new Book(4,"Book4", "Description4", "Author4", 1));
        Book.addBook(new Book(5,"Book5", "Description5", "Author5", 1));

    }

}
