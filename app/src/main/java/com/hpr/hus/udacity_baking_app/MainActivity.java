package com.hpr.hus.udacity_baking_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hpr.hus.udacity_baking_app.adapter.RecipeAdapter;
import com.hpr.hus.udacity_baking_app.graphic.RecipeDetailActivity;
import com.hpr.hus.udacity_baking_app.json.RecipeIdlingResource;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;

import java.util.ArrayList;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ListItemOnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIdlingResource();
        Timber.plant(new Timber.DebugTree());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
       // mRecipeList = (RecyclerView) findViewById(R.id.rv_list_recipe);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        /*mRecipeList.setLayoutManager(layoutManager);
        recipeAdapter = new RecipeAdapter(this);
        mRecipeList.setAdapter(recipeAdapter);*/

       // RecyclerView recyclerView;
       /* recyclerView=(RecyclerView)  rootView.findViewById(R.id.recipe_recyclerview);
        textViewtext.setText("this is for test");
        final RecipeAdapter recipesAdapter =new RecipeAdapter((MainActivity)getActivity());
        Log.v("uuu" , "recipesAdapter  " + recipesAdapter);
        Log.v("uuu" , "recyclerView  " + recyclerView);
        recyclerView.setAdapter(recipesAdapter);*/
/*
        if(findViewById(R.id.fragments_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Change the GridView to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.recipe_grid_view);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity
          //  Button nextButton = (Button) findViewById(R.id.next_button);
          //  nextButton.setVisibility(View.GONE);

            if(savedInstanceState == null) {
                // In two-pane mode, add initial BodyPartFragments to the screen

                RecipeFragments recipeTextFragment = new RecipeFragments();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.recipe_text_container, recipeTextFragment).commit();



            }
        } else {
            Timber.plant();
            mTwoPane = false;
        }*/

    }

  /*  @Override
    public void onRecipeSelected(int position) {
       // if (mTwoPane) {
            RecipeFragments recipeTextFragment = new RecipeFragments();
           // FragmentManager fragmentManager = getSupportFragmentManager();
         //   fragmentManager.beginTransaction().add(R.id.recipe_text_container, recipeTextFragment).commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_text_container, recipeTextFragment)
                    .commit();
     //   }else{
       //     final Intent intent = new Intent(this, BackingFragmentActivity.class);
     //       startActivity(intent);
     //   }

        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

  @Override
  public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
  }


    private RecipeIdlingResource mIdlingResource;
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }



    @Override
    public void onListItemClick(ParsingRecipe clickedItemIndex){
        Bundle selectedRecipeBundle = new Bundle();
        ArrayList<ParsingRecipe> selectedRecipe = new ArrayList<>();
        selectedRecipe.add(clickedItemIndex);
        selectedRecipeBundle.putParcelableArrayList("Select_Recipe",selectedRecipe);
                       Log.v("jjj", "onListItemClick");
        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);
    }



}
