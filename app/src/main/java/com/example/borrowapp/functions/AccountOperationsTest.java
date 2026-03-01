package com.example.borrowapp.functions;

//import com.example.borrowapp.Database;
import android.content.Context;

import com.example.borrowapp.Database;
import com.example.borrowapp.DatabaseTest;
import com.example.borrowapp.models.Account;

public class AccountOperationsTest {

    public static boolean registerAccount(Context context, Account account){
        DatabaseTest databaseTest = new DatabaseTest(context);

        if(account.getUsername().length() > 16){

            return false; //exceeds 16

        }if(databaseTest.checkUsername(account.getUsername())){
            return false; //username exists
        }
        if(!(account.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).+$"))) {

            return false; //password does not match
        }
        databaseTest.registerAccount(account);
        return true;

    }
    public  static  boolean loginAccount(Context context, Account account)
    {
       DatabaseTest databaseTest = new DatabaseTest(context);
        if(!databaseTest.checkUsername(account.getUsername())){
            return false; //username exists
        }
        if( ! databaseTest.log_in(account.getUsername(),account.getPassword()))
        {
            return false;
        }
        return true;
    }
}
