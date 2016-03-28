package com.jorkyin.myapp.myFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.jorkyin.myapp.R;


/**
 * Created by YinJian on 2016/3/17.
 */
public class TestFragmentActivity extends Activity implements View.OnClickListener {

    private static final String TAG = TestFragmentActivity.class.getSimpleName();

    private ContentFragment mWeixin;
    private FriendFragment mFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_text_fragment);

        // 初始化控件和声明事件
        findViewById(R.id.tab_bottom_weixin).setOnClickListener(this);
        findViewById(R.id.tab_bottom_friend).setOnClickListener(this);

        // 设置默认的Fragment
        setDefaultFragment();

/*
        //Fragment管理对象
        FragmentManager fragmentManager = getFragmentManager();

        //Fragment处理对象
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //创建一个Fragment对象的实例
        TestFragment testFragment = new TestFragment();

        //将其添加到ViewGroup
        fragmentTransaction.add(R.id.fragment_rl_view,testFragment);

        //通知Fragment管理对象
        fragmentTransaction.commit();

        //将其移除
        fragmentTransaction.remove(testFragment);

        //找到我们的Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_text);

        if (fragment instanceof TestFragment){

        }else {
            throw new IllegalStateException("Is not testFragment");
        }
*/

    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ContentFragment mWeixin = new ContentFragment();
        fragmentTransaction.replace(R.id.id_content, mWeixin).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.tab_bottom_weixin:
                if (mWeixin == null) {
                    mWeixin = new ContentFragment();
                }
                Log.i(TAG,"tab_bottom_weixin");
                // 使用当前Fragment的布局替代id_content的控件
                fragmentTransaction.replace(R.id.id_content, mWeixin);
                break;
            case R.id.tab_bottom_friend:
                Log.i(TAG,"tab_bottom_friend");
                if (mFriend == null) {
                    mFriend = new FriendFragment();
                }
                fragmentTransaction.replace(R.id.id_content, mFriend);
                break;
        }
        // 事务提交
        fragmentTransaction.commit();
    }
}
