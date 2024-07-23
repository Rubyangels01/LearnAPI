package com.example.learnapi.controller.account;

import com.example.learnapi.activity.ui.account.DetailAccount;
import com.example.learnapi.activity.ui.account.EditAccount;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.ResData;
import com.example.learnapi.repository.UserRepository;
import com.example.learnapi.setupgeneral.dbHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAccountController extends baseController<DetailAccount, UserRepository> {

    // hàm khởi tao
    public DetailAccountController(DetailAccount activity)
    {
        this.view = activity;
        this.repository = new UserRepository();
    }
    public void DisplayInfor(int idUser)
    {
        repository.GetCustomerByID(idUser, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if(response.isSuccessful())
                {
                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        Customer customer1 = dbHelper.convertObject(resData,Customer.class);
                        view.displayInformation(customer1);

                    }
                    else
                    {
                        view.showAlertDialog(resData.getMessage());
                    }
                }
                else
                {
                    view.showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {

            }
        });
    }
}
