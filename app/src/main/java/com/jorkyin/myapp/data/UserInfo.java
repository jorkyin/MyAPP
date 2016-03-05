package com.jorkyin.myapp.data;

/**
 * Created by YinJian on 2016/3/4.
 */
public class UserInfo {
    private String mName;
    private int mAge;

    public UserInfo(String name, int age) {
        mName = name;
        mAge = age;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


}
