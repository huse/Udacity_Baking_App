package com.hpr.hus.udacity_baking_app.json2;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by hk640d on 1/2/2018.
 */

public class ParsingStep implements Parcelable {
    private String videoURL;
    private String thumbnailURL;

    private String shortDescription;
    private Integer id;
    private String description;





    protected ParsingStep(Parcel parcel) {
        Log.v("uuuStep", "Step   " + parcel);

        id = parcel.readByte() == 0x00 ? null : parcel.readInt();
        shortDescription = parcel.readString();
        description = parcel.readString();
        videoURL = parcel.readString();
        thumbnailURL = parcel.readString();
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
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }


    public static final Parcelable.Creator<ParsingStep> CREATOR = new Parcelable.Creator<ParsingStep>() {
        @Override
        public ParsingStep[] newArray(int Stepsize) {
            return new ParsingStep[Stepsize];
        }
        @Override
        public ParsingStep createFromParcel(Parcel parcel) {
            return new ParsingStep(parcel);
        }


    };


    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }


}