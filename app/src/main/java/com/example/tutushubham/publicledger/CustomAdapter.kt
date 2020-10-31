package com.example.tutushubham.publicledger

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class CustomAdapter(var lists: ArrayList<String>, var context: Context) : BaseAdapter() {
    override fun getCount() = lists.size

    override fun getItem(position: Int) = lists[position]


    override fun getItemId(position: Int) = 0.toLong()

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val name = convertView.findViewById<TextView>(R.id.text1)
        val amount = convertView.findViewById<TextView>(R.id.text2)
        Log.e("adapter", "Setting text")
        name.text = getItem(position)
        return convertView
    }
}