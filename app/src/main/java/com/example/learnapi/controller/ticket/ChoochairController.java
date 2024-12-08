package com.example.learnapi.controller.ticket;

import static com.example.learnapi.setupgeneral.dbHelper.parseJsonArray;

import com.example.learnapi.activity.ChooseChair;
import com.example.learnapi.activity.TestActivity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Chairs;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.DateShow;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Rooms;
import com.example.learnapi.module.Theater;
import com.example.learnapi.module.Voucher;
import com.example.learnapi.repository.TicketRepository;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoochairController extends baseController<ChooseChair, TicketRepository> {
    public ChoochairController(ChooseChair activity)
    {
        this.view = activity;
        this.repository = new TicketRepository();
    }
    public void GetRoomBySchedule(int idTheater,String showDate,int idMovie) {
        repository.GetRoomBySchedule(idTheater,idMovie,showDate, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {
                        ArrayList<Rooms> roomsList = dbHelper.convertToObject(resData,Rooms.class);

                        view.getRoom(roomsList.get(0));
                       view.DisplayRoom(roomsList.get(0));

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
    // lấy danh sachs voucher phù hợp với hoá đơn ngươi dùng
    // lấy danh sách x

    public void GetVoucherByCondition(int idCustomer,String totalBill) {
        repository.GetVoucherByCondition(idCustomer,totalBill, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {
                        ArrayList<Voucher> vouchersList = dbHelper.convertToObject(resData,Voucher.class);

                        view.GetVoucherList(vouchersList);

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
    public void UpdateChair(int idChair, int idRoom, String showDate) {
        repository.UpdateChair(idChair,idRoom,showDate, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 201) {
                        view.showAlertDialog("Vé của bạn sẽ được giữ chỗ trong 15 phút");
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
    public void GetAllChairs(int idRoom,String showDate) {
        repository.GetAllChairs(idRoom,showDate, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {
                        ArrayList<Chairs> roomsList = dbHelper.convertToObject(resData,Chairs.class);

                        view.DisplayChair(roomsList);

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
