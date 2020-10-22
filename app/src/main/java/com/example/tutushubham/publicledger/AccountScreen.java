package com.example.tutushubham.publicledger;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class AccountScreen extends AppCompatActivity {

    Toolbar tb;
    EditText item, price;
    String name;
    Product p = new Product();
    Button addProduct;

    MyDBHandler dbHandler = new MyDBHandler(AccountScreen.this);  //error could be here but not sure how to pass dbhandler from TAb1Accounts to here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);
        item = findViewById(R.id.item);
        price = findViewById(R.id.price);
        name = getIntent().getStringExtra("name");
        final int ID = getIntent().getIntExtra("User_ID", 1);

        addProduct = findViewById(R.id.button_product_send);

        addProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String itemget = item.getText().toString();
                String priceget = price.getText().toString();

                Log.e("data mila", "" + itemget + priceget + ID + name);

                p.setItemPrice(priceget);
                p.setProductItem(itemget);
                p.setUserID(ID);              //product object me data dala intent n edittext se


                dbHandler.addHandler("Product", p);       //item added but previous data not retained...new table created

                item.setText("");
                price.setText("");
                Log.e("product", "product dal gaya " + ID + " ke naam pe");

                Log.e("account table ", dbHandler.loadHandler("Account"));        //prev table should come but new table is being made so its empty

                Log.e("product table ", dbHandler.loadHandler("Product"));        // data is being added but as fresh entry

            }
        });


        //View view = inflater.inflate(R.layout.activity_account_screen, container, false);

        tb = findViewById(R.id.title1);
        TextView accName;
        accName = findViewById(R.id.accName);
        accName.setText(name);

        //  findAccountt(tb, name);

    }


   /* public void findAccountt(View view, String name) {

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

            *//**//*studentid.setText("");

            studentname.setText("");

        }

    }*/

}
