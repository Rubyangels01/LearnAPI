package com.example.learnapi.controller.ticket;

import static com.example.learnapi.setupgeneral.dbHelper.parseJsonArray;

import android.widget.Toast;

import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.activity.TestActivity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.DateShow;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Theater;
import com.example.learnapi.repository.MovieRepository;
import com.example.learnapi.repository.TicketRepository;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooTimeController extends baseController<TestActivity, TicketRepository> {
    public ChooTimeController(TestActivity activity)
    {
        this.view = activity;
        this.repository = new TicketRepository();
    }
    public void GetDateShowofMovie(int idMovie) {
        repository.GetDateShowofMovie(idMovie, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {
                        JsonObject dataObject = new Gson().toJsonTree(resData.getData()).getAsJsonObject();
                        ArrayList<DateShow> dateList = parseJsonArray(dataObject, "dateShow", DateShow.class);
                        for (DateShow dateShow : dateList) {
                            String convertedDate = dbHelper.ConvertDateInSql(dateShow.getDateShow());
                            dateShow.setDateShow(convertedDate);  // Cập nhật lại ngày đã chuyển đổi
                        }
                        if (!dateList.isEmpty()) {
                            view.getDateShowofMovie(dateList);
                        }
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

    public void GetTheaterShowofMovie(int idMovie, String showDate) {

        repository.GetTheaterShowofMovie(idMovie, showDate, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {

                if (response.isSuccessful() && response.body() != null) {
                    ResData resData = response.body();
                    if (resData.getCode() == 200) {

                        ArrayList<Theater> theaterList = dbHelper.convertToObject(resData,Theater.class);
                        view.SetDataListTheater(theaterList);

                    } else {
                        view.showAlertDialog("Error: " + resData.getMessage());
                    }
                } else {
                    view.showAlertDialog("Failed to get data");
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                view.showAlertDialog("API call failed: " + throwable.getMessage());
            }
        });
    }




}
