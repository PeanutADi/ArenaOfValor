package com.example.alias.arenaofvalor;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.alias.arenaofvalor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Hero> mList = new ArrayList<>();

    public MyAdapter(Context context, List<Hero> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.herolist, null);
            viewHolder.mName = view.findViewById(R.id.name);
            viewHolder.mPosition= view.findViewById(R.id.position);
            viewHolder.mPicture= view.findViewById(R.id.imageView);
            viewHolder.mAlias=view.findViewById(R.id.alias);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mName.setText(mList.get(i).name);
        viewHolder.mPosition.setText(mList.get(i).position);
        viewHolder.mAlias.setText(mList.get(i).alias);




        return view;
    }


    public interface onItemChangeListener {
        void onChangeClick(View v,int i);
    }

    private onItemChangeListener mOnItemChangeListener;

    public void setOnItemChangeListener(onItemChangeListener mOnItemChangeListener) {
        this.mOnItemChangeListener = mOnItemChangeListener;
    }

    class ViewHolder {
        TextView mName;
        TextView mPosition;
        ImageView mPicture;
        TextView mAlias;
    }

}
