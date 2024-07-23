package com.example.learnapi.controller.account;

import com.example.learnapi.activity.Login_Activity;
import com.example.learnapi.activity.ui.account.EditAccount;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.ResData;
import com.example.learnapi.repository.UserRepository;
import com.example.learnapi.setupgeneral.dbHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditController extends baseController<EditAccount, UserRepository> {

    // hàm khởi tao
    public EditController(EditAccount activity)
    {
        this.view = activity;
        this.repository = new UserRepository();
    }
    public void UpdateAccount(int idUser, Customer customer)
    {
        repository.UpdateAccount(idUser, customer, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if(response.isSuccessful())
                {
                    ResData resData = response.body();
                    if(resData.getCode() == 201)
                    {
                        view.showAlertDialog(resData.getMessage());
                        view.IntentDetailUpdate();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {

            }
        });
    }
}
