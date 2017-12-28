package com.hpr.hus.udacity_baking_app.json;

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

    public static GetJsonRecipeInterface RetrieveJson() {

        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        jsonRecipes = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build().create(GetJsonRecipeInterface.class);


        return jsonRecipes;
    }

    public interface GetJsonRecipeInterface {
        @GET("baking_recipes.json")
        Call<ArrayList<ParsingRecipe>> getRecipe();
    }
}
