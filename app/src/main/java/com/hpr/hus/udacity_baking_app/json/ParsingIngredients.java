package com.hpr.hus.udacity_baking_app.json;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hk640d on 12/21/2017.
 */

public class ParsingIngredients  implements Parcelable {

    private Double quantity;
    private String measure;
    private String ingredient;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    protected ParsingIngredients(Parcel in) {
        quantity = in.readByte() == 0x00 ? null : in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (quantity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(quantity);
        }
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ParsingIngredients> CREATOR = new Parcelable.Creator<ParsingIngredients>() {
        @Override
        public ParsingIngredients createFromParcel(Parcel in) {
            return new ParsingIngredients(in);
        }

        @Override
        public ParsingIngredients[] newArray(int size) {
            return new ParsingIngredients[size];
        }
    };
}