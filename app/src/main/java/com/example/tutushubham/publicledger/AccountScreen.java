package com.example.tutushubham.publicledger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class AccountScreen extends AppCompatActivity {

    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);

        String name = getIntent().getStringExtra("name");

        //View view = inflater.inflate(R.layout.activity_account_screen, container, false);

        tb = findViewById(R.id.title1);
        TextView accName;
        accName = findViewById(R.id.accName);
        accName.setText(name);

        //  findAccountt(tb, name);

    }

    public void findAccountt(View view, String name) {

        //MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        TextView accName;
        accName = view.findViewById(R.id.accName);

       /* Account account =

                dbHandler.findHandler(name);

        if (account != null) {

            accName.setText(name);

           *//* lst.setText(String.valueOf(account.getID()) + " " + account.getAccountName() + System.getProperty("line.separator"));

            studentid.setText("");

            studentname.setText("");*//*

        } else {

            accName.setText("No Match Found");

            *//*studentid.setText("");

            studentname.setText("");*//*

        }*/

    }

}
