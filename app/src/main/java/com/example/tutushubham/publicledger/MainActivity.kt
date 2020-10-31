package com.example.tutushubham.publicledger

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class MainActivity : AppCompatActivity() {
    var PICK_CONTACT = 0

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager = findViewById(R.id.container)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)

        mViewPager?.apply {
            adapter = mSectionsPagerAdapter
            addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
            tabLayout?.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(this))
        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);


                startActivityForResult(intent, 1);
            }
        });*/
    }

    /*@Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use
                        Toast.makeText(MainActivity.this, name,
                                Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {                             //tabs set karna
            return when (position) {
                Constants.TAB_POSITION_0 -> {
                    Tab1Accounts()
                }
                Constants.TAB_POSITION_1 -> {
                    Tab2Calendar()
                }
                Constants.TAB_POSITION_2 -> {
                    Tab3Notes()
                }
                Constants.TAB_POSITION_3 -> {
                    Tab4Calc()
                }
                else -> Tab1Accounts() // Default
            }
        }

        override fun getCount(): Int {           //total tabs
            return Constants.DEFAULT_TAB_POSITION
        }

        override fun getPageTitle(position: Int): CharSequence? {            //tabs ka title (buggy)
            return when (position) {
                0 -> "Accounts"
                1 -> "Calendar"
                2 -> "Notes"
                3 -> "Calculator"
                else -> null
            }
        }
    }
}