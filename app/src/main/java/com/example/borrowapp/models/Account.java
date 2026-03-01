package com.example.borrowapp.models;

public class Account {
    //SIGNED IN ACCOUNT
    private static String U_username;
    public static void setU_username(String username){
        U_username=username;
    }
    public static String getU_username(){
        return U_username;
    }

    //Accounts
    private int id;
    private String username;
    private String password;

    public Account(int id, String username, String password){
        this.id=id;
        this.username = username;
        this.password = password;
    }
    public int getId(){
        return id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //for admin credentials
    private final static String adminUsername="admin",
            adminPassword="1234";
    public static String getAdminUsername(){
        return adminUsername;
    }
    public static String getAdminPassword(){
        return adminPassword;
    }
} 