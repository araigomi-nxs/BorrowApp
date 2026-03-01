package com.example.borrowapp.functions;

//import com.example.borrowapp.Database;
import com.example.borrowapp.models.Account;

public class AccountOperations {

    public boolean registerAccount(Account account){

        if(account.getUsername().length() > 16){

            return false; // exceeds char limit
        }
        /*
        if(Database.checkUsername(account.getUsername())){
            return false; // username exists
        }
        */
        if(!(account.getPassword().matches("^(?=.*[A-Z])(?=.*\\\\d)[A-Za-z\\\\d]{16}$"))) {

            return false;
        }
       // Database.registerAccount(account);
        return true;

    }
    //0 if not found // 1 if found
     /*
    public boolean verifyAccount(Account account){


          if( !(Database.checkUsername(account.getUsername()) && account.getPassword().matches(Database.checkPassword(account.getUsername()))))
        {
            return false;
        }
        return true;
    }
 */
}
