package com.hpr.hus.udacity_baking_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hpr.hus.udacity_baking_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 12/4/2017.
 */

public class MasterListRecipeFragment extends Fragment {

    OnRecipeClickListener mCallback;

    public MasterListRecipeFragment(){

    }

    public interface OnRecipeClickListener {
        void onRecipeSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipeClickListener");
        }
    }
    private static final List<Integer> all = new ArrayList<Integer>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
    }};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.master_frament_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.master_fragment_recipe_grid_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        //MasterListRecipeAdapter mAdapter = new MasterListRecipeAdapter(getContext(), all);

        // Set the adapter on the GridView
       // gridView.setAdapter(mAdapter);

        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Trigger the callback method and pass in the position that was clicked
                mCallback.onRecipeSelected(position);
            }
        });
       /* final TextView textViewtext = (TextView) rootView.findViewById( R.id.recipe_fragment_tv);
        RecyclerView recyclerView;
        recyclerView=(RecyclerView)  rootView.findViewById(R.id.recipe_recyclerview);
        textViewtext.setText("this is for test");
        final RecipeAdapter recipesAdapter =new RecipeAdapter((MainActivity)getActivity());*/


        // Return the root view
        return rootView;
    }


}
