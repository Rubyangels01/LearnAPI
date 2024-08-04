package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Ticket implements Parcelable {
    @SerializedName("IDTicket")
    private int idMovie;
    @SerializedName("Image")
    String image;
    @SerializedName("NAMEMOVIE")
    private String nameMovie;
    @SerializedName("TIME")
    private int time;


    protected Ticket(Parcel in) {
        idMovie = in.readInt();
        image = in.readString();
        nameMovie = in.readString();
        time = in.readInt();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idMovie);
        dest.writeString(image);
        dest.writeString(nameMovie);
        dest.writeInt(time);
    }
}
