package com.example.learnapi.controller.ticket;

import static com.example.learnapi.activity.ChooseChair.listIDChair;

import android.widget.Toast;

import com.example.learnapi.activity.DetailPayment;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.Bills;
import com.example.learnapi.module.Chairs;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Ticket2;
import com.example.learnapi.repository.TicketRepository;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPaymentController  extends baseController<DetailPayment, TicketRepository> {
public DetailPaymentController(DetailPayment activity)
        {
        this.view = activity;
        this.repository = new TicketRepository();
        }

    public void SaveBillOfUser(int idUser, Bill bill) {

        repository.SaveBill(idUser,bill,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 201) {

                        Gson gson = new Gson();
                        Bills bill = gson.fromJson(gson.toJson(resData.getData()), Bills.class);
                        view.getBill(bill);
                       view.SaveTicket(bill.getIdBill());

                    }
                } else {
                    view.showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                // Xử lý lỗi khi gọi API thất bại
                // (Thêm mã xử lý nếu cần)
            }
        });
    }

    public void SaveTicketOfUser(Ticket2 ticket2) {
        repository.SaveTicket(ticket2,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 201) {

                        view.IntentDetailTicket();
                    }
                } else {
                    view.showAlertDialog("Lỗi hệ thống!");
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                // Xử lý lỗi khi gọi API thất bại
                // (Thêm mã xử lý nếu cần)
            }
        });
    }

    private List<Integer> getListIDChair() {
    return listIDChair;
    }
}
