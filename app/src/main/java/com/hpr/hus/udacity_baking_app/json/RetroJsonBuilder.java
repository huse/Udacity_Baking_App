package com.hpr.hus.udacity_baking_app.json;

import android.util.Log;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by hk640d on 12/21/2017.
 */

public class RetroJsonBuilder {

    static GetJsonRecipeInterface jsonRecipes;
    private static final String ROOT_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public static GetJsonRecipeInterface retrieveJson() {
        Log.v("uuu33", "retrieveJson");


        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        jsonRecipes = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build().create(GetJsonRecipeInterface.class);
        Log.v("jjjjsonRecipes", " " +jsonRecipes);
        Log.w("bbbb",gson.toString());

        return jsonRecipes;
    }

   /* public interface GetJsonRecipeInterface {
        @GET("topher/2017/May/59121517_baking/baking.json")
        Call<ArrayList<ParsingRecipe>> getRecipe();
    }*/
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
