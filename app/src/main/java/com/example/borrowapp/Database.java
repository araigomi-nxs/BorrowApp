package com.example.borrowapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.borrowapp.models.Account;
import com.example.borrowapp.models.Book;


public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "borrowapp.db";
    private static final int DB_VERSION = 1;
    //IMPORTANT: call itong constructor sa MainActivity.java:
    //Example:
    //Database db = new Database(this);
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //ma create na table after creation ng app
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE accounts("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"+
                "username VARCHAR(16) UNIQUE, "+
                "password VARCHAR(16))");
        db.execSQL("CREATE TABLE borrow_books("+
                "account_id INTEGER,"+
                "title TEXT UNIQUE,"+
                "description TEXT,"+
                "author TEXT,"+
                "quantity INTEGER)");
    }
    //if ever mag update idelete
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS accounts");
        db.execSQL("DROP TABLE IF EXISTS borrow_books");
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query(
                "accounts",                     // table
                new String[]{"username"},       // columns to return
                "username=?",                   // selection
                new String[]{username},         // selection args
                null, null, null
        )) {
            return cursor.moveToFirst();       // true if exists
        }
    }
    public boolean log_in(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query(
                "accounts",
                new String[]{"username"},
                "username=? AND password=?",
                new String[]{username, password},
                null, null, null
        )) {
            return cursor.moveToFirst();  // true if match found
        }
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
                                cursor.getInt(0),    //id
                                cursor.getString(1),//username
                                cursor.getString(2)//password
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
        public void returnBooks(int id){
            SQLiteDatabase db=this.getWritableDatabase();
            db.delete("borrow_books","id=?",new String[]{String.valueOf(id)});
        }

}
