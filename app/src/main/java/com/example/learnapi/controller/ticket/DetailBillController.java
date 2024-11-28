package com.example.learnapi.controller.ticket;

import com.example.learnapi.activity.DetailBill_Activity;
import com.example.learnapi.activity.TestActivity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.Bills;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Rooms;
import com.example.learnapi.module.Voucher;
import com.example.learnapi.repository.TicketRepository;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBillController extends baseController<DetailBill_Activity, TicketRepository> {
    public DetailBillController(DetailBill_Activity activity)
    {
        this.view = activity;
        this.repository = new TicketRepository();
    }
    public void GetTicketByBill(int idUser, int idBill) {

        repository.GetTicketByBill(idUser,idBill,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {

                        ArrayList<Bill> billList = dbHelper.convertToObject(resData,Bill.class);

                        view.DislayInfor(billList.get(0));
                        view.GetBillList(billList);
                        view.DisplayBill(billList);
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

    public void GetPromotion(int idPromotion) {

        repository.GetPromotion(idPromotion,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {

                        ArrayList<Voucher> voucher = dbHelper.convertToObject(resData,Voucher.class);

                        view.DisplayVoucher(voucher.get(0));
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

    public void UpdateTimeRefund(int idBill, String timeRefund) {

        repository.UpdateTimeRefund(idBill,timeRefund,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {

                        view.Intent_Cancel();
                    }
                } else {
                    //view.showAlertDialogYesNo(idBill + "- " + timeRefund);
                    view.showAlertDialog(response.message() + " - " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                // Xử lý lỗi khi gọi API thất bại
                // (Thêm mã xử lý nếu cần)
            }
        });
    }
    public void DeleteStatusChair(int idChair, int idRoom, String showDate) {
        repository.DeleteChair(idChair,idRoom,showDate, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 201) {
                        view.showAlertDialog("Vé của bạn đã bị huỷ");
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
