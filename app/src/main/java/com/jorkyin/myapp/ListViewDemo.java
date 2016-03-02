package com.jorkyin.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by YinJian on 2016/3/2.
 */
public class ListViewDemo extends Activity {

    private ListView mPhoneBookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);

        mPhoneBookListView = (ListView) findViewById(R.id.list_view);
        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(ListViewDemo.this);
        mPhoneBookListView.setAdapter(phoneBookAdapter);
    }
}
