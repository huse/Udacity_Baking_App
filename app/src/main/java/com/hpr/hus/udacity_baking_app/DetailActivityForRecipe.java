package com.hpr.hus.udacity_baking_app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hpr.hus.udacity_baking_app.adapter.RecipesAdapterDetail;
import com.hpr.hus.udacity_baking_app.fragments.DetailFragmentsRecipes;
import com.hpr.hus.udacity_baking_app.fragments.DetailFragmentsSteps;
//import com.hpr.hus.udacity_baking_app.json.ParsingRecipe2;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;
import com.hpr.hus.udacity_baking_app.json2.ParsingStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 12/30/2017.
 */

public class DetailActivityForRecipe extends AppCompatActivity implements RecipesAdapterDetail.ListOnItemClickListener,DetailFragmentsSteps.ListItemClickListener{


    static String SELECTED_RECIPES="Select_Recipe";
    static String SELECTED_STEPS="Select_Step";
    static String SELECTED_INDEX="Select_Index";
    static String RECIPE_STACK_DETAIL="Recipe_Stack_detail";
    static String STEP_STACK_DETAIL="Step_Stack_detail";


    private ArrayList<ParsingRecipe> recipeArrList;
    public String recipeName;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.v("jjj", "DetailActivityForRecipe 1"  + "onCreate");

        setContentView(R.layout.activity_detail_recipe);

        if (bundle == null) {

            Bundle selectedRecipeBundle = getIntent().getExtras();

            recipeArrList = new ArrayList<>();
            recipeArrList = selectedRecipeBundle.getParcelableArrayList(SELECTED_RECIPES);
            recipeName = recipeArrList.get(0).getName();

            final DetailFragmentsRecipes fragment = new DetailFragmentsRecipes();
            fragment.setArguments(selectedRecipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.FRAGMENT_CONTAINER_ONE, fragment).addToBackStack(RECIPE_STACK_DETAIL)
                    .commit();
            Log.v("jjj", "DetailActivityForRecipe 1"  + findViewById(R.id.recipe_detail_linear_layout).getTag());

            Log.v("jjj", "DetailActivityForRecipe 2"  + findViewById(R.id.recipe_detail_linear_layout).getTag());
            if (findViewById(R.id.recipe_detail_linear_layout).getTag()!=null && findViewById(R.id.recipe_detail_linear_layout).getTag().equals("tablet")) {
                Log.v("jjj", "DetailActivityForRecipe 3");
                final DetailFragmentsSteps fragment2 = new DetailFragmentsSteps();
                fragment2.setArguments(selectedRecipeBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.FRAGMENT_CONTAINER_TWO, fragment2).addToBackStack(STEP_STACK_DETAIL)
                        .commit();

            }



        } else {
            recipeName= bundle.getString("Title");
        }



    }





    @Override
    public void onListItemClick(List<ParsingStep> parsingStepsList, int clickedItemIndex, String nameRecipe) {


        final DetailFragmentsSteps fragment = new DetailFragmentsSteps();
        FragmentManager fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setTitle(nameRecipe);

        Bundle stepBundle = new Bundle();
        stepBundle.putParcelableArrayList(SELECTED_STEPS,(ArrayList<ParsingStep>) parsingStepsList);
        stepBundle.putInt(SELECTED_INDEX,clickedItemIndex);
        stepBundle.putString("Title",nameRecipe);
        fragment.setArguments(stepBundle);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Title",recipeName);
    }



}
