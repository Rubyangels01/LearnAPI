package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bill implements Parcelable {
    @SerializedName("IDBill")
    private int idBill;
    @SerializedName("ShowDate")
    String showdate;
    @SerializedName("NAMEMOVIE")
    private String nameMovie;
    @SerializedName("NAMETHEATER")
    private String nameTheater;
    @SerializedName("NameRoom")
    private String nameRoom;
    @SerializedName("Total")
    private String total;
    @SerializedName("Payment")
    private int payment;

    public Bill() {

    }

    public Bill(int idBill, String showdate, String nameMovie, String nameTheater, String nameRoom, String total, int payment) {
        this.idBill = idBill;
        this.showdate = showdate;
        this.nameMovie = nameMovie;
        this.nameTheater = nameTheater;
        this.nameRoom = nameRoom;
        this.total = total;
        this.payment = payment;
    }

    public String getNameTheater() {
        return nameTheater;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getShowdate() {
        return showdate;
    }

    public void setShowdate(String showdate) {
        this.showdate = showdate;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    protected Bill(Parcel in) {
        idBill = in.readInt();
        nameMovie = in.readString();
        showdate = in.readString();
        nameTheater = in.readString();

        nameRoom = in.readString();
        total = in.readString();
        payment = in.readInt();
    }

    public static final Creator<Bill> CREATOR = new Creator<Bill>() {
        @Override
        public Bill createFromParcel(Parcel in) {
            return new Bill(in);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idBill);
        dest.writeString(nameMovie);
        dest.writeInt(payment);
        dest.writeString(showdate);

        dest.writeString(nameRoom);
        dest.writeString(nameTheater);
        dest.writeString(total);

    }
}
