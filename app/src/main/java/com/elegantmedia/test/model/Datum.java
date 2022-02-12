package com.elegantmedia.test.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Datum implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String address;
    private String postcode;
    private String phoneNumber;
    private String latitude;
    private String longitude;
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.address);
        dest.writeString(this.postcode);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeParcelable(this.image, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.title = source.readString();
        this.description = source.readString();
        this.address = source.readString();
        this.postcode = source.readString();
        this.phoneNumber = source.readString();
        this.latitude = source.readString();
        this.longitude = source.readString();
        this.image = source.readParcelable(Image.class.getClassLoader());
    }

    public Datum() {
    }

    protected Datum(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.address = in.readString();
        this.postcode = in.readString();
        this.phoneNumber = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel source) {
            return new Datum(source);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
}
