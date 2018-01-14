package com.hpr.hus.udacity_baking_app.json;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hk640d on 12/30/2017.
 */

public class IngredientsToParse  implements Parcelable {

    private String measurement;
    private String ingredients;
    private Double quantities;







    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (quantities == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeDouble(quantities);
        }
        parcel.writeString(measurement);
        parcel.writeString(ingredients);
    }
    protected IngredientsToParse(Parcel parcel) {
        measurement = parcel.readString();
        ingredients = parcel.readString();
        quantities = parcel.readByte() == 0x00 ? null : parcel.readDouble();

    }

    public static final Parcelable.Creator<IngredientsToParse> CREATOR = new Parcelable.Creator<IngredientsToParse>() {
        @Override
        public IngredientsToParse createFromParcel(Parcel parcel) {
            return new IngredientsToParse(parcel);
        }

        @Override
        public IngredientsToParse[] newArray(int size) {
            return new IngredientsToParse[size];
        }
    };


}