package com.example.learnapi.module;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chairs implements Parcelable {
    @SerializedName("IDChair")
    private int idChair;

    @SerializedName("NameChair")
    private String nameChair;
    @SerializedName("Status")
    private boolean status;


    protected Chairs(Parcel in) {
        idChair = in.readInt();
        nameChair = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            status  = in.readBoolean();
        }
    }

    public Chairs() {
    }

    public boolean getStatus() {
        return status;
    }

    public int getIdChair() {
        return idChair;
    }

    public void setIdChair(int idChair) {
        this.idChair = idChair;
    }

    public String getNameChair() {
        return nameChair;
    }

    public void setNameChair(String nameChair) {
        this.nameChair = nameChair;
    }

    public static final Creator<Chairs> CREATOR = new Creator<Chairs>() {
        @Override
        public Chairs createFromParcel(Parcel in) {
            return new Chairs(in);
        }

        @Override
        public Chairs[] newArray(int size) {
            return new Chairs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idChair);
        dest.writeString(nameChair);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(status);
        }
    }
}
