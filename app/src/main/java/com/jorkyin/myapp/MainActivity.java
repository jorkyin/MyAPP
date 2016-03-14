package com.jorkyin.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jorkyin.myapp.layoutDemo.LayoutDemoActivity;
import com.jorkyin.myapp.listViewDemo.ListViewDemo;

public class MainActivity extends AppCompatActivity {

    private Button mBt_listviewDemo;
    private Button mBt_layoutDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBt_listviewDemo = (Button) findViewById(R.id.main_bt_listviewDemo);
        mBt_layoutDemo = (Button) findViewById(R.id.main_bt_layoutDemo);

        mBt_listviewDemo.setOnClickListener(new ButtonListener());
        mBt_layoutDemo.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {

        private Intent mIntent;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_bt_listviewDemo:
                    mIntent = new Intent(MainActivity.this,ListViewDemo.class);
                    startActivity(mIntent);
                    break;
                case R.id.main_bt_layoutDemo:
                    mIntent=new Intent(MainActivity.this, LayoutDemoActivity.class);
                    startActivity(mIntent);
                    break;
            }
        }
    }
}
