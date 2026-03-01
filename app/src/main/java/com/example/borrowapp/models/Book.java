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