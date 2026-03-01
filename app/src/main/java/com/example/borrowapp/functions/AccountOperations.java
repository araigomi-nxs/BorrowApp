package com.example.borrowapp.functions;

//import com.example.borrowapp.Database;
import android.content.Context;

import com.example.borrowapp.Database;
import com.example.borrowapp.models.Account;

public class AccountOperations {
    private Database db;
    public AccountOperations(Context context) {
        db=new Database(context);

    }
    public static  boolean registerAccount(Account account){

        if(account.getUsername().length() > 16){

            return false; // exceeds char limit
        }
        /*

        */
        if(!(account.getPassword().matches("^(?=.*[A-Z])(?=.*\\\\d)[A-Za-z\\\\d]{16}$"))) {
            return false;
        }
       // Database.registerAccount(account);
        return true;
    }
    public boolean isUsernameFound(String username){
        return db.checkUsername(username);
    }
    public boolean verifyAccount(String username,String password){
        return db.log_in(username,password);
    }
}
