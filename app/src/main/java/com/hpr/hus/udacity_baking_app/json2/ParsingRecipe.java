package com.hpr.hus.udacity_baking_app.json2;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 1/2/2018.
 */

public class ParsingRecipe implements Parcelable {

    private String images;
    private List<ParsingStep> steps = null;
    private String name;
    private List<ParsingIngredient> ingredients = null;
    private Integer id;
    private Integer servingsInt;



    public List<ParsingIngredient> getIngredients() {
        return ingredients;
    }





    protected ParsingRecipe(Parcel parcel) {
        Log.v("uuuParsingRecipe", "Parcel parcel   " + parcel);

        id = parcel.readByte() == 0x00 ? null : parcel.readInt();
        name = parcel.readString();
        if (parcel.readByte() == 0x01) {
            ingredients = new ArrayList<>();
            parcel.readList(ingredients, ParsingIngredient.class.getClassLoader());
        } else {
            ingredients = null;
        }
        if (parcel.readByte() == 0x01) {
            steps = new ArrayList<>();
            parcel.readList(steps, ParsingStep.class.getClassLoader());
        } else {
            steps = null;
        }
        servingsInt = parcel.readByte() == 0x00 ? null : parcel.readInt();
        images = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.v("kkk7", "writeToParcel" +i);

        if (id == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        if (ingredients == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(ingredients);
        }
        if (steps == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(steps);
        }
        if (servingsInt == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeInt(servingsInt);
        }
        parcel.writeString(images);
    }


    public static final Parcelable.Creator<ParsingRecipe> CREATOR = new Parcelable.Creator<ParsingRecipe>() {
        @Override
        public ParsingRecipe createFromParcel(Parcel parcel) {
            return new ParsingRecipe(parcel);
        }

        @Override
        public ParsingRecipe[] newArray(int recipesize) {
            return new ParsingRecipe[recipesize];
        }
    };
    public void setIngredients(List<ParsingIngredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<ParsingStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ParsingStep> steps) {
        this.steps = steps;
    }
    public Integer getId() {
        Log.v("uuuParsingRecipe", "getId   " + id);

        return id;
    }

    public void setId(Integer id) {
        Log.v("uuuParsingRecipe", "setId   " + id);

        this.id = id;
    }
    public Integer getServingsInt() {
        return servingsInt;
    }

    public void setServingsInt(Integer servingsInt) {
        this.servingsInt = servingsInt;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }



    public String getName() {
        Log.v("uuuParsingRecipe", "getName   " + name);

        return name;
    }

    public void setName(String name) {
        Log.v("uuuParsingRecipe", "setName   " + name);

        this.name = name;
    }
}