package com.hpr.hus.udacity_baking_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hpr.hus.udacity_baking_app.adapter.RecipeAdapter;

public class MainActivity extends AppCompatActivity {
        private RecipeAdapter recipeAdapter;
        private RecyclerView mRecipeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecipeList = findViewById(R.id.rv_list_recipe);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecipeList.setLayoutManager(layoutManager);
        recipeAdapter = new RecipeAdapter();
        mRecipeList.setAdapter(recipeAdapter);

    }
}
