package com.hpr.hus.udacity_baking_app.json;

import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hk640d on 12/30/2017.
 */



    public interface GetJsonRecipeInterface {
    @GET("baking.json")
        Call<ArrayList<ParsingRecipe>> getRecipe();
    }
