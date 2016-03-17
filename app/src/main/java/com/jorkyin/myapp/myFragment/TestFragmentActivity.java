package com.jorkyin.myapp.myFragment;

import android.app.Activity;
import android.app.FragmentManager;
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

        FragmentManager fragmentManager = getFragmentManager();
    }
}
