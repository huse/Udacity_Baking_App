package com.hpr.hus.udacity_baking_app.json;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hk640d on 12/21/2017.
 */

public class ParsingIngredients  implements Parcelable {

    private Double quantities;
    private String measures;
    private String ingredients;

    public Double getQuantities() {
        return quantities;
    }

    public void setQuantities(Double quantities) {
        this.quantities = quantities;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }


    protected ParsingIngredients(Parcel in) {
        quantities = in.readByte() == 0x00 ? null : in.readDouble();
        measures = in.readString();
        ingredients = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (quantities == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeDouble(quantities);
            dest.writeByte((byte) (0x01));

        }
        dest.writeString(ingredients);
        dest.writeString(measures);

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