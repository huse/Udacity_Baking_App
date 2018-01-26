package com.hpr.hus.udacity_baking_app.graphic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hpr.hus.udacity_baking_app.MainActivity;
import com.hpr.hus.udacity_baking_app.R;
import com.hpr.hus.udacity_baking_app.adapter.RecipesAdapterDetail;
import com.hpr.hus.udacity_baking_app.fragments.DetailFragmentsRecipes;
import com.hpr.hus.udacity_baking_app.fragments.DetailFragmentsSteps;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;
import com.hpr.hus.udacity_baking_app.json2.ParsingStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 1/3/2018.
 */

public class RecipeDetailActivity extends AppCompatActivity implements RecipesAdapterDetail.ListOnItemClickListener,DetailFragmentsSteps.ListItemClickListener{


    static String SELECTED_RECIPES="Select_Recipe";
    static String SELECTED_STEPS="Select_Step";
    static String SELECTED_INDEX="Select_Index";
    static String RECIPE_STACK_DETAIL="Recipe_Stack_detail";
    static String STEP_STACK_DETAIL="Step_Stack_detail";




    private ArrayList<ParsingRecipe> recipeArrList;
    public String nameOfRecipe;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.v("jjj", "RecipeDetailActivity 0"  + "onCreate");

        setContentView(R.layout.activity_detail_recipe);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (bundle == null) {

            Bundle selectedRecipeBundle = getIntent().getExtras();

            recipeArrList = new ArrayList<>();
            recipeArrList = selectedRecipeBundle.getParcelableArrayList(SELECTED_RECIPES);
            nameOfRecipe = recipeArrList.get(0).getName();

            final DetailFragmentsRecipes fragment = new DetailFragmentsRecipes();
            fragment.setArguments(selectedRecipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.FRAGMENT_CONTAINER_ONE, fragment)
                    .commit();
            Log.v("jjj", "RecipeDetailActivity 1"  + findViewById(R.id.recipe_detail_linear_layout).getTag());

            Log.v("jjj", "RecipeDetailActivity 2"  + findViewById(R.id.recipe_detail_linear_layout).getTag());

            if (findViewById(R.id.recipe_detail_linear_layout).getTag()!=null && findViewById(R.id.recipe_detail_linear_layout).getTag().equals("tablet")) {
                Log.v("jjj", "RecipeDetailActivity 3");

                final DetailFragmentsSteps fragment2 = new DetailFragmentsSteps();
                fragment2.setArguments(selectedRecipeBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.FRAGMENT_CONTAINER_TWO, fragment2).addToBackStack(STEP_STACK_DETAIL)
                        .commit();

            }



        } else {
            nameOfRecipe= bundle.getString("Title");
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
               return true;
    }


    @Override
    public void onBackPressed()
    {
        if (findViewById(R.id.recipe_detail_linear_layout).getTag()!=null && findViewById(R.id.recipe_detail_linear_layout).getTag().equals("tablet")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("Title",nameOfRecipe);
    }


    @Override
    public void onListItemClick(List<ParsingStep> parsingStepsList, int clickedItemIndex, String nameRecipe) {


        final DetailFragmentsSteps fragment = new DetailFragmentsSteps();
        FragmentManager fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setTitle(nameRecipe);

        Bundle bundlingSteps = new Bundle();
        bundlingSteps.putParcelableArrayList(SELECTED_STEPS,(ArrayList<ParsingStep>) parsingStepsList);
        bundlingSteps.putInt(SELECTED_INDEX,clickedItemIndex);
        bundlingSteps.putString("Title",nameRecipe);
        fragment.setArguments(bundlingSteps);

        if (findViewById(R.id.recipe_detail_linear_layout).getTag()!=null && findViewById(R.id.recipe_detail_linear_layout).getTag().equals("tablet")) {
            fragmentManager.beginTransaction()
                    .replace(R.id.FRAGMENT_CONTAINER_TWO, fragment).addToBackStack(STEP_STACK_DETAIL)
                    .commit();


        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.FRAGMENT_CONTAINER_ONE, fragment).addToBackStack(RECIPE_STACK_DETAIL)
                    .commit();
        }

    }





}
