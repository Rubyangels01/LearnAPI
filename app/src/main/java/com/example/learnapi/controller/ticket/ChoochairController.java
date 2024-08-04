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

    public void UpdateChair(int idChair, int idRoom, int status) {
        repository.UpdateChair(idChair,idRoom,status, new Callback<ResData>() {
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
    public void GetAllChairs(int idRoom) {
        repository.GetAllChairs(idRoom, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {
                        ArrayList<Chairs> roomsList = dbHelper.convertToObject(resData,Chairs.class);
                        view.showAlertDialog(roomsList.get(0).getNameChair());
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
