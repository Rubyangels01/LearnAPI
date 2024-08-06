package com.example.learnapi.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Movie implements Parcelable {
    @SerializedName("IDMOVIE")
    private int idMovie;
    @SerializedName("Image")
    String image;
    @SerializedName("NAMEMOVIE")
    private String nameMovie;
    @SerializedName("TIME")
    private int time;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("RELEASEDDATE")
    private Date releasedDate;
    @SerializedName("LANGUAGE")
    private String language;
    @SerializedName("CAST")
    private String cast;
    @SerializedName("PriceMovie")
    private String price;
    @SerializedName("Status")
    private int status;
    @SerializedName("NumberTicket")
    private int numberTicket;

    public Movie(int idMovie, String nameMovie, int time, String description, Date releasedDate, String language, String cast) {
        this.idMovie = idMovie;
        this.nameMovie = nameMovie;
        this.time = time;
        this.description = description;
        this.releasedDate = releasedDate;
        this.language = language;
        this.cast = cast;
    }
    public Movie() {
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    protected Movie(Parcel in) {
        idMovie = in.readInt();
        nameMovie = in.readString();
        time = in.readInt();
        description = in.readString();
        long tmpReleasedDate = in.readLong();
        releasedDate = tmpReleasedDate != -1 ? new Date(tmpReleasedDate) : null;
        language = in.readString();
        cast = in.readString();
        image = in.readString();
        price = in.readString();
        status = in.readInt();
        numberTicket = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idMovie);
        dest.writeString(nameMovie);
        dest.writeInt(time);
        dest.writeString(description);
        dest.writeLong(releasedDate != null ? releasedDate.getTime() : -1); // Chuyển đổi releasedDate thành long
        dest.writeString(language);
        dest.writeString(cast);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeInt(status);
        dest.writeInt(numberTicket);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
