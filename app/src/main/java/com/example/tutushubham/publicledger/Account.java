package com.example.tutushubham.publicledger;

public class Account {
    // fields

    private int accountID;

    private String accountName;

    private String accountItem;

    private String itemPrice;

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

    public String getAccountName() {

        return this.accountName;

    }

    public void setAccountName(String accountname) {

        this.accountName = accountname;

    }

    public String getAccountItem() {

        return this.accountItem;

    }

    public void setAccountItem(String accountItem) {

        this.accountItem = accountItem;

    }

    public String getItemPrice() {

        return this.itemPrice;

    }

    public void setItemPrice(String itemPrice) {

        this.itemPrice = itemPrice;

    }
}
