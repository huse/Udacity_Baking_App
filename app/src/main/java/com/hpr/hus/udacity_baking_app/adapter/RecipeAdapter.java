package com.hpr.hus.udacity_baking_app.adapter;



/**
 * Created by hk640d on 11/30/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {


    public RecipeAdapter(){

    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder{


        public RecipeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
