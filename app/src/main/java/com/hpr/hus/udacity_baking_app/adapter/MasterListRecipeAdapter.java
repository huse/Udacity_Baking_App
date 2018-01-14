package com.hpr.hus.udacity_baking_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hk640d on 12/4/2017.
 */

public class MasterListRecipeAdapter extends BaseAdapter{

    private Context mContext;
    private List<Integer> mRecipeIds;

    public MasterListRecipeAdapter(Context context, List<Integer> recipeIds) {
        mContext = context;
        mRecipeIds = recipeIds;
    }

    @Override
    public int getCount() {
        return mRecipeIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {

            textView = new TextView(mContext);

            textView.setPadding(16, 16, 16, 16);
        } else {
            textView = (TextView) convertView;
        }

        // Set the image resource and return the newly created ImageView
        textView.setText(mRecipeIds.get(position)+"");
        return textView;
    }


}
