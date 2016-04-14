package com.jorkyin.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jorkyin.myapp.broadcast.SendBrocastActivity;
import com.jorkyin.myapp.girdviewdemo.GridViewDemo;
import com.jorkyin.myapp.listViewDemo.ListViewDemo;
import com.jorkyin.myapp.myFragment.TestFragmentActivity;
import com.jorkyin.myapp.myHandler.TestHandlerActivity;
import com.jorkyin.myapp.myview.TestRedButtonActivity;
import com.jorkyin.myapp.scrolDemo.ScrolViewDemo;
import com.jorkyin.myapp.servers.MusicActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListview;
    private List<MainActivityInfo> mButtonList= new ArrayList<>();
    private ListButtonAdapter mListButtonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListview = (ListView) findViewById(R.id.listview);

        /*RWFile rwFile = new RWFile(MainActivity.this);

        rwFile.testFileDemo();
        rwFile.testAssets();
        rwFile.testResFile();
        rwFile.testSDCard();
*/
       //new XMLParse(MainActivity.this);
       initData();
    }

    private void initData() {
        mButtonList.add(new MainActivityInfo("ListViewDemo", ListViewDemo.class));
        mButtonList.add(new MainActivityInfo("GridViewDemo", GridViewDemo.class));
        mButtonList.add(new MainActivityInfo("ScrolViewDemo", ScrolViewDemo.class));
        mButtonList.add(new MainActivityInfo("MusicActivity", MusicActivity.class));
        mButtonList.add(new MainActivityInfo("TestRedButtonActivity", TestRedButtonActivity.class));
        mButtonList.add(new MainActivityInfo("TestFragmentActivity", TestFragmentActivity.class));
        mButtonList.add(new MainActivityInfo("TestHandlerActivity", TestHandlerActivity.class));
        mButtonList.add(new MainActivityInfo("SendBrocastActivity", SendBrocastActivity.class));
        mButtonList.add(new MainActivityInfo("NetWorkActivity", NetWorkActivity.class));
        mButtonList.add(new MainActivityInfo("ThreadActivity", ThreadActivity.class));

        mListButtonAdapter = new ListButtonAdapter(MainActivity.this, mButtonList);

        //在listview中添加Adapter
        mListview.setAdapter(mListButtonAdapter);

        //标签点击事件
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mButtonList != null) {
                    startActivity(new Intent(MainActivity.this, mButtonList.get(position).getCls()));
                }
            }
        });
    }
}
