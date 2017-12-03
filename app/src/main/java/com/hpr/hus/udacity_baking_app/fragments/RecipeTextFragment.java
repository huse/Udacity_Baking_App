package com.hpr.hus.udacity_baking_app.fragments;


import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpr.hus.udacity_baking_app.R;

/**
 * Created by hk640d on 12/3/2017.
 */

public class RecipeTextFragment extends Fragment{

   public RecipeTextFragment(){

   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_text_fragment,container, false);
        TextView textView = rootView.findViewById( R.id.recipe_fragment_tv);
        Log.v("uuu" , "textview  " + textView);

        textView.setText("this is for test");
        return rootView;
    }

}
