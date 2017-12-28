package com.hpr.hus.udacity_baking_app.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 12/21/2017.
 */

public class ParsingRecipe implements Parcelable {

    private Integer idInt;
    private String nameRecipe;
    private List<ParsingIngredients> ingredientsList = null;
    private List<ParsingSteps> stepsList = null;
    private Integer servingsInt;
    private String images;

    public Integer getId() {
        return idInt;
    }

    public void setId(Integer id) {
        this.idInt = id;
    }

    public String getName() {
        return nameRecipe;
    }

    public void setName(String name) {
        this.nameRecipe = name;
    }

   /* public List<ParsingIngredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<ParsingIngredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<ParsingSteps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<ParsingSteps> stepsList) {
        this.stepsList = stepsList;
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
    }*/


    protected ParsingRecipe(Parcel in) {
        idInt = in.readByte() == 0x00 ? null : in.readInt();
        nameRecipe = in.readString();
        if (in.readByte() == 0x01) {
            ingredientsList = new ArrayList<>();
            in.readList(ingredientsList, ParsingIngredients.class.getClassLoader());
        } else {
            ingredientsList = null;
        }
        if (in.readByte() == 0x01) {
            stepsList = new ArrayList<>();
            in.readList(stepsList, ParsingSteps.class.getClassLoader());
        } else {
            stepsList = null;
        }
        servingsInt = in.readByte() == 0x00 ? null : in.readInt();
        images = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idInt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(idInt);
        }
        dest.writeString(nameRecipe);
        if (ingredientsList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredientsList);
        }
        if (stepsList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(stepsList);
        }
        if (servingsInt == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(servingsInt);
        }
        dest.writeString(images);
    }


    public static final Parcelable.Creator<ParsingRecipe> CREATOR = new Parcelable.Creator<ParsingRecipe>() {
        @Override
        public ParsingRecipe createFromParcel(Parcel in) {
            return new ParsingRecipe(in);
        }

        @Override
        public ParsingRecipe[] newArray(int size) {
            return new ParsingRecipe[size];
        }
    };
}