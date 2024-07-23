

package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Rooms implements Parcelable {
        @SerializedName("IDRoom")
        private int idRoom;
        @SerializedName("NameRoom")
        private String nameRoom;
        @SerializedName("IDTheater")
        private int idTheater;


        public Rooms() {

        }

        protected Rooms(Parcel in) {
        idRoom = in.readInt();
        nameRoom = in.readString();
        idTheater = in.readInt();

        }

        public static final Creator<Rooms> CREATOR = new Creator<Rooms>() {
        @Override
        public Rooms createFromParcel(Parcel in) {
        return new Rooms(in);
        }

        @Override
        public Rooms[] newArray(int size) {
        return new Rooms[size];
        }
        };

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getIdTheater() {
        return idTheater;
        }

        public void setIdTheater(int idTheater) {
        this.idTheater = idTheater;
        }


        @Override
        public int describeContents() {
        return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idRoom);
        dest.writeString(nameRoom);
        dest.writeInt(idTheater);
        }
        }

