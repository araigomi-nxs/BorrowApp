package com.example.borrowapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.borrowapp.models.Account;
import com.example.borrowapp.models.Book;

import java.util.ArrayList;
import java.util.List;


public class DatabaseTest extends SQLiteOpenHelper {
    private static final String DB_NAME = "borrowapp2.db";
    private static final int DB_VERSION = 1;
    public DatabaseTest( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE accounts("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"+
                "username TEXT UNIQUE, "+
                "password TEXT)");
        db.execSQL("CREATE TABLE borrow_books ("+
                "account_id INTEGER ,"+
                "title TEXT,"+
                "description TEXT,"+
                "author TEXT,"+
                "quantity INTEGER)");

        db.execSQL("CREATE TABLE books_inventory("+
                "book_id INTEGER UNIQUE,"+
                "stock INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS accounts");
        db.execSQL("DROP TABLE IF EXISTS borrow_books");
        db.execSQL("DROP TABLE IF EXISTS books_inventory");
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db= getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM accounts WHERE username = ?", new String[]{username});
        if(cursor.moveToFirst())
        {
            return true; //username exists
        }
        db.close();
        return  false; //username does not exist
    }
    public boolean log_in(String inputUsername, String inputPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("accounts", null, "username=? AND password=?",
                new String[]{inputUsername, inputPassword},
                null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }


    public void registerAccount(Account account){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username", account.getUsername());
        content.put("password",account.getPassword());
        db.insert("accounts",null,content);
    }

    public void initializeUser() {
        SQLiteDatabase db=this.getWritableDatabase();
        try (Cursor cursor = db.query("accounts", null, null, null, null, null, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Account account = new Account(
                            cursor.getInt(1),    //id
                            cursor.getString(2),//username
                            cursor.getString(3)//password
                    );
                } while (cursor.moveToNext());
            }
        }
        try (Cursor cursor = db.query("borrow_books", null, null, null, null, null, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Book book = new Book(
                            cursor.getInt(1),        //id
                            cursor.getString(2),    //title
                            cursor.getString(3),    //description
                            cursor.getString(4),    //author
                            cursor.getInt(5));      //quantity
                    Book.bookList.add(book);
                } while (cursor.moveToNext());
            }
        }
    }
    public void borrowBooks(Book book){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();

        content.put("account_id",book.getId());
        content.put("title",book.getTitle());
        content.put("description",book.getDescription());
        content.put("author",book.getAuthor());
        content.put("quantity",book.getQuantity());
        db.insert("borrow_books",null,content);
    }
    public List<Book> getBorrowedBooklist(){
        List<Book> borrowedBookList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM borrow_books",null);

        while (cursor.moveToNext()) {
            Book book = new Book(
                    cursor.getInt(0),  // account_id (first column)
                    cursor.getString(1), // title (second column)
                    cursor.getString(2), // description (third column)
                    cursor.getString(3), // author (fourth column)
                    cursor.getInt(4)     // quantity (fifth column)
            );
            borrowedBookList.add(book);
        }

        cursor.close();
        db.close();
        return borrowedBookList;
    }

    public void createBookLibrary(List<Book> booklist)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("test");
        for(Book book : booklist) {
            insertBook(book);
        }
        db.close();
    }

    public void insertBook(Book book) //insert by row
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("book_id",book.getId());
        content.put("stock",book.getQuantity());
        db.insert("books_inventory",null,content);

    }

    public void updateBookStock(int bookId, int newStock) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE books_inventory SET stock = " + newStock +
                " WHERE book_id = " + bookId);
        db.close();
    }

    public int getQuantity(int bookId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("books_inventory", new String[]{"stock"},"book_id LIKE ? ", new String[] {String.valueOf(bookId)},null, null, null );

        if (!cursor.moveToFirst())
        {
            db.close();
            return 0;
        }
        db.close();
        return cursor.getInt(0);
    }



    public void returnBooks(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("borrow_books","id=?",new String[]{String.valueOf(id)});
    }

}
