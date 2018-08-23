package com.example.tutushubham.publicledger;

public class Product {
    // fields

    private int productID;

    private int userID;

    private String productItem;

    private String itemPrice;

    // constructors

    public Product() {
    }

    public Product(int id, int userID) {

        this.productID = id;

        this.userID = userID;

    }

    // properties

    public int getID() {

        return this.productID;

    }

    public void setID(int id) {

        this.productID = id;

    }

    //breakkkkkkkkkkkkkk
    public int getUserID() {

        return this.userID;

    }

    public void setUserID(int userid) {

        this.userID = userID;

    }

    //breakkkkkkkkkkkkkk
    public String getProductItem() {

        return this.productItem;

    }

    public void setProductItem(String productItem) {

        this.productItem = productItem;

    }

    public String getItemPrice() {

        return this.itemPrice;

    }

    public void setItemPrice(String itemPrice) {

        this.itemPrice = itemPrice;

    }
}
