package com.example.learnapi.repository;

import static com.example.learnapi.setupgeneral.dbHelper.PORT;

import com.example.learnapi.api.MovieService;
import com.example.learnapi.api.RetrofitClient;
import com.example.learnapi.api.TicketService;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Ticket2;

import java.util.Date;

import retrofit2.Callback;
import retrofit2.http.Body;

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
        ticketService.getRoomBySchedule(idMovie,idTheater, showDate).enqueue(callback);
    }

    public void UpdateChair(int idChair, int idRoom, String showDate, Callback<ResData> callback)
    {
        ticketService.UpdateChair(idChair,idRoom, showDate).enqueue(callback);
    }
    public void DeleteChair(int idChair, int idRoom, String showDate, Callback<ResData> callback)
    {
        ticketService.DeleteStatusChair(idChair,idRoom, showDate).enqueue(callback);
    }
    public void GetAllChairs(int idRoom,String showDate,Callback<ResData> callback)
    {
        ticketService.getAllChair(idRoom,showDate).enqueue(callback);
    }
    public void SaveBill(int idUser,Bill bill, Callback<ResData> callback)
    {
        ticketService.SaveBillofUser(idUser, bill).enqueue(callback);
    }
    public void SaveTicket(Ticket2 ticket2, Callback<ResData> callback)
    {
        ticketService.SaveTicketofUser(ticket2).enqueue(callback);
    }
    public void GetTicketByBill(int idUser, int idBill, Callback<ResData> callback)
    {
        ticketService.GetTicketByBill(idUser,idBill).enqueue(callback);
    }
    public void GetPromotion(int idPromotion, Callback<ResData> callback)
    {
        ticketService.GetPromotion(idPromotion).enqueue(callback);
    }
    public void GetVoucherByCondition(String totalBill, Callback<ResData> callback)
    {
        ticketService.GetVoucherByCondition(totalBill).enqueue(callback);
    }

    public void UpdateTimeRefund(int idBill,String timeRefund, Callback<ResData> callback)
    {
        ticketService.UpdateTimeRefund(idBill,timeRefund).enqueue(callback);
    }
    public void GetTimeRefund(int idBill, Callback<ResData> callback)
    {
        ticketService.GetTimeRefund(idBill).enqueue(callback);
    }

}
