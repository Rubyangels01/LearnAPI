package com.example.learnapi.repository;

import static com.example.learnapi.setupgeneral.dbHelper.PORT;

import android.widget.Toast;

import com.example.learnapi.api.MovieService;
import com.example.learnapi.api.RetrofitClient;
import com.example.learnapi.api.UserService;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.PassWord;
import com.example.learnapi.module.ResData;
import com.example.learnapi.repository.Irepository.IUserRepository;

import retrofit2.Callback;
import retrofit2.http.Body;

public class UserRepository implements Repository {
    UserService userService;


    public UserRepository() {
        String baseUrl = "http://"+ PORT + ":3000/";

        this.userService = RetrofitClient.getClient(baseUrl).create(UserService.class);
    }

    public void checkLogin(Customer customer, Callback<ResData> callback) {
        userService.login(customer).enqueue(callback);
    }
    public void UpdateAccount(int idUser,Customer customer,Callback<ResData> callback)
    {
        userService.UpdateAccount(idUser,customer).enqueue(callback);
    }
    public void GetCustomerByID(int idUser, Callback<ResData> callback)
    {
        userService.GetUserByID(idUser).enqueue(callback);
    }

}
