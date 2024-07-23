package com.example.learnapi.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.learnapi.activity.DetailMovie_Activity;
import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.activity.Login_Activity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Account;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.ResData;
import com.example.learnapi.repository.UserRepository;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.gson.Gson;

import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccoutController extends baseController<Login_Activity, UserRepository>{

    // hàm khởi tao
    public AccoutController(Login_Activity activity)
    {
        this.view = activity;
        this.repository = new UserRepository();
    }

    public void checkUserLogin(Customer customer) {
        repository.checkLogin(customer, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful()) {
                    ResData resData = response.body();
                    if (resData.getCode() == 201) {
                        Gson gson = new Gson();
                        Customer customer1 = gson.fromJson(gson.toJson(resData.getData()), Customer.class);
                       view.saveAccount(customer1);
                        Home_Activity.LOGINED = 1;
                        view.IntentHome();

                    } else {
                        view.InforErr(resData.getMessage());
                    }
                } else {
                    if (response.code() == 400) {
                        view.InforErr("Invalid email or password"); // Thông báo lỗi khi email hoặc mật khẩu không hợp lệ
                    } else {
                        view.InforErr(response.message());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                // Xử lý khi request thất bại
                view.InforErr(throwable.getMessage());
            }
        });
    }




}
