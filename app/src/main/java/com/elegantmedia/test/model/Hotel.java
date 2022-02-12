package com.elegantmedia.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Hotel extends AppResponse implements Parcelable {

    private int status;
    private ArrayList<Datum> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeList(this.data);
    }

    public void readFromParcel(Parcel source) {
        this.status = source.readInt();
        this.data = new ArrayList<Datum>();
        source.readList(this.data, Datum.class.getClassLoader());
    }

    public Hotel() {
    }

    protected Hotel(Parcel in) {
        this.status = in.readInt();
        this.data = new ArrayList<Datum>();
        in.readList(this.data, Datum.class.getClassLoader());
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel source) {
            return new Hotel(source);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
}
