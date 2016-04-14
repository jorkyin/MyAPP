package com.jorkyin.myapp;

/**
 * Created by YinJian on 2016/4/14.
 */
public class MainActivityInfo {
    private String mButtonName;
    private Class<?> mCls;

    public MainActivityInfo(String buttonName, Class<?> cls) {
        mButtonName = buttonName;
        this.mCls = cls;
    }

    public Class<?> getCls() {
        return mCls;
    }

    public void setCls(Class<?> cls) {
        this.mCls = cls;
    }

    public String getButtonName() {
        return mButtonName;
    }

    public void setButtonName(String buttonName) {
        mButtonName = buttonName;
    }
}
