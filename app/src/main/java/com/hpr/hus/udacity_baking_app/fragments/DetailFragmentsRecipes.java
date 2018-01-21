package com.hpr.hus.udacity_baking_app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpr.hus.udacity_baking_app.R;
import com.hpr.hus.udacity_baking_app.adapter.RecipesAdapterDetail;
import com.hpr.hus.udacity_baking_app.bakingWidgets.BakingIntentService;
import com.hpr.hus.udacity_baking_app.graphic.RecipeDetailActivity;
import com.hpr.hus.udacity_baking_app.json2.ParsingIngredient;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 12/30/2017.
 */

public class DetailFragmentsRecipes  extends Fragment {

   public static  ArrayList<ParsingRecipe> recipeArrayList;
    String nameOfRecipe;
    static String RECIPE_SELECTED ="Select_Recipe";

    public DetailFragmentsRecipes() {

    }
    @Override
    public void onSaveInstanceState(Bundle bundle) {

        super.onSaveInstanceState(bundle);

        bundle.putParcelableArrayList(RECIPE_SELECTED, recipeArrayList);

        bundle.putString("Title", nameOfRecipe);
        Log.v("hhh22", "onSaveInstanceState: DetailFragmentsRecipes");


    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclView;


        recipeArrayList = new ArrayList<>();


        if(bundle != null) {
            Log.v("jjjDetailFragRecIpe", "bundle != null");
            recipeArrayList = bundle.getParcelableArrayList(RECIPE_SELECTED);
            Log.v("jjjRecipe_recipe", recipeArrayList.toString());


        }
        else {
            Log.v("jjjDetailFragRecIpe", "bundle == null");

            recipeArrayList = getArguments().getParcelableArrayList(RECIPE_SELECTED);
            Log.v("jjjRecipe_recipe", recipeArrayList.toString());

        }

        ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();
        if (DetailFragmentsRecipes.recipeArrayList!=null&& DetailFragmentsRecipes.recipeArrayList.size()!=0) {
            List<ParsingIngredient> ingredients = DetailFragmentsRecipes.recipeArrayList.get(0).getIngredients();
            nameOfRecipe = DetailFragmentsRecipes.recipeArrayList.get(0).getName();
            int counter = 0;

            for (ParsingIngredient i : ingredients) {
                counter++;

                recipeIngredientsForWidgets.add(i.getIngredient()+"\n"+
                        "Quantity: "+i.getQuantity().toString()+"\n"+
                        "Measure: "+i.getMeasure()+"\n");
            }
        }
        View View = layoutInflater.inflate(R.layout.fragment_recipe_details, viewGroup, false);

        recyclView=(RecyclerView)View.findViewById(R.id.recycler_view_recipe_detail);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(getContext());
        recyclView.setLayoutManager(mLayoutManager);

        RecipesAdapterDetail mRecipeDetailAdapter =new RecipesAdapterDetail((RecipeDetailActivity)getActivity());
        recyclView.setAdapter(mRecipeDetailAdapter);
        mRecipeDetailAdapter.setMasterRecipesData(recipeArrayList,getContext());


        BakingIntentService.startWidget(getContext(),recipeIngredientsForWidgets);

        return View;
    }




}