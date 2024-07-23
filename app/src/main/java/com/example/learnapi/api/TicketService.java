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

    @GET("tickets/movie/{idMovie}/room")
    Call<ResData> getRoomBySchedule(
            @Path("idMovie") int idMovie, // @Path cần phải nằm trước @Query
            @Query("idTheater") int idTheater,
            @Query("showDate") String showDate
    );
}
