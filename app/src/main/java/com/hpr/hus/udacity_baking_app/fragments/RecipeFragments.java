package com.hpr.hus.udacity_baking_app.fragments;


import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hpr.hus.udacity_baking_app.ActivityRecipe;
import com.hpr.hus.udacity_baking_app.R;
import com.hpr.hus.udacity_baking_app.adapter.RecipeAdapter;
import com.hpr.hus.udacity_baking_app.json.ParsingRecipe;
import com.hpr.hus.udacity_baking_app.json.ParsingSteps;
import com.hpr.hus.udacity_baking_app.json.RecipeIdlingResource;
import com.hpr.hus.udacity_baking_app.json.RetroJsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hk640d on 12/3/2017.
 */

public class RecipeFragments extends Fragment{
int counter =0;
    static String ALL_RECIPES="All_Recipes";
   public RecipeFragments(){

   }
    RecipeIdlingResource idlingResource;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_text_fragment,container, false);
        final TextView textViewtext = (TextView) rootView.findViewById( R.id.recipe_fragment_tv);
        Log.v("uuu" , "textview  " + textViewtext);
        RecyclerView recyclerView;
        textViewtext.setText("this is for test");
        final RecipeAdapter recipesAdapter =new RecipeAdapter((ActivityRecipe)getActivity());
      //  recyclerView.setAdapter(recipesAdapter);
        final RetroJsonBuilder.GetJsonRecipeInterface jsonRecipeInterface = RetroJsonBuilder.RetrieveJson();
        Call<ArrayList<ParsingRecipe>> recipe = jsonRecipeInterface.getRecipe();


        textViewtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("uuu" , "textview  " + "clicked");
                textViewtext.setText("this number is for test:"  + counter++);

            }
        });

        idlingResource = (RecipeIdlingResource)((ActivityRecipe)getActivity()).getIdlingResource();

        recipe.enqueue(new Callback<ArrayList<ParsingRecipe>>() {
            @Override
            public void onResponse(Call<ArrayList<ParsingRecipe>> call, Response<ArrayList<ParsingRecipe>> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                ArrayList<ParsingRecipe> recipes = response.body();

                Bundle recipesBundle = new Bundle();
                recipesBundle.putParcelableArrayList(ALL_RECIPES, recipes);

                recipesAdapter.setRecipeData(recipes,getContext());
                if (idlingResource != null) {
                    idlingResource.setIdleState(true);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ParsingRecipe>> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });

        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle currentState) {
       // Needs implementation for saving the status in device rotation.
       /*currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);*/
    }
}
