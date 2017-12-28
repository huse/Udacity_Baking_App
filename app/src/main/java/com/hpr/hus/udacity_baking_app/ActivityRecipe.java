package com.hpr.hus.udacity_baking_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hpr.hus.udacity_baking_app.adapter.RecipeAdapter;
import com.hpr.hus.udacity_baking_app.json.ParsingRecipe;
import com.hpr.hus.udacity_baking_app.json.RecipeIdlingResource;

import java.util.ArrayList;

/**
 * Created by hk640d on 12/21/2017.
 */

public class ActivityRecipe extends AppCompatActivity implements RecipeAdapter.ListItemClickListener{

    static String ALL_RECIPES="All_Recipes";
    static String SELECTED_RECIPES="Selected_Recipes";
    static String SELECTED_STEPS="Selected_Steps";
    static String SELECTED_INDEX="Selected_Index";

    @Nullable
    private RecipeIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link RecipeIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);

        Toolbar myToolbar =  (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Baking Time");

// Get the IdlingResource instance
        getIdlingResource();
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onListItemClick(ParsingRecipe clickedItemIndex) {
        Bundle selectedRecipeBundle = new Bundle();
        ArrayList<ParsingRecipe> selectedRecipe = new ArrayList<>();
        selectedRecipe.add(clickedItemIndex);
        selectedRecipeBundle.putParcelableArrayList(SELECTED_RECIPES,selectedRecipe);

        final Intent intent = new Intent(this, BackingFragmentActivity.class);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);
    }
}
