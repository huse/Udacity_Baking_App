package com.hpr.hus.udacity_baking_app.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hpr.hus.udacity_baking_app.json2.ParsingRecipe;

import java.util.ArrayList;

/**
 * Created by hk640d on 12/30/2017.
 */

public class RecipeList {
    @SerializedName("recipe")
    @Expose
    private ArrayList<ParsingRecipe> parsingRecipe = new ArrayList<>();
    public void setRecipes(ArrayList<ParsingRecipe> parsingRecipe) {
        this.parsingRecipe = parsingRecipe;
    }
    public ArrayList<ParsingRecipe> getContacts() {
        return parsingRecipe;
    }


}
