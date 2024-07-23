package com.example.learnapi.api;

import com.example.learnapi.module.ResData;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TicketService {
    @GET("tickets/showdate/movie/{idMovie}")
    Call<ResData> getDateShowofMovie(@Path("idMovie") int idMovie);
    @GET("tickets/theaters/movie/{idMovie}")
    Call<ResData> getTheaterShowofMovie(@Path("idMovie") int idMovie, @Query("showDate") String showDate);
    @GET("tickets/theater/{idTheater}/room")
    Call<ResData> getRoomBySchedule(@Path("idTheater") int idTheater, @Query("showDate") String showDate,@Query("idMovie") int idMovie);
}
