package com.example.tutushubham.publicledger;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {

    //information of database

    public static final String TABLE_NAME = "Account";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "AccountName";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AccountDB.db";

    public MyDBHandler(Tab1Accounts context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context.getContext(), name, factory, version);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE if not exists " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER(10) PRIMARY KEY , "
                + COLUMN_NAME + " VARCHAR(40) , "
                + " Item " + " VARCHAR(40) , "
                + " Price " + " VARCHAR(40) " +
                " );";

        db.execSQL(CREATE_TABLE);

        Log.e("table", CREATE_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public int size() {

        SQLiteDatabase db = this.getWritableDatabase();

        int result_0 = 0;
        String query = "Select *  FROM " + TABLE_NAME + " ;";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            result_0++;

        }
        return result_0;
    }

    public String loadHandler() {
        String result = "";

        String query = "Select * FROM " + TABLE_NAME + " ;";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            int result_0 = cursor.getInt(0);

            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);

            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 + " " + result_3 +

                    System.getProperty("line.separator");

        }
        Log.e("table bulaya", query + " " + result);
        cursor.close();


        return result;
    }

    public void addHandler(Account account) {

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, account.getID());

        values.put(COLUMN_NAME, account.getAccountName());

        values.put("Item", account.getAccountItem());

        values.put("Price", account.getItemPrice());

        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("data add", "insert " + account.getID() + account.getAccountName() + account.getAccountItem() + account.getItemPrice());

        db.insert(TABLE_NAME, null, values);
        Log.e("after data add", "insert ke baad");


    }

    public Account findHandler(String accounttname) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + accounttname + "' ;";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Account account = new Account();

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            account.setID(Integer.parseInt(cursor.getString(0)));

            account.setAccountName(cursor.getString(1));

            account.setAccountItem(cursor.getString(2));

            account.setItemPrice(cursor.getString(3));

            cursor.close();

        } else {

            account = null;

        }

        return account;
    }

    //public boolean deleteHandler(int ID) {}

    //public boolean updateHandler(int ID, String name) {}

}