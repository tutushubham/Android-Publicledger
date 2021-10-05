package com.example.tutushubham.publicledger

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class Tab1Accounts : Fragment(R.layout.tab1accounts) {

    private var lists = ArrayList<String>()
    private var account = Account()
    private var lv: ListView? = null
    private var dbHandler = MyDBHandler(activity)
    // private val id = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tab1accounts, container, false)
        lv = view.findViewById(R.id.contacts)


        // contact selection
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(intent, 1)
        }
        return view
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        when (reqCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val contactData = data!!.data
                val c = requireActivity().managedQuery(contactData, null, null, null, null)
                if (c.moveToFirst()) {
                    val name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)) //selected contact
                    //String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    account.accountName = name
                    //account.setID(id);

                    //dbHandler.addHandler(account);
                    lateinit var account1: Account

                    //Log.e("Size", ""+dbHandler.size());
                    if (dbHandler.size() > 0) {  //adding to table
                        account1 = dbHandler!!.findHandler(name, "Account")!!
                        if (account1 == null) {
                            dbHandler.addHandler("Account", account)
                            //id++;
                        } else if (account1.accountName == name) {
                            Log.e("mat karo add", " code chala")
                        }
                    } else {
                        dbHandler.addHandler("Account", account)
                        //id++;
                    }
                    account1 = dbHandler!!.findHandler(name, "Account")!!
                    if (lists.isEmpty() || !lists.contains(name)) {  //adding to list
                        if (account1 != null) lists.add(account1!!.accountName!!) else lists.add(account!!.accountName!!)
                    }
                    Log.e("List of contacts", lists.toString())
                    val s = dbHandler.loadHandler("Account") //checking table in log
                    Log.e("table created", s)
                    val adapter = CustomAdapter(lists, context!!)
                    Log.e("Test", "Setting adapter")
                    lv!!.adapter = adapter //setting list on screen later to be done with table
                    lv!!.onItemClickListener = OnItemClickListener { parent, view, position, id -> //list se next screen jana
                        account = dbHandler!!.findHandler(lists[position], "Account")!! //selected list item ki table details
                        val intent = Intent(activity, AccountScreen::class.java)
                        intent.putExtra("name", lists[position])
                        intent.putExtra("User_ID", account.id)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}