package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Bills implements Parcelable {
    @SerializedName("IDBill")
    private int idBill;

    @SerializedName("Payment")
    private String payment;
    @SerializedName("Total")
    private String total;
    @SerializedName("IDCustomer")
    private int idCustomer;


    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Bills() {
    }

    public int getIdBill() {
        return idBill;
    }

    public String getPayment() {
        return payment;
    }

    public String getTotal() {
        return total;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public Bills(int idBill, String payment, String total, int idCustomer) {
        this.idBill = idBill;
        this.payment = payment;
        this.total = total;
        this.idCustomer = idCustomer;
    }

    protected Bills(Parcel in) {
        idBill = in.readInt();
        payment = in.readString();
        total = in.readString();
        idCustomer = in.readInt();
    }

    public static final Creator<Bills> CREATOR = new Creator<Bills>() {
        @Override
        public Bills createFromParcel(Parcel in) {
            return new Bills(in);
        }

        @Override
        public Bills[] newArray(int size) {
            return new Bills[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idBill);
        dest.writeString(payment);
        dest.writeString(total);
        dest.writeInt(idCustomer);
    }
}
