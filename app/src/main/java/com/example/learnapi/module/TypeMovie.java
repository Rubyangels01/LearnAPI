package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TypeMovie implements Parcelable {
    @SerializedName("IDType")
    private int idType;
    @SerializedName("NameType")
    private String nameType;

    public TypeMovie() {
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    protected TypeMovie(Parcel in) {
        idType = in.readInt();
        nameType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idType);
        dest.writeString(nameType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TypeMovie> CREATOR = new Creator<TypeMovie>() {
        @Override
        public TypeMovie createFromParcel(Parcel in) {
            return new TypeMovie(in);
        }

        @Override
        public TypeMovie[] newArray(int size) {
            return new TypeMovie[size];
        }
    };
}
