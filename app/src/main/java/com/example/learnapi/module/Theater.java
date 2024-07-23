package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Theater implements Parcelable {
    @SerializedName("IDTHEATER")
    private int idTheater;
    @SerializedName("Image")
    private String image;
    @SerializedName("NAMETHEATER")
    private String nameTheater;
    @SerializedName("ADDRESS")
    private String address;
    @SerializedName("showtimes")
    private List<String> showtimes;

    public Theater() {
        showtimes = new ArrayList<>();
    }

    protected Theater(Parcel in) {
        idTheater = in.readInt();
        image = in.readString();
        nameTheater = in.readString();
        address = in.readString();
        showtimes = in.createStringArrayList();
    }

    public static final Creator<Theater> CREATOR = new Creator<Theater>() {
        @Override
        public Theater createFromParcel(Parcel in) {
            return new Theater(in);
        }

        @Override
        public Theater[] newArray(int size) {
            return new Theater[size];
        }
    };

    public int getIdTheater() {
        return idTheater;
    }

    public void setIdTheater(int idTheater) {
        this.idTheater = idTheater;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameTheater() {
        return nameTheater;
    }

    public void setNameTheater(String nameTheater) {
        this.nameTheater = nameTheater;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<String> showtimes) {
        this.showtimes = showtimes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idTheater);
        dest.writeString(image);
        dest.writeString(nameTheater);
        dest.writeString(address);
        dest.writeStringList(showtimes);
    }
}
