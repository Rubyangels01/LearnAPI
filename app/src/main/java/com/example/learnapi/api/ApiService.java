package com.example.learnapi.api;

import com.example.learnapi.module.Customer;
import com.example.learnapi.module.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
//    int port = 0;
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//    ApiService apiservice = new Retrofit.Builder()
//
//            .baseUrl("http://192.168.1.14:" + port + "/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);



    @GET("Customer")
    Call<List<Customer>> getData();

    @POST("Customer")
    Call<Customer> addCustomer(@Body Customer newCustomer);

}
