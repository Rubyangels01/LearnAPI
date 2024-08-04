package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ticket2 implements Parcelable {
    @SerializedName("IDBill")
    private int idBill;
    @SerializedName("IDRoom")
    private int idRoom;
    @SerializedName("ShowDate")
    private String showdate;
    @SerializedName("IDMovie")
    private int idMove;
    @SerializedName("listIDChair")
    private List<Integer> listIDChair;

    public Ticket2() {
        listIDChair = new ArrayList<>();
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getShowdate() {
        return showdate;
    }

    public void setShowdate(String showdate) {
        this.showdate = showdate;
    }

    public int getIdMove() {
        return idMove;
    }

    public void setIdMove(int idMove) {
        this.idMove = idMove;
    }

    public List<Integer> getListIDChair() {
        return listIDChair;
    }

    public void setListIDChair(List<Integer> listIDChair) {
        this.listIDChair = listIDChair;
    }

    protected Ticket2(Parcel in) {
        idBill = in.readInt();
        idRoom = in.readInt();
        showdate = in.readString();
        idMove = in.readInt();
        // Đọc mảng số nguyên từ Parcel
        int size = in.readInt(); // Đọc kích thước danh sách
        listIDChair = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            listIDChair.add(in.readInt());
        }
    }

    public static final Creator<Ticket2> CREATOR = new Creator<Ticket2>() {
        @Override
        public Ticket2 createFromParcel(Parcel in) {
            return new Ticket2(in);
        }

        @Override
        public Ticket2[] newArray(int size) {
            return new Ticket2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idBill);
        dest.writeInt(idRoom);
        dest.writeString(showdate);
        dest.writeInt(idMove);
        // Ghi danh sách số nguyên vào Parcel
        if (listIDChair != null) {
            dest.writeInt(listIDChair.size());
            for (Integer chairId : listIDChair) {
                dest.writeInt(chairId);
            }
        } else {
            dest.writeInt(0);
        }
    }
}
