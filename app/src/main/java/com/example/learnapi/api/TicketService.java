package com.example.learnapi.api;

import com.example.learnapi.module.Bill;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Ticket2;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TicketService {
    @GET("tickets/showdate/movie/{idMovie}")
    Call<ResData> getDateShowofMovie(@Path("idMovie") int idMovie);

    @GET("tickets/theaters/movie/{idMovie}")
    Call<ResData> getTheaterShowofMovie(@Path("idMovie") int idMovie, @Query("showDate") String showDate);

    @GET("tickets/movie/{idMovie}/room")
    Call<ResData> getRoomBySchedule(
            @Path("idMovie") int idMovie,
            @Query("idTheater") int idTheater,
            @Query("showDate") String showDate
    );
    @GET("tickets/customer/{idUser}")
    Call<ResData> getBillofUser(@Path("idUser") int idUser);

    @PUT("tickets/chair/{idChair}/room/{idRoom}")
    Call<ResData> UpdateChair(@Path("idChair") int idChair,@Path("idRoom") int idRoom,@Query("status") int status);
    @GET("tickets/chairs/room/{idRoom}")
    Call<ResData> getAllChair(@Path("idRoom") int idRoom);
    @POST("tickets/ticket/customer/{IDCustomer}")
    Call<ResData> SaveBillofUser(@Path("IDCustomer") int IDCustomer, @Body Bill bill);
    @POST("tickets/ticket/save")
    Call<ResData> SaveTicketofUser(@Body Ticket2 ticket2);

    @GET("tickets/customer/{IDCustomer}/bill/{idBill}")
    Call<ResData> GetTicketByBill(@Path("IDCustomer") int IDCustomer, @Path("idBill") int idBill);
}
