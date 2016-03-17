package com.jorkyin.myapp.myFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.jorkyin.myapp.R;


/**
 * Created by YinJian on 2016/3/17.
 */
public class TestFragmentActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text_fragment);

        //Fragment管理对象
        FragmentManager fragmentManager = getFragmentManager();

        //Fragment处理对象
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //创建一个Fragment对象的实例
        TestFragment testFragment = new TestFragment();

        //将其添加到ViewGroup
        fragmentTransaction.add(R.id.fragment_rl_view,testFragment).commit();

       /* //将其移除
        fragmentTransaction.remove(testFragment);

        //找到我们的Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_text);

        if (fragment instanceof TestFragment){

        }else {
            throw new IllegalStateException("Is not testFragment");
        }
*/

    }
}
