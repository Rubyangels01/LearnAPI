package com.example.learnapi.controller.ticket;

import com.example.learnapi.activity.CancelTicket_Activity;
import com.example.learnapi.activity.ChooseChair;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Rooms;
import com.example.learnapi.repository.TicketRepository;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelTicketController extends baseController<CancelTicket_Activity,TicketRepository> {
    public CancelTicketController(CancelTicket_Activity activity)
    {
        this.view = activity;
        this.repository = new TicketRepository();
    }
    public void GetTimeRefund(int idBill) {
        repository.GetTimeRefund(idBill, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {
                        String timeRefund = dbHelper.getRefundTime(resData);
                        view.DisplayTimeRefund(timeRefund);

                    }
                } else {
                    // Xử lý lỗi khi không thành công
                    // (Thêm mã xử lý nếu cần)
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                // Xử lý lỗi khi gọi API thất bại
                // (Thêm mã xử lý nếu cần)
            }
        });
    }

}
