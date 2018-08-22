package com.example.tutushubham.publicledger;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<String> lists;
    Context context;

    public CustomAdapter(ArrayList<String> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public String getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.samplec_contact,parent,false);
        TextView name=listItem.findViewById(R.id.text1);
        TextView amount=listItem.findViewById(R.id.text2);

        Log.e("adapter", "Setting text");

        name.setText(getItem(position));

        return listItem;
    }
}
