package com.example.learnapi.module;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DateShow {
    @SerializedName("dateShow")
    private String dateShow;

    public String getDateShow() {
        return dateShow;
    }

    public void setDateShow(String dateShow) {
        this.dateShow = dateShow;
    }

    public DateShow(String dateShow) {
        this.dateShow = dateShow;
    }
    public DateShow() {

    }
}
