package com.jorkyin.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.jorkyin.myapp.girdviewdemo.GridViewDemo;
import com.jorkyin.myapp.listViewDemo.ListViewDemo;
import com.jorkyin.myapp.scrolDemo.ScrolViewDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
    }

    private void initActivity() {
        findViewById(R.id.main_bt_listviewDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_gridviewDemo).setOnClickListener(this);
        findViewById(R.id.main_bt_scrolviewDemo).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_listviewDemo:
                startActivity(new Intent(MainActivity.this, ListViewDemo.class));
                break;
            case R.id.main_bt_gridviewDemo:
                startActivity(new Intent(MainActivity.this, GridViewDemo.class));
                break;
            case R.id.main_bt_scrolviewDemo:
                startActivity(new Intent(MainActivity.this, ScrolViewDemo.class));
                break;
        }
    }
}
