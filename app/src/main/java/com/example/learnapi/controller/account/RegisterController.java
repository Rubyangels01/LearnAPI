package com.example.learnapi.controller.account;

import com.example.learnapi.activity.Register_Activity;
import com.example.learnapi.activity.ui.account.EditAccount;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.ResData;
import com.example.learnapi.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterController extends baseController<Register_Activity, UserRepository> {

    // hàm khởi tao
    public RegisterController(Register_Activity activity)
    {
        this.view = activity;
        this.repository = new UserRepository();
    }
    public void RegisterAccount(Customer customer)
    {
        repository.Register(customer, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if(response.isSuccessful())
                {
                    ResData resData = response.body();
                    if(resData.getCode() == 201)
                    {
                        view.showAlertDialog("Register successfully! Please choose 'Continue' to Login!");
                        view.TransToLogin();
                    }
                    else
                    {
                        view.showAlertDialog(resData.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {

            }
        });
    }
}
