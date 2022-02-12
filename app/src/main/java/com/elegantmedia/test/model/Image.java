package com.elegantmedia.test.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private String small;
    private String medium;
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public String toString() {
        return "Image{" +
                "small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.medium);
        dest.writeString(this.large);
    }

    public void readFromParcel(Parcel source) {
        this.small = source.readString();
        this.medium = source.readString();
        this.large = source.readString();
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.small = in.readString();
        this.medium = in.readString();
        this.large = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
