package com.hpr.hus.udacity_baking_app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hpr.hus.udacity_baking_app.R;

import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;
import com.hpr.hus.udacity_baking_app.json2.ParsingStep;

import java.util.List;

/**
 * Created by hk640d on 12/30/2017.
 */

public class RecipesAdapterDetail extends RecyclerView.Adapter<RecipesAdapterDetail.RecyclViewHolder> {

        List<ParsingStep> listSteps;
private  String recipesName;

final private ListOnItemClickListener listItemOnClickListener;

public interface ListOnItemClickListener {
    void onListItemClick(List<ParsingStep> stepsOut, int clickedItemIndex, String recipeName);
}

    public RecipesAdapterDetail(ListOnItemClickListener listener) {
        listItemOnClickListener =listener;
    }


    public void setMasterRecipesData(List<ParsingRecipe> recipesIistParsed, Context context) {

        listSteps = recipesIistParsed.get(0).getSteps();
        recipesName =recipesIistParsed.get(0).getName();
        notifyDataSetChanged();
    }

    @Override
    public RecyclViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();

        int layoutId = R.layout.card_recipe_with_details;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId, viewGroup, false);

        RecyclViewHolder viewHolder = new RecyclViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclViewHolder holder, int position) {
    String text = (listSteps.get(position).getId()+". "+ listSteps.get(position).getShortDescription());
        holder.textRecyclerView.setText(text);

    }

    @Override
    public int getItemCount() {

        return listSteps !=null ? listSteps.size():0 ;
    }

class RecyclViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textRecyclerView;

    public RecyclViewHolder(View view) {
        super(view);

        textRecyclerView = (TextView) view.findViewById(R.id.short_length_description);

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        listItemOnClickListener.onListItemClick(listSteps,clickedPosition, recipesName);
    }

}
}
