package com.hpr.hus.udacity_baking_app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.hpr.hus.udacity_baking_app.fragments.MasterListRecipeFragment;
import com.hpr.hus.udacity_baking_app.fragments.RecipeFragments;
import timber.log.Timber;
public class MainActivity extends AppCompatActivity implements  MasterListRecipeFragment.OnRecipeClickListener{
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());

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

                RecipeFragments recipeVideoFragment = new RecipeFragments();
                fragmentManager.beginTransaction().add(R.id.recipe_video_container, recipeVideoFragment).commit();

            }
        } else {
            Timber.plant();
            mTwoPane = false;
        }

    }

    @Override
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
    }
}
