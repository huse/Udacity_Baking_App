package com.hpr.hus.udacity_baking_app.fragments;


import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hpr.hus.udacity_baking_app.MainActivity;
import com.hpr.hus.udacity_baking_app.R;
import com.hpr.hus.udacity_baking_app.adapter.RecipeAdapter;
import com.hpr.hus.udacity_baking_app.graphic.RecipeDetailActivity;
import com.hpr.hus.udacity_baking_app.json.GetJsonRecipeInterface;
import com.hpr.hus.udacity_baking_app.json.RecipeIdlingResource;
import com.hpr.hus.udacity_baking_app.json.RetroJsonBuilder;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hk640d on 12/3/2017.
 */

public class RecipeFragments extends Fragment implements RecipeAdapter.ListItemOnClickListener {
int counter =0;
    public static String RECIPES_ALL="Recipe_All";
    static String SELECTED_RECIPES="Select_Recipe";

    public static RecipeAdapter recipesAdapter;
    public static RecipeIdlingResource recipeIdlingResource;
   public RecipeFragments(){

   }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        RecyclerView recyclerView;
        View rootView = inflater.inflate(R.layout.fragment_recipe_recyclerview,container, false);
     //   final TextView textViewtext = (TextView) rootView.findViewById( R.id.recipe_fragment_tv);
     //   Log.v("uuu" , "textview  " + textViewtext);

        recyclerView=(RecyclerView)  rootView.findViewById(R.id.fragment_recipe_recyclerview_);
//        textViewtext.setText("this is for test");
        recipesAdapter =new RecipeAdapter(this);
      //  Log.v("uuu" , "recipesAdapter  " + recipesAdapter);
       // Log.v("uuu" , "recyclerView  " + recyclerView);
        recyclerView.setAdapter(recipesAdapter);
       /* GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(),4);
        recyclerView.setLayoutManager(mLayoutManager);*/

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);


        GetJsonRecipeInterface jsonRecipeInterface = RetroJsonBuilder.retrieveJson();
        Call<ArrayList<ParsingRecipe>> parsedRecipeCall = jsonRecipeInterface.getRecipe();
//ParsingRecipe parsedJ = RetroJsonBuilder3.retrieveJson();
      //  Call<ArrayList<ParsingRecipe>> parsedRecipeCall =  Call<ArrayList<parsedJ>>
                Log.v("uuu", "recipe  " + parsedRecipeCall);
     /*      textViewtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("uuu" , "textview  " + "clicked");
                textViewtext.setText("this number is for test:"  + counter++);

            }
        });*/


      //  RetroJsonBuilder.GetJsonRecipeInterface iRecipe = RetroJsonBuilder.RetrieveJson();
       // Call<ArrayList<RetroJsonBuilder.GetJsonRecipeInterface>> recipe2 = iRecipe.getRecipe();

        recipeIdlingResource = (RecipeIdlingResource)((MainActivity)getActivity()).getIdlingResource();
        Log.v("uuu4", "recipeIdlingResource  " + recipeIdlingResource);

        if (recipeIdlingResource != null) {
            recipeIdlingResource.setIdleState(false);
            Log.v("uuu5", "recipeIdlingResource false " + recipeIdlingResource);

        }

     //   recipeIdlingResource = (RecipeIdlingResource)((MainActivity)getActivity()).getIdlingResource();
        Log.v("uuu6", "enqueue");

       // CallbackRecipe callbackRecipe= new CallbackRecipe();
      //  parsedRecipeCall.enqueue(callbackRecipe);
       parsedRecipeCall.enqueue(new Callback<ArrayList<ParsingRecipe>>() {

            @Override
            public void onResponse(Call<ArrayList<ParsingRecipe>> call, Response<ArrayList<ParsingRecipe>> response) {
                Log.v("uuu7", "onResponse");
                Log.w("uuuuu",new Gson().toJson(response));
                Log.w("uuuuuu",new GsonBuilder().setPrettyPrinting().create().toJson(response));

                Integer statusCode = response.code();
                Log.v("hhhh", statusCode.toString());
                ArrayList<ParsingRecipe> recipes = response.body();

             /*  ParsingRecipe parsingRecipe = new ParsingRecipe();
                recipes.set(0, parsingRecipe);*/

                    for (ParsingRecipe r: recipes){
                    Log.v("uuu776", "  " + r.toString());
                         }
                Log.v("uuu777", "  " + "running setRecipeData");
                Bundle recipesBundle = new Bundle();
                recipesBundle.putParcelableArrayList(RECIPES_ALL, recipes);
                Log.v("uuu778", "  " + recipesBundle.toString());
                Log.v("uuu779", "  " + recipes.toString());

                Log.v("uuu880", "  " + "running setRecipeData");
                recipesAdapter.setRecipeData(recipes,getContext());
                Log.v("uuu781", "recipesAdapter  " + recipesAdapter);
                if (recipeIdlingResource != null) {
                    recipeIdlingResource.setIdleState(true);

                }

            }

            @Override
            public void onFailure(Call<ArrayList<ParsingRecipe>> call, Throwable t) {
                Log.v("uuue: ", "no internet");
                Toast.makeText(getActivity(),"No internet connection, app needs internet to work", Toast.LENGTH_LONG).show();


            }
        });
        Log.v("uuu000",  "end of enqueue");

        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle currentState) {
       // Needs implementation for saving the status in device rotation.
/*currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);*/

    }

    @Override
    public void onListItemClick(ParsingRecipe clickedItemIndex) {
        Bundle selectedRecipeBundle = new Bundle();
        ArrayList<ParsingRecipe> selectedRecipe = new ArrayList<>();
        selectedRecipe.add(clickedItemIndex);
        selectedRecipeBundle.putParcelableArrayList(SELECTED_RECIPES,selectedRecipe);

        final Intent intent = new Intent((MainActivity)getActivity(), RecipeDetailActivity.class);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);
    }
}
