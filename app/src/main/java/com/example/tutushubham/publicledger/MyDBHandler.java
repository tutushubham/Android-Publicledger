package com.example.tutushubham.publicledger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {


    //private static MyDBHandler sInstance;

    //SQLiteDatabase db = getWritableDatabase();

    //information of database

    public static final String TABLE_NAME = "Account";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "AccountName";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AccountDB.db";

   /* public static synchronized MyDBHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MyDBHandler(context);
        }
        return sInstance;
    }
*/


    MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("creating db", "create called");
        String CREATE_TABLE_ACCOUNT = "CREATE TABLE if not exists " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " VARCHAR(40) "
                + " );";

        String CREATE_TABLE_PRODUCT = "CREATE TABLE if not exists " + " Product " + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " User_ID INTEGER , "
                + " Product_Name VARCHAR(40) , "
                + " Price VARCHAR(40), "
                + " AccID  INTEGER, FOREIGN KEY(AccID) REFERENCES Account(ID)"
                + " );";


        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_PRODUCT);


        Log.e("table", CREATE_TABLE_ACCOUNT + "\n" + CREATE_TABLE_PRODUCT);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    //neeche hai databse ke methods


    public int size() {

        SQLiteDatabase db = this.getWritableDatabase(); //this command makes table again n again

        int result_0 = 0;
        String query = "Select *  FROM " + TABLE_NAME + " ;";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            result_0++;

        }
        cursor.close();
        return result_0;
    }

    public String loadHandler(String tablename) {   //to show table
        String result = "", result_2 = "", result_3 = "", result_1 = "";
        int result_0, result_4;


        String query = "Select * FROM " + tablename + " ;";

        SQLiteDatabase db = this.getWritableDatabase(); //this command makes table again n again

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            if (tablename.equals("Account")) {

                result_0 = cursor.getInt(0);

                result_1 = cursor.getString(1);

                result += String.valueOf(result_0) + " " + result_1 +

                        System.getProperty("line.separator");
            } else if (tablename.equals("Product")) {
                result_0 = cursor.getInt(0);

                result_4 = cursor.getInt(1);
                result_2 = cursor.getString(2);
                result_3 = cursor.getString(3);


                result += String.valueOf(result_0) + " " + String.valueOf(result_4) + " " + result_2 + " " + result_3 +

                        System.getProperty("line.separator");

            }



        }
        Log.e("table bulaya", query + " " + result);
        cursor.close();


        return result;
    }

    public void addHandler(String tablename, Account account) {  //add data to account

/*
        Account account=null;
        Product product=null;
*/
        ContentValues values = new ContentValues();

        if (tablename.equals("Account")) {

            //values.put(COLUMN_ID, account.getID());

            values.put(COLUMN_NAME, account.getAccountName());

            SQLiteDatabase db = this.getWritableDatabase();       //this command makes table again n again
            Log.e("data add", "insert " + account.getId() + account.getAccountName());

            db.insert(tablename, null, values);
            Log.e("after data add", "insert ke baad");
        }
        /*else if(tablename.equals("Product")) {

            values.put("ID", product.getID());

            values.put("User_ID", product.getUserID());

            values.put("Product_Name", product.getProductItem());

            values.put("Price", product.getItemPrice());

            SQLiteDatabase db = this.getWritableDatabase();
            Log.e("data add", "insert " + product.getID() + product.getUserID() + product.getProductItem() + product.getItemPrice());

            db.insert(TABLE_NAME, null, values);
            Log.e("after data add", "insert ke baad");
        }*/

    }

    public void addHandler(String tablename, Product product) {  // above function overloaded to add to product

/*
        Account account=null;
        Product product=null;
*/
        ContentValues values = new ContentValues();

        /*if(tablename.equals("Account")) {

            values.put(COLUMN_ID, account.getID());

            values.put(COLUMN_NAME, account.getAccountName());

            SQLiteDatabase db = this.getWritableDatabase();
            Log.e("data add", "insert " + account.getID() + account.getAccountName());

            db.insert(TABLE_NAME, null, values);
            Log.e("after data add", "insert ke baad");
        }*/
        if (tablename.equals("Product")) {
            // edhar pe code likh where condn daalke and dono table connect karke


            values.put("ID", product.getId());

            values.put("User_ID", product.getUserId());

            values.put("Product_Name", product.getProductItem());

            values.put("Price", product.getItemPrice());

            SQLiteDatabase db = this.getWritableDatabase();      //this command makes table again n again
            Log.e("data add", "insert " + product.getId() + product.getUserId() + product.getProductItem() + product.getItemPrice());

            db.insert(tablename, null, values);
            Log.e("after data add", "insert ke baad");
        }

    }

    public Account findHandler(String name, String tablename) {     //name se table me dhundho
        String query = "Select * FROM " + tablename + " WHERE " + COLUMN_NAME + " = " + "'" + name + "' ;";

        SQLiteDatabase db = this.getWritableDatabase();          //this command makes table again n again

        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            account.setId(Integer.parseInt(cursor.getString(0)));

            account.setAccountName(cursor.getString(1));

            cursor.close();

        } else {

            account = null;

        }

        return account;
    }

    public Account findHandler(int ID, String tablename) {         //id se table me dhundho
        String query = "Select * FROM " + tablename + " WHERE " + COLUMN_ID + " = " + "'" + ID + "' ;";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            account.setId(Integer.parseInt(cursor.getString(0)));

            account.setAccountName(cursor.getString(1));

            cursor.close();

        } else {

            account = null;

        }

        return account;
    }


    //public boolean deleteHandler(int ID) {}

    //public boolean updateHandler(int ID, String name) {}

}