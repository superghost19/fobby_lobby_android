package com.app.fobbylobby.fobbylobby_android.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Agent implements Parcelable {
    private int id;
    private String name;
    private String address;
    private int rating;
    private double latitute;
    private double longitute;

    public Agent() {
    }

    public Agent(String name, String address, double latitude, double longitude, int rating) {
        super();
        this.name = name;
        this.address = address;
        this.latitute = latitude;
        this.longitute = longitude;
        this.rating = rating;
    }

    protected Agent(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        rating = in.readInt();
        latitute = in.readDouble();
        longitute = in.readDouble();
    }

    public static final Creator<Agent> CREATOR = new Creator<Agent>() {
        @Override
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }

        @Override
        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", latitute=" + latitute +
                ", longitute=" + longitute +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(rating);
        dest.writeDouble(latitute);
        dest.writeDouble(longitute);
    }
}
