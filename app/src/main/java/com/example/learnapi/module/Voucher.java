package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Voucher implements Parcelable {
    @SerializedName("IDPromotion")
    private int idPromotion;
    @SerializedName("NamePromotion")
    String namePromotion;
    @SerializedName("StartDate")
    private Date startDate;
    @SerializedName("EndDate")
    private Date endDate;
    @SerializedName("PercentSell")
    private int percentSell;
    @SerializedName("Desciption")
    private String description;

    @SerializedName("TotalBill")
    private String totalBill;


    public Voucher() {

    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getNamePromotion() {
        return namePromotion;
    }

    public void setNamePromotion(String namePromotion) {
        this.namePromotion = namePromotion;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPercentSell() {
        return percentSell;
    }

    public void setPercentSell(int percentSell) {
        this.percentSell = percentSell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    protected Voucher(Parcel in) {
        idPromotion = in.readInt();
        namePromotion = in.readString();
        percentSell = in.readInt();
        description = in.readString();
        long tmpstartDate = in.readLong();
        long tmpenddDate = in.readLong();
        startDate = tmpstartDate != -1 ? new Date(tmpstartDate) : null;
        startDate = tmpenddDate != -1 ? new Date(tmpenddDate) : null;
        totalBill = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPromotion);
        dest.writeString(namePromotion);
        dest.writeInt(percentSell);
        dest.writeString(description);
        dest.writeLong(startDate != null ? startDate.getTime() : -1); // Chuyển đổi releasedDate thành long
        dest.writeLong(endDate != null ? endDate.getTime() : -1);
        dest.writeString(totalBill);
    }

    public static final Creator<Voucher> CREATOR = new Creator<Voucher>() {
        @Override
        public Voucher createFromParcel(Parcel in) {
            return new Voucher(in);
        }

        @Override
        public Voucher[] newArray(int size) {
            return new Voucher[size];
        }
    };
}
