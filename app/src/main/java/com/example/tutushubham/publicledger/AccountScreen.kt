package com.example.tutushubham.publicledger

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class AccountScreen : AppCompatActivity() {
    private var tb: Toolbar? = null
    private var item: EditText? = null
    private var price: EditText? = null
    private var name: String? = null
    private var p = Product()
    private var addProduct: Button? = null
    private var dbHandler = MyDBHandler(this@AccountScreen) //error could be here but not sure how to pass dbhandler from TAb1Accounts to here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_screen)
        item = findViewById(R.id.item)
        price = findViewById(R.id.price)
        name = intent.getStringExtra("name")
        val ID = intent.getIntExtra("User_ID", 1)
        addProduct = findViewById(R.id.button_product_send)
        addProduct?.let { btnAdd ->
            btnAdd.setOnClickListener {
                val itemget = item?.text.toString()
                val priceget = price?.getText().toString()
                Log.e("data mila", "" + itemget + priceget + ID + name)
                p.itemPrice = priceget
                p.productItem = itemget
                p.userId = ID //product object me data dala intent n edittext se
                dbHandler.addHandler("Product", p) //item added but previous data not retained...new table created
                item?.setText("")
                price?.setText("")
                Log.e("product", "product dal gaya $ID ke naam pe")
                Log.e("account table ", dbHandler.loadHandler("Account")) //prev table should come but new table is being made so its empty
                Log.e("product table ", dbHandler.loadHandler("Product")) // data is being added but as fresh entry
            }
        }


        //View view = inflater.inflate(R.layout.activity_account_screen, container, false);
        tb = findViewById(R.id.title1)
        val accName: TextView = findViewById(R.id.accName)
        accName.text = name

        //  findAccountt(tb, name);
    } /* public void findAccountt(View view, String name) {

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        TextView accName;
        accName = view.findViewById(R.id.accName);

        Account account =

                dbHandler.findHandler(name);

        if (account != null) {

            accName.setText(name);

           lst.setText(String.valueOf(account.getID()) + " " + account.getAccountName() + System.getProperty("line.separator"));

            studentid.setText("");

            studentname.setText("");

        } else {

            accName.setText("No Match Found");

            */
    /**/ /*studentid.setText("");

            studentname.setText("");

        }

    }*/
}