package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bill implements Parcelable {
    @SerializedName("IDBill")
    private int idBill;
    @SerializedName("IDTicket")
    private int idTicket;
    @SerializedName("IDCustomer")
    private int idCustomer;
    @SerializedName("ShowDate")
    String showdate;
    @SerializedName("NAMEMOVIE")
    private String nameMovie;
    @SerializedName("NAMETHEATER")
    private String nameTheater;
    @SerializedName("NameRoom")
    private String nameRoom;
    @SerializedName("NameChair")
    private String namechair;
    @SerializedName("Total")
    private String total;
    @SerializedName("Payment")
    private String payment;
    @SerializedName("PriceMovie")
    private String price;
    @SerializedName("Time")
    private int time;
    @SerializedName("TimeRefund")
    private String cancelDate;
    @SerializedName("IDRoom")
    private int idRoom;
    @SerializedName("IDChair")
    private int idChair;
    @SerializedName("Status") //(1 laf đã đặt success, 0 là cancel)
    private int status;
    @SerializedName("IDPromotion") //(1 laf đã đặt success, 0 là cancel)
    private int idPromotion;


    public Bill() {

    }

    public Bill(int idBill, String showdate, String nameMovie, String nameTheater, String nameRoom, String total, String payment) {
        this.idBill = idBill;
        this.showdate = showdate;
        this.nameMovie = nameMovie;
        this.nameTheater = nameTheater;
        this.nameRoom = nameRoom;
        this.total = total;
        this.payment = payment;

    }

    public int getIdTicket() {
        return idTicket;
    }



    public String getNamechair() {
        return namechair;
    }

    public String getPrice() {
        return price;
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



    public String getShowdate() {
        return showdate;
    }



    public String getNameMovie() {
        return nameMovie;
    }



    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



    public void setPayment(String payment) {
        this.payment = payment;
    }



    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public int getIdChair() {
        return idChair;
    }

    protected Bill(Parcel in) {
        idBill = in.readInt();
        nameMovie = in.readString();
        showdate = in.readString();
        nameTheater = in.readString();
        idCustomer = in.readInt();
        nameRoom = in.readString();
        total = in.readString();
        payment = in.readString();
        namechair = in.readString();
        price = in.readString();
        time = in.readInt();
        idTicket = in.readInt();
        cancelDate = in.readString();
        status = in.readInt();
        idPromotion = in.readInt();
        idRoom = in.readInt();
        idChair = in.readInt();
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
        dest.writeString(payment);
        dest.writeString(showdate);
        dest.writeInt(idCustomer);
        dest.writeString(nameRoom);
        dest.writeString(nameTheater);
        dest.writeString(total);
        dest.writeString(namechair);
        dest.writeString(price);
        dest.writeInt(time);
        dest.writeInt(idTicket);
        dest.writeString(cancelDate);
        dest.writeInt(status);
        dest.writeInt(idPromotion);
        dest.writeInt(idRoom);
        dest.writeInt(idChair);
    }
}
