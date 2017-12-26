package com.hpr.hus.udacity_baking_app.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk640d on 12/21/2017.
 */

public class ParsingRecipe implements Parcelable {

    private Integer id;
    private String name;
    private List<ParsingIngredients> ingredients = null;
    private List<ParsingSteps> steps = null;
    private Integer servings;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParsingIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<ParsingIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<ParsingSteps> getSteps() {
        return steps;
    }

    public void setSteps(List<ParsingSteps> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    protected ParsingRecipe(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<>();
            in.readList(ingredients, ParsingIngredients.class.getClassLoader());
        } else {
            ingredients = null;
        }
        if (in.readByte() == 0x01) {
            steps = new ArrayList<>();
            in.readList(steps, ParsingSteps.class.getClassLoader());
        } else {
            steps = null;
        }
        servings = in.readByte() == 0x00 ? null : in.readInt();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredients);
        }
        if (steps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(steps);
        }
        if (servings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(servings);
        }
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
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