package com.example.tutushubham.publicledger;

public class Account {
    // fields

    private int accountID;

    private String accountName;


    // constructors

    public Account() {
    }

    public Account(int id, String accountname) {

        this.accountID = id;

        this.accountName = accountname;

    }

    // properties

    public int getID() {

        return this.accountID;

    }

    public void setID(int id) {

        this.accountID = id;

    }

    //breakkkkkkkkkkkkkkkkkkkkkkkkkk


    public String getAccountName() {

        return this.accountName;

    }

    public void setAccountName(String accountname) {

        this.accountName = accountname;

    }
}
