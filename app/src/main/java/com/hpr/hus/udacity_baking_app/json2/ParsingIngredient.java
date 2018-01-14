package com.hpr.hus.udacity_baking_app.json2;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by hk640d on 1/2/2018.
 */

public class ParsingIngredient implements Parcelable {
    private Double quantity;
    private String measure;
    private String ingredient;






    public ParsingIngredient(Parcel parcel) {
        Log.v("uuuIngredient", "Ingredient   " + parcel);

        quantity = parcel.readByte() == 0x00 ? null : parcel.readDouble();
        measure = parcel.readString();
        ingredient = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (quantity == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeDouble(quantity);
        }
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }


    public static final Parcelable.Creator<ParsingIngredient> CREATOR = new Parcelable.Creator<ParsingIngredient>() {
        @Override
        public ParsingIngredient[] newArray(int size) {
            return new ParsingIngredient[size];
        }
        @Override
        public ParsingIngredient createFromParcel(Parcel in) {
            return new ParsingIngredient(in);
        }


    };
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

}