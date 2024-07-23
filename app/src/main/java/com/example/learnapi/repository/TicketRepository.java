package com.example.learnapi.repository;

import static com.example.learnapi.setupgeneral.dbHelper.PORT;

import com.example.learnapi.api.MovieService;
import com.example.learnapi.api.RetrofitClient;
import com.example.learnapi.api.TicketService;
import com.example.learnapi.module.ResData;

import java.util.Date;

import retrofit2.Callback;

public class TicketRepository implements Repository{
    TicketService ticketService;
    String baseUrl = "http://"+ PORT + ":3004/";
    public TicketRepository()
    {
        this.ticketService = RetrofitClient.getClient(baseUrl).create(TicketService.class);
    }
    public void GetDateShowofMovie(int idMovie, Callback<ResData> callback)
    {
        ticketService.getDateShowofMovie(idMovie).enqueue(callback);
    }
    public void GetTheaterShowofMovie(int idMovie, String showDate, Callback<ResData> callback)
    {
        ticketService.getTheaterShowofMovie(idMovie, showDate).enqueue(callback);
    }
    public void GetRoomBySchedule(int idTheater,int idMovie, String showDate, Callback<ResData> callback)
    {
        ticketService.getRoomBySchedule(idMovie, showDate,idMovie).enqueue(callback);
    }
}
