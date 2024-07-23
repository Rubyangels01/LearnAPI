package com.example.learnapi.api;

import com.example.learnapi.module.Customer;
import com.example.learnapi.module.PassWord;
import com.example.learnapi.module.ResData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @POST("api/users/login")
    Call<ResData> login(@Body Customer customer);
    @PUT("api/users/user/{idUser}/update")
    Call<ResData> UpdateAccount(@Path("idUser") int idUser, @Body Customer customer);
    @GET("api/users/user/{idUser}")
    Call<ResData> GetUserByID(@Path("idUser") int idUser);
    @PUT("api/users/user/{idUser}/password")
    Call<ResData> ChangePass(@Path("idUser") int idUser, @Body PassWord passWord);
}
