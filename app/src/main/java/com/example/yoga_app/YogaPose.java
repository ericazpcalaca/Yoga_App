package com.example.yoga_app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import android.os.Parcel;
import android.os.Parcelable;

public class YogaPose implements Parcelable {
    private int poseId;
    private String name;
    private String description;
    private String benefits;
    private String image;

    public YogaPose(int poseId, String name, String description, String benefits, String image) {
        this.poseId = poseId;
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.image = image;
    }

    public YogaPose(String name, String description, String benefits, String image) {
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.image = image;
    }

    protected YogaPose(Parcel in) {
        name = in.readString();
        description = in.readString();
        benefits = in.readString();
        image = in.readString();
    }

    public static final Creator<YogaPose> CREATOR = new Creator<YogaPose>() {
        @Override
        public YogaPose createFromParcel(Parcel in) {
            return new YogaPose(in);
        }

        @Override
        public YogaPose[] newArray(int size) {
            return new YogaPose[size];
        }
    };

    public int getPoseId() {
        return poseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(benefits);
        dest.writeString(image);
    }
}

