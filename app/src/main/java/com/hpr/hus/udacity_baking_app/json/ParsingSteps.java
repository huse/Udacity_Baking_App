package com.hpr.hus.udacity_baking_app.json;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hk640d on 12/21/2017.
 */

public class ParsingSteps  implements Parcelable {

    private Integer idInt;
    private String shortDescriptions;
    private String descriptionStr;
    private String videoURLstr;
    private String thumbnailURLstr;

    public Integer getId() {
        return idInt;
    }

    public void setId(Integer id) {
        this.idInt = id;
    }

    /*public String getShortDescriptions() {
        return shortDescriptions;
    }

    public void setShortDescriptions(String shortDescriptions) {
        this.shortDescriptions = shortDescriptions;
    }

    public String getDescriptionStr() {
        return descriptionStr;
    }

    public void setDescriptionStr(String descriptionStr) {
        this.descriptionStr = descriptionStr;
    }

    public String getVideoURLstr() {
        return videoURLstr;
    }

    public void setVideoURLstr(String videoURLstr) {
        this.videoURLstr = videoURLstr;
    }

    public String getThumbnailURLstr() {
        return thumbnailURLstr;
    }

    public void setThumbnailURLstr(String thumbnailURLstr) {
        this.thumbnailURLstr = thumbnailURLstr;
    }*/


    protected ParsingSteps(Parcel in) {
        idInt = in.readByte() == 0x00 ? null : in.readInt();
        shortDescriptions = in.readString();
        descriptionStr = in.readString();
        videoURLstr = in.readString();
        thumbnailURLstr = in.readString();
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
        dest.writeString(shortDescriptions);
        dest.writeString(descriptionStr);
        dest.writeString(videoURLstr);
        dest.writeString(thumbnailURLstr);
    }


    public static final Parcelable.Creator<ParsingSteps> CREATOR = new Parcelable.Creator<ParsingSteps>() {
        @Override
        public ParsingSteps createFromParcel(Parcel in) {
            return new ParsingSteps(in);
        }

        @Override
        public ParsingSteps[] newArray(int size) {
            return new ParsingSteps[size];
        }
    };

}
