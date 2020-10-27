package com.example.tutushubham.publicledger

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDBHandler  /* public static synchronized MyDBHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MyDBHandler(context);
        }
        return sInstance;
    }
*/
internal constructor(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        Log.e("creating db", "create called")
        val CREATE_TABLE_ACCOUNT = ("CREATE TABLE if not exists " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " VARCHAR(40) "
                + " );")
        val CREATE_TABLE_PRODUCT = ("CREATE TABLE if not exists " + " Product " + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " User_ID INTEGER , "
                + " Product_Name VARCHAR(40) , "
                + " Price VARCHAR(40), "
                + " AccID  INTEGER, FOREIGN KEY(AccID) REFERENCES Account(ID)"
                + " );")
        db.execSQL(CREATE_TABLE_ACCOUNT)
        db.execSQL(CREATE_TABLE_PRODUCT)
        Log.e("table", """
     $CREATE_TABLE_ACCOUNT
     $CREATE_TABLE_PRODUCT
     """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {}

    //neeche hai databse ke methods
    fun size(): Int {
        val db = this.writableDatabase //this command makes table again n again
        var result_0 = 0
        val query = "Select *  FROM $TABLE_NAME ;"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            result_0++
        }
        cursor.close()
        return result_0
    }

    fun loadHandler(tablename: String): String {   //to show table
        var result = ""
        var result_2 = ""
        var result_3 = ""
        var result_1 = ""
        var result_0: Int
        var result_4: Int
        val query = "Select * FROM $tablename ;"
        val db = this.writableDatabase //this command makes table again n again
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (tablename == "Account") {
                result_0 = cursor.getInt(0)
                result_1 = cursor.getString(1)
                result += result_0.toString() + " " + result_1 +
                        System.getProperty("line.separator")
            } else if (tablename == "Product") {
                result_0 = cursor.getInt(0)
                result_4 = cursor.getInt(1)
                result_2 = cursor.getString(2)
                result_3 = cursor.getString(3)
                result += result_0.toString() + " " + result_4.toString() + " " + result_2 + " " + result_3 +
                        System.getProperty("line.separator")
            }
        }
        Log.e("table bulaya", "$query $result")
        cursor.close()
        return result
    }

    fun addHandler(tablename: String, account: Account) {  //add data to account

/*
        Account account=null;
        Product product=null;
*/
        val values = ContentValues()
        if (tablename == "Account") {

            //values.put(COLUMN_ID, account.getID());
            values.put(COLUMN_NAME, account.accountName)
            val db = this.writableDatabase //this command makes table again n again
            Log.e("data add", "insert " + account.id + account.accountName)
            db.insert(tablename, null, values)
            Log.e("after data add", "insert ke baad")
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

    fun addHandler(tablename: String, product: Product) {  // above function overloaded to add to product

/*
        Account account=null;
        Product product=null;
*/
        val values = ContentValues()

        /*if(tablename.equals("Account")) {

            values.put(COLUMN_ID, account.getID());

            values.put(COLUMN_NAME, account.getAccountName());

            SQLiteDatabase db = this.getWritableDatabase();
            Log.e("data add", "insert " + account.getID() + account.getAccountName());

            db.insert(TABLE_NAME, null, values);
            Log.e("after data add", "insert ke baad");
        }*/if (tablename == "Product") {
            // edhar pe code likh where condn daalke and dono table connect karke
            values.put("ID", product.id)
            values.put("User_ID", product.userId)
            values.put("Product_Name", product.productItem)
            values.put("Price", product.itemPrice)
            val db = this.writableDatabase //this command makes table again n again
            Log.e("data add", "insert " + product.id + product.userId + product.productItem + product.itemPrice)
            db.insert(tablename, null, values)
            Log.e("after data add", "insert ke baad")
        }
    }

    fun findHandler(name: String, tablename: String): Account? {     //name se table me dhundho
        val query = "Select * FROM $tablename WHERE $COLUMN_NAME = '$name' ;"
        val db = this.writableDatabase //this command makes table again n again
        val cursor = db.rawQuery(query, null)
        var account: Account? = Account()
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            account!!.id = cursor.getString(0).toInt()
            account.accountName = cursor.getString(1)
            cursor.close()
        } else {
            account = null
        }
        return account
    }

    fun findHandler(ID: Int, tablename: String): Account? {         //id se table me dhundho
        val query = "Select * FROM $tablename WHERE $COLUMN_ID = '$ID' ;"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var account: Account? = Account()
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            account!!.id = cursor.getString(0).toInt()
            account.accountName = cursor.getString(1)
            cursor.close()
        } else {
            account = null
        }
        return account
    } //public boolean deleteHandler(int ID) {}

    //public boolean updateHandler(int ID, String name) {}
    companion object {
        //private static MyDBHandler sInstance;
        //SQLiteDatabase db = getWritableDatabase();
        //information of database
        const val TABLE_NAME = "Account"
        const val COLUMN_ID = "ID"
        const val COLUMN_NAME = "AccountName"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AccountDB.db"
    }
}