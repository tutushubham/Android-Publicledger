package com.example.tutushubham.publicledger;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


public class Tab1Accounts extends Fragment {
    ArrayList<String> lists = new ArrayList<>();
    Account account = new Account();
    ListView lv;
    MyDBHandler dbHandler = new MyDBHandler(Tab1Accounts.this, null, null, 1);
    int id = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1accounts, container, false);

        lv = view.findViewById(R.id.contacts);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

                startActivityForResult(intent, 1);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();

                    Cursor c = Objects.requireNonNull(getActivity()).managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        //String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        account.setAccountName(name);
                        account.setID(id);
                        account.setItemPrice("null");
                        account.setAccountItem("null");

                        //dbHandler.addHandler(account);

                        Account account1;

                        //    Log.e("Size", ""+dbHandler.size());

                        if (dbHandler.size() > 0) {
                            account1 = dbHandler.findHandler(name);
                            if (account1 == null) {
                                dbHandler.addHandler(account);
                                id++;
                            } else if (account1.getAccountName().equals(name)) {
                                Log.e("mat karo add", " code chala");
                            }
                        } else {
                            dbHandler.addHandler(account);
                            id++;
                        }


                        account1 = dbHandler.findHandler(name);

                        if (lists.isEmpty() || !lists.contains(name)) {
                            if (account1 != null)
                                lists.add(account1.getAccountName());
                            else
                                lists.add(account.getAccountName());
                        }

                        Log.e("List of contacts", String.valueOf(lists));


                        String s = dbHandler.loadHandler();
                        Log.e("table created", s);


                        CustomAdapter adapter = new CustomAdapter(lists, getContext());
                        Log.e("Test", "Setting adapter");
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), AccountScreen.class);
                                intent.putExtra("name", lists.get(position));
                                startActivity(intent);

                            }
                        });

                    }
                }
                break;
        }
    }

}
