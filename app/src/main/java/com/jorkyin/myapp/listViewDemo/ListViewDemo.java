package com.jorkyin.myapp.listViewDemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jorkyin.myapp.R;
import com.jorkyin.myapp.data.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinJian on 2016/3/2.
 */
public class ListViewDemo extends Activity implements View.OnClickListener {

    private ListView mPhoneBookListView;
    private List<UserInfo> mUserInfo = new ArrayList<>();
    private ListAdapter mPhoneBookAdapter;
    private int mDataCounts = 10;
    private EditText mCountsEditText;
    private Button mCountsButton;

    //系统自动创建XML，名字：preferences   地址：/data/data/com.jorkyin.myapp.listViewDemo/preferences.xml
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);
        mSharedPreferences = ListViewDemo.this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        //初始化控件
        initActivity();

        setData();
        setListeners();
    }

    private void initActivity() {
        //初始化控件
        mPhoneBookListView = (ListView) findViewById(R.id.list_view);
        mCountsEditText = (EditText) findViewById(R.id.data_counts_edit_text);
        mCountsButton = (Button) findViewById(R.id.data_counts_button);

        mCountsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_counts_button:
                String CountsString = mCountsEditText.getText().toString();
                mDataCounts = Integer.valueOf(CountsString);
                refreshListView();

                saveDataToPrefercence(mDataCounts);
                break;
        }
    }

    private void saveDataToPrefercence(int mDataCounts) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("list_view_Data_Counts", mDataCounts);
        //后台写入，另开线程
        editor.apply();
        //删除
        //editor.remove("list_view_Data_Counts");
        mSharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            //监听被修改的Key
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Toast.makeText(ListViewDemo.this, key + ":" + mSharedPreferences.getInt(key, 0), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refreshListView() {
        mUserInfo.clear();
        for (int index = 0; index < mDataCounts; index++) {
            //添加数据
            mUserInfo.add(new UserInfo("jorkdn", 170));
        }
        //更新页面
        mPhoneBookAdapter.refreshData(mUserInfo);
    }

    private void setData() {
        mDataCounts = mSharedPreferences.getInt("list_view_Data_Counts", 10);
        mCountsEditText.setText(String.valueOf(mDataCounts));

        for (int index = 0; index < mDataCounts; index++) {
            //添加数据
            mUserInfo.add(new UserInfo("jorkdn", 170));
        }

        mPhoneBookAdapter = new ListAdapter(ListViewDemo.this, mUserInfo);
        //设置Adapter参数
        mPhoneBookListView.setAdapter(mPhoneBookAdapter);
    }

    private void setListeners() {
        //Item点击事件监听
        mPhoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mUserInfo.get(position) != null) {
                    //修改数据
                    mUserInfo.get(position).setName("ddfsfsf");
                    mUserInfo.get(position).setAge(56);
                    //更新页面
                    mPhoneBookAdapter.refreshData(mUserInfo);
                } else {
                    Toast.makeText(ListViewDemo.this, "数据为空", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Item长按事件监听
        mPhoneBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewDemo.this, "长按 ", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
