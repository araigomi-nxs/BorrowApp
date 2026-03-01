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
        Book.addBook(new Book(1, "The Great Gatsby", "A classic novel set in the Jazz Age, exploring themes of wealth, love, and the American Dream. The story follows the mysterious millionaire Jay Gatsby and his obsession with Daisy Buchanan.", "F. Scott Fitzgerald", 5));
        Book.addBook(new Book(2, "To Kill a Mockingbird", "A powerful story of racial injustice and loss of innocence in the American South. Told through the eyes of young Scout Finch, whose father Atticus defends a black man falsely accused of a crime.", "Harper Lee", 3));
        Book.addBook(new Book(3, "1984", "A dystopian social science fiction novel that explores totalitarianism and government surveillance. Winston Smith rebels against the all-seeing Party and its leader Big Brother.", "George Orwell", 2));
        Book.addBook(new Book(4, "Pride and Prejudice", "A romantic novel that critiques the British landed gentry at the end of the 18th century. Elizabeth Bennet navigates issues of manners, upbringing, morality, education, and marriage.", "Jane Austen", 4));
        Book.addBook(new Book(5, "The Hobbit", "A fantasy adventure about a hobbit who joins a quest to reclaim a treasure from a dragon. Bilbo Baggins discovers courage and cunning he never knew he had.", "J.R.R. Tolkien", 6));
        Book.addBook(new Book(6, "Dune", "An epic science fiction story set on a desert planet, exploring politics, religion, and ecology. Young Paul Atreides must navigate treacherous alliances to survive.", "Frank Herbert", 3));
        Book.addBook(new Book(7, "Harry Potter", "The first book in the Harry Potter series, introducing the boy wizard and his magical world. Harry discovers he is a wizard and begins his journey at Hogwarts School.", "J.K. Rowling", 8));
        Book.addBook(new Book(8, "The Alchemist", "An allegorical novel about a young shepherd on his journey to find treasure and discover his personal legend. Santiago learns to listen to his heart and follow his dreams.", "Paulo Coelho", 5));
        Book.addBook(new Book(9, "The Martian", "A science fiction story about an astronaut stranded on Mars trying to survive. Using his ingenuity and scientific knowledge, Mark Watney fights to stay alive until rescue.", "Andy Weir", 4));
        Book.addBook(new Book(10, "The Da Vinci Code", "A mystery thriller that follows a symbologist investigating a murder in the Louvre Museum. Robert Langdon uncovers secrets that could shake the foundations of Christianity.", "Dan Brown", 7));
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