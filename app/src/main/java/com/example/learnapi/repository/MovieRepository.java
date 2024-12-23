package com.example.learnapi.repository;

import static com.example.learnapi.setupgeneral.dbHelper.PORT;

import com.example.learnapi.api.ApiService;
import com.example.learnapi.api.MovieService;
import com.example.learnapi.api.RetrofitClient;
import com.example.learnapi.api.TicketService;
import com.example.learnapi.api.UserService;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.PassWord;
import com.example.learnapi.module.ResData;

import retrofit2.Callback;

public class MovieRepository implements Repository {


    MovieService movieService;
    UserService userService;
    TicketService ticketService;
     String baseUrl = "http://"+ PORT + ":3002/";
     String baseUrl2 = "http://"+ PORT + ":3000/";
     String baseUrl3 = "http://"+ PORT + ":3004/";
    public MovieRepository()
    {
        this.movieService = RetrofitClient.getClient(baseUrl).create(MovieService.class);
        this.userService = RetrofitClient.getClient(baseUrl2).create(UserService.class);
        this.ticketService = RetrofitClient.getClient(baseUrl3).create(TicketService.class);
    }
    public void GetListMovie(Callback<ResData> callback)
    {
        movieService.getListMovie().enqueue(callback);
    }
    public void GetListTheater(Callback<ResData> callback)
    {
        movieService.getListTheaters().enqueue(callback);
    }
    public void checkLogin1(Customer customer, Callback<ResData> callback) {
        userService.login(customer).enqueue(callback);
    }
    public void ChangePass(int idUser, PassWord passWord, Callback<ResData> callback)
    {
        userService.ChangePass(idUser,passWord).enqueue(callback);
    }
    public void GetBillofUser(int idUser, Callback<ResData> callback)
    {
        ticketService.getBillofUser(idUser).enqueue(callback);
    }
    public void GetBillRefundofUser(int idUser, Callback<ResData> callback)
    {
        ticketService.getBillRefundofUser(idUser).enqueue(callback);
    }
    public void GetTypeMovie(int idMovie, Callback<ResData> callback)
    {
        movieService.getTypeMovie(idMovie).enqueue(callback);
    }
    public void GetNumberTicketMovie(int idMovie, Callback<ResData> callback)
    {
        movieService.getNumberTicket(idMovie).enqueue(callback);
    }


}
