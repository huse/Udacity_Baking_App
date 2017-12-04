package com.hpr.hus.udacity_baking_app;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.hpr.hus.udacity_baking_app.adapter.RecipeAdapter;
import com.hpr.hus.udacity_baking_app.fragments.MasterListRecipeFragment;
import com.hpr.hus.udacity_baking_app.fragments.RecipeFragments;

public class BackingFragmentActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener{
        private RecipeAdapter recipeAdapter;
        private RecyclerView mRecipeList;
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_baking);


           /* mRecipeList = findViewById(R.id.rv_list_recipe);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecipeList.setLayoutManager(layoutManager);
            recipeAdapter = new RecipeAdapter(this);
            mRecipeList.setAdapter(recipeAdapter);*/
  if(savedInstanceState == null) {
            // setting fragment
            RecipeFragments recipeTextFragment = new RecipeFragments();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.recipe_text_container, recipeTextFragment).commit();

            RecipeFragments recipeVideoFragment = new RecipeFragments();
            fragmentManager.beginTransaction().add(R.id.recipe_video_container, recipeVideoFragment).commit();
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.v("jjj", "clicked");
        if (mToast != null) {
            mToast.cancel();
        }


        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }



}
