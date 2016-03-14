package com.jorkyin.myapp.girdviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jorkyin.myapp.R;
import com.jorkyin.myapp.data.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinJian on 2016/3/2.
 */
public class GridViewDemo extends Activity {

    private GridView mPhoneBookGridView;
    private List<UserInfo> mUserInfo = new ArrayList<>();
    private GridAdapter mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_demo);

        //初始化ListView控件
        mPhoneBookGridView = (GridView) findViewById(R.id.grid_view);

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


        mGridAdapter = new GridAdapter(GridViewDemo.this, mUserInfo);

        //设置Adapter参数
        mPhoneBookGridView.setAdapter(mGridAdapter);

        //Item点击事件监听
        mPhoneBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mUserInfo.get(position) != null) {
                    //修改数据
                    mUserInfo.get(position).setName("ddfsfsf");
                    mUserInfo.get(position).setAge(56);
                    //更新页面
                    mGridAdapter.refreshData(mUserInfo);
                } else {
                    Toast.makeText(GridViewDemo.this, "数据为空", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Item长按事件监听
        mPhoneBookGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewDemo.this, "长按 ", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
