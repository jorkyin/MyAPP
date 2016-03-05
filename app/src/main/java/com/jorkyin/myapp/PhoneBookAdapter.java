package com.jorkyin.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinJian on 2016/3/2.
 */
public class PhoneBookAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<UserInfo> mUserInfo = new ArrayList<>();

    public PhoneBookAdapter(Context context, List<UserInfo> userInfos) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUserInfo = userInfos;
    }

    @Override
    public int getCount() {
        //返回多少条数据
        return mUserInfo.size();
    }

    @Override
    public Object getItem(int position) {
        //返回某条数据的对象
        return mUserInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        //返回某条数据的ID
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //设置不同的ItemView
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //返回数据视图
        if (convertView == null) {
            //解析item_phone_book_friend，并赋值给convertView；
            convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friend, null);
            viewHolder = new ViewHolder();
            //在View中获取控件
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.item_phoneBook_tv_name);
            viewHolder.tx_age = (TextView) convertView.findViewById(R.id.item_phoneBook_tv_age);
            viewHolder.tv_avatar = (TextView) convertView.findViewById(R.id.item_phoneBook_iv_avatar);
            //设置控件标记
            convertView.setTag(viewHolder);
        } else {
            //获取控件标记
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //和数据之间进行绑定
        viewHolder.tv_name.setText(mUserInfo.get(position).getName());
        viewHolder.tx_age.setText(mUserInfo.get(position).getAge() + "岁");
        viewHolder.tv_avatar.setText(mUserInfo.get(position).getName().subSequence(0, 1).toString().toUpperCase());

        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tx_age;
        TextView tv_avatar;
    }

    /**
     * 刷新数据
     **/
    public void refreshData(List<UserInfo> userInfo) {
        mUserInfo = userInfo;

        //跟新列表数据
        notifyDataSetChanged();
    }
}
