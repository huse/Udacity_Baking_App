package com.hpr.hus.udacity_baking_app.adapter;



/**
 * Created by hk640d on 11/30/2017.
 */
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpr.hus.udacity_baking_app.R;

import java.util.ArrayList;

import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    final private ListItemOnClickListener mListOnClickListener;
    ArrayList<ParsingRecipe> arrListRecipes;
    Context mContext;
    public RecipeAdapter(ListItemOnClickListener listener){
        Log.v("uuu11", "RecipeAdapter object made");


        mListOnClickListener = listener;
    }
    @Override
    public int getItemCount() {
        if (arrListRecipes != null) {
            return arrListRecipes.size();
        }
        else return 0;
    }
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.v("uuu77", "onCreateViewHolder");

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.card_recipe;
        LayoutInflater inflater = LayoutInflater.from(context);


        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Log.v("uuu99", "onBindViewHolder");

if (arrListRecipes != null) {
    for (ParsingRecipe r: arrListRecipes){
        Timber.tag("uuuonBindViewHolder66").v("  " + r.toString());
    }
   // holder.listItemView.setText("hguyguoy");
    holder.listItemViews.setText(arrListRecipes.get(position).getName());
    //
    Timber.tag("uuu5555").v("listItemView   " + position + arrListRecipes.get(position).getName());
    Timber.tag("uuu5555").v("arrListRecipes   " + position + arrListRecipes.get(position));
    String imageUrl = arrListRecipes.get(position).getImages();

    if (imageUrl != null) {
        Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
        Picasso.with(mContext).load(builtUri).into(holder.imageRecyclerViews);
    }
}
    }




    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listItemViews;
        ImageView imageRecyclerViews;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            listItemViews =  (TextView) itemView.findViewById(R.id.tv_recipe_list_item);
            imageRecyclerViews = (ImageView) itemView.findViewById(R.id.recipeImageView);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {


            int clickedPosition = getAdapterPosition();
            Timber.tag("kkkRecipeAdapter").v("clicked  " + clickedPosition);
            mListOnClickListener.onListItemClick(arrListRecipes.get(clickedPosition));
        }
    }
    public interface ListItemOnClickListener {
        void onListItemClick(ParsingRecipe clickedItemIndex);
    }
    public void setRecipeData(ArrayList<ParsingRecipe> recipesIn, Context context) {
        arrListRecipes = recipesIn;
        for (ParsingRecipe r: arrListRecipes){
            Timber.tag("uuusetRecipeData66").v("  " + r);
        }
        mContext=context;
        notifyDataSetChanged();
        Timber.tag("uuu88").v("setRecipeData");

    }
}
