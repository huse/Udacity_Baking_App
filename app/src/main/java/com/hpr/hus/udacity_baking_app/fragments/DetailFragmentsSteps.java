package com.hpr.hus.udacity_baking_app.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.hpr.hus.udacity_baking_app.R;

import com.hpr.hus.udacity_baking_app.graphic.RecipeDetailActivity;
import com.hpr.hus.udacity_baking_app.json2.ParsingIngredient;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;
import com.hpr.hus.udacity_baking_app.json2.ParsingStep;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.AbstractPreferences;

/**
 * Created by hk640d on 12/30/2017.
 */

public class DetailFragmentsSteps extends Fragment {

    private BandwidthMeter bandWidthMeter;
    private ArrayList<ParsingStep> stepsArrList = new ArrayList<>();
    private int index;
    String nammeForRecipe;
    Handler mainHandler;
    private ArrayList<ParsingRecipe> recipe = new ArrayList<>();

    static String STEPS_SELECTED ="Select_Step";
    static String INDEX_SELECTED ="Select_Index";
    static String RECIPE_SELECTED ="Select_Recipe";

    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer exoPlayer;
    private long playerPosition;

    boolean isPlayWhenReady;
    public DetailFragmentsSteps() {

    }

    private ListItemClickListener itemClickListener;

    public interface ListItemClickListener {
        void onListItemClick(List<ParsingStep> allSteps, int Index, String recipeName);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TextView textView;
        TextView textView2;
        if (exoPlayer != null) {
            Log.v("hhh35", "exoPlayer != null  " + playerPosition);
            exoPlayer.seekTo(playerPosition);


        }

        Log.v("hhh30", "playerPosition:  " + playerPosition);

       // playerPosition = savedInstanceState.getLong(SELECTED_POSITION, C.TIME_UNSET);
        mainHandler = new Handler();
        bandWidthMeter = new DefaultBandwidthMeter();

        itemClickListener =(RecipeDetailActivity)getActivity();



        if(bundle != null) {
            stepsArrList = bundle.getParcelableArrayList(STEPS_SELECTED);
            index = bundle.getInt(INDEX_SELECTED);
            nammeForRecipe = bundle.getString("Title");
            Log.v("jjjStepsF11", "  " + "bundle != null");
            if (exoPlayer != null) {
                playerPosition = exoPlayer.getCurrentPosition();
                Log.v("hhh38", "bundle != null: " + playerPosition);
            }

        }
        else {
            Log.v("jjjStepsF12", "  " + "bundle != null");

            stepsArrList =getArguments().getParcelableArrayList(STEPS_SELECTED);
            recipe =getArguments().getParcelableArrayList(RECIPE_SELECTED);

            if (stepsArrList!=null) {
                Log.v("jjjStepsF13", "  " + "bundle != null");
                Log.v("jjjStepsF13", "steps before  " + stepsArrList );
                stepsArrList =getArguments().getParcelableArrayList(STEPS_SELECTED);
                index =getArguments().getInt(INDEX_SELECTED);
                nammeForRecipe =getArguments().getString("Title");
                recipe =getArguments().getParcelableArrayList(RECIPE_SELECTED);
                Log.v("jjjStepsF13", "steps  " + stepsArrList );
                Log.v("jjjStepsF13", "recipe  " + recipe);


            }
            else {
                Log.v("jjjStepsF14", "  " + "bundle != null");

                recipe =getArguments().getParcelableArrayList(RECIPE_SELECTED);
                //casting List to ArrayList
                stepsArrList=(ArrayList<ParsingStep>)recipe.get(0).getSteps();
                index =0;
            }

        }



        View view = layoutInflater.inflate(R.layout.fragment_steps_details, viewGroup, false);
        textView = (TextView) view.findViewById(R.id.fragment_recipe_step_detail_text);
        textView.setText(stepsArrList.get(index).getDescription());
        textView.setVisibility(View.VISIBLE);


        exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.exo_player_view);
        exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

        String videoURL = stepsArrList.get(index).getVideoURL();

        if (view.findViewWithTag("port_step_detail")!=null) {
            nammeForRecipe =((RecipeDetailActivity) getActivity()).nameOfRecipe;
            ((RecipeDetailActivity) getActivity()).getSupportActionBar().setTitle(nammeForRecipe);
        }

        String imageUrl=stepsArrList.get(index).getThumbnailURL();
        if (imageUrl!="") {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            ImageView thumbImage = (ImageView) view.findViewById(R.id.thumb_nail_image);
            Picasso.with(getContext()).load(builtUri).into(thumbImage);
        }

        if (!videoURL.isEmpty()) {


            initializePlayer(Uri.parse(stepsArrList.get(index).getVideoURL()));

            if (view.findViewWithTag("sw600dp_step_detail")!=null) {
                getActivity().findViewById(R.id.FRAGMENT_CONTAINER_TWO).setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
                exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
            }
            else if (isInLandscapeMode(getContext())){
                textView.setVisibility(View.GONE);
            }
        }
        else {
            exoPlayer =null;
            exoPlayerView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.icon_none));
            exoPlayerView.setLayoutParams(new LinearLayout.LayoutParams(350, 350));
        }

        Button mPrevStep = (Button) view.findViewById(R.id.previous_step_button);
        Button mNextstep = (Button) view.findViewById(R.id.next_step_button);

        mPrevStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v("uuu300", "  " + "onClick prev");

                if (stepsArrList.get(index).getId() > 0) {
                    if (exoPlayer !=null){
                        exoPlayer.stop();
                    }
                    itemClickListener.onListItemClick(stepsArrList,stepsArrList.get(index).getId() - 1, nammeForRecipe);
                }
                else {
                  Log.v("lll","start of recipe");

                }
            }});

        mNextstep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v("uuu301", "  " + "onClick next");

                int lastIndex = stepsArrList.size()-1;
                if (stepsArrList.get(index).getId() < stepsArrList.get(lastIndex).getId()) {
                    if (exoPlayer !=null){
                        exoPlayer.stop();
                    }
                    itemClickListener.onListItemClick(stepsArrList,stepsArrList.get(index).getId() + 1, nammeForRecipe);
                }
                else {

                    Log.v("lll","end of recipe");

                }
            }});





            if (DetailFragmentsRecipes.recipeArrayList!=null&& DetailFragmentsRecipes.recipeArrayList.size()!=0) {
                List<ParsingIngredient> ingredients = DetailFragmentsRecipes.recipeArrayList.get(0).getIngredients();
                nammeForRecipe = DetailFragmentsRecipes.recipeArrayList.get(0).getName();

                textView2 = (TextView) view.findViewById(R.id.steps_detail_text2);


                int counter = 0;
                textView2.setText("Ingredient: \n");
                for (ParsingIngredient i : ingredients) {
                    counter++;
                    textView2.append(counter + ". " + i.getIngredient() + "\n");
                }

            }




        return view;
    }



    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(STEPS_SELECTED,stepsArrList);
        currentState.putInt(INDEX_SELECTED, index);
        currentState.putString("Title", nammeForRecipe);
        if(exoPlayer != null){
        playerPosition = exoPlayer.getCurrentPosition();
        Log.v("hhh33", "onSaveInstanceState: " +playerPosition);
        isPlayWhenReady = exoPlayer.getPlayWhenReady();}
        currentState.putBoolean("playstate", isPlayWhenReady);
        currentState.putLong("playstate", playerPosition);
    }

    public boolean isInLandscapeMode( Context context ) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer !=null) {
            exoPlayer.stop();
            playerPosition = exoPlayer.getCurrentPosition();
            Log.v("hhh36", "onPause: " + playerPosition);

           // exoPlayer.release();
        }
    }
    /*@Override
    public void onDetach() {
        super.onDetach();
        if (exoPlayer !=null) {
            exoPlayer.stop();
            exoPlayer.release();
        }
    }*/



  /*  @Override
    public void onStop() {
        super.onStop();
        Log.v("hhh36", "onStop: " + playerPosition);

        if (exoPlayer !=null) {
            exoPlayer.stop();
            exoPlayer.release();
            playerPosition = exoPlayer.getCurrentPosition();

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("hhh36", "onDestroyView: " + playerPosition);

        if (exoPlayer !=null) {
            exoPlayer.stop();
            playerPosition = exoPlayer.getCurrentPosition();



        }
    }*/
    @Override
    public void onResume() {
        Log.v("hhh34", "onResume:  " + playerPosition);
        super.onResume();
        if (exoPlayer != null) {
            Log.v("hhh35", "exoPlayer != null  " + playerPosition);
            if (playerPosition != C.TIME_UNSET) exoPlayer.seekTo(playerPosition);
            exoPlayer.setPlayWhenReady(isPlayWhenReady);

        }
    }
    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandWidthMeter);

            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(getContext(), "Baking Udacity Application");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            Log.v("hhh40", "initializePlayer  " + playerPosition);

            if (playerPosition != C.TIME_UNSET) exoPlayer.seekTo(playerPosition);
            Log.v("hhh41", "initializePlayer  " + playerPosition);

            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }
}
