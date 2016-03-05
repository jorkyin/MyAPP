package com.jorkyin.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinJian on 2016/3/2.
 */
public class ListViewDemo extends Activity {

    private ListView mPhoneBookListView;
    private List<UserInfo> mUserInfo = new ArrayList<>();
    private PhoneBookAdapter mPhoneBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);

        //初始化ListView控件
        mPhoneBookListView = (ListView) findViewById(R.id.list_view);

        //添加数据
        mUserInfo.add(new UserInfo("jorkdn", 170));
        mUserInfo.add(new UserInfo("jodyin", 120));
        mUserInfo.add(new UserInfo("jordin", 170));
        mUserInfo.add(new UserInfo("jordin", 140));
        mUserInfo.add(new UserInfo("jordin", 130));
        mUserInfo.add(new UserInfo("jordin", 170));
        mUserInfo.add(new UserInfo("jordin", 160));
        mUserInfo.add(new UserInfo("jdyin", 130));
        mUserInfo.add(new UserInfo("jorkyin", 120));
        mUserInfo.add(new UserInfo("jorkyin", 130));
        mUserInfo.add(new UserInfo("jdyin", 104));
        mUserInfo.add(new UserInfo("jordin", 130));
        mUserInfo.add(new UserInfo("jordin", 170));
        mUserInfo.add(new UserInfo("jordin", 160));
        mUserInfo.add(new UserInfo("jdyin", 130));
        mUserInfo.add(new UserInfo("jorkyin", 120));
        mUserInfo.add(new UserInfo("jorkyin", 130));
        mUserInfo.add(new UserInfo("jdyin", 104));
        mUserInfo.add(new UserInfo("jordin", 130));
        mUserInfo.add(new UserInfo("jordin", 170));
        mUserInfo.add(new UserInfo("jordin", 160));
        mUserInfo.add(new UserInfo("jdyin", 130));
        mUserInfo.add(new UserInfo("jorkyin", 120));
        mUserInfo.add(new UserInfo("jorkyin", 130));
        mUserInfo.add(new UserInfo("jdyin", 104));
        mUserInfo.add(new UserInfo("jordin", 130));
        mUserInfo.add(new UserInfo("jordin", 170));
        mUserInfo.add(new UserInfo("jordin", 160));
        mUserInfo.add(new UserInfo("jdyin", 130));
        mUserInfo.add(new UserInfo("jorkyin", 120));
        mUserInfo.add(new UserInfo("jorkyin", 130));
        mUserInfo.add(new UserInfo("jdyin", 104));
        mUserInfo.add(new UserInfo("jordin", 130));


        mPhoneBookAdapter = new PhoneBookAdapter(ListViewDemo.this, mUserInfo);

        //设置Adapter参数
        mPhoneBookListView.setAdapter(mPhoneBookAdapter);

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
