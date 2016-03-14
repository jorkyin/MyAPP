package com.jorkyin.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jorkyin.myapp.listViewDemo.ListViewDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_bt_listviewDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_layoutDemo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_listviewDemo:
                startActivity(new Intent(MainActivity.this, ListViewDemo.class));
                break;

        }
    }
}
