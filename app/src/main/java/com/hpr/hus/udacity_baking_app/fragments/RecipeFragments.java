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
import android.widget.Toast;

import com.hpr.hus.udacity_baking_app.R;

import java.util.ArrayList;

/**
 * Created by hk640d on 12/3/2017.
 */

public class RecipeFragments extends Fragment{
int counter =0;
   public RecipeFragments(){

   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_text_fragment,container, false);
        final TextView textViewtext = rootView.findViewById( R.id.recipe_fragment_tv);
        Log.v("uuu" , "textview  " + textViewtext);

        textViewtext.setText("this is for test");




        textViewtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("uuu" , "textview  " + "clicked");
                textViewtext.setText("this number is for test:"  + counter++);

            }
        });
        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle currentState) {
       // Needs implementation
       /*currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);*/
    }
}
