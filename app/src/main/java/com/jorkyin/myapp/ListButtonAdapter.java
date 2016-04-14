package com.jorkyin.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YinJian on 2016/4/14.
 */
public class ListButtonAdapter extends BaseAdapter {
    private Context mContext;
    private List<MainActivityInfo> mButtonList;
    private final LayoutInflater mLayoutInflater;

    public ListButtonAdapter(Context context, List<MainActivityInfo> buttonList) {
        mContext = context;
        mButtonList = buttonList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mButtonList.size();
    }

    @Override
    public MainActivityInfo getItem(int position) {
        return mButtonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_button_friend, null);
            viewHolder = new ViewHolder();
            viewHolder.itemNmae = (TextView) convertView.findViewById(R.id.item_button_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemNmae.setText(mButtonList.get(position).getButtonName());
        return convertView;
    }

    class ViewHolder {
        TextView itemNmae;
    }
}
