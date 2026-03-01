package com.example.borrowapp.models;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private int id;
    private String title;
    private String description;
    private String author;

    private int quantity;
    public static List<Book> bookList = new ArrayList<Book>();
    public static void initializeBooklist()
    {
        Book.addBook(new Book(1,"Book1", "Description1", "Author1", 1));
        Book.addBook(new Book(2,"Book2", "Description2", "Author2", 1));
        Book.addBook(new Book(3,"Book3", "Description3", "Author3", 1));
        Book.addBook(new Book(4,"Book4", "Description4", "Author4", 1));
        Book.addBook(new Book(5,"Book5", "Description5", "Author5", 1));

    }

    public static void addBook(Book book) {
        bookList.add(book);
    }


    public Book(int id, String title, String description, String author, int quantity)
    {
        this.id=id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.quantity = quantity;
    }



    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}