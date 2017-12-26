package com.hpr.hus.udacity_baking_app.adapter;



/**
 * Created by hk640d on 11/30/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hpr.hus.udacity_baking_app.R;
import com.hpr.hus.udacity_baking_app.json.ParsingRecipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    final private ListItemClickListener mOnClickListener;
    ArrayList<ParsingRecipe> arrListRecipes;
    Context mContext;
    public RecipeAdapter(ListItemClickListener listener){
        mOnClickListener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listItemView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            listItemView =  itemView.findViewById(R.id.tv_recipe_item);
            itemView.setOnClickListener(this);

        }
        void bind(int listIndex) {
            listItemView.setText(String.valueOf(listIndex));
        }
        @Override
        public void onClick(View v) {
            Log.v("kkk", "clicked");

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(arrListRecipes.get(clickedPosition));
        }
    }
    public interface ListItemClickListener {
        void onListItemClick(ParsingRecipe clickedItemIndex);
    }
    public void setRecipeData(ArrayList<ParsingRecipe> recipesIn, Context context) {
        arrListRecipes = recipesIn;
        mContext=context;
        notifyDataSetChanged();
    }
}
