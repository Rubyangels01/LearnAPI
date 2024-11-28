package com.example.learnapi.controller.movie;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.Customer;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.PassWord;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.Theater;
import com.example.learnapi.module.Usersession;
import com.example.learnapi.repository.MovieRepository;
import com.example.learnapi.repository.TicketRepository;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageController extends baseController<Home_Activity, MovieRepository> {


    public static int IDUser = 0;
    public HomePageController(Home_Activity activity)
    {

        this.view = activity;
        this.repository = new MovieRepository();

    }
    public void GetListMovieNow()
    {

        repository.GetListMovie(new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {

                if(response.isSuccessful())
                {

                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        ArrayList<Movie> movieList = dbHelper.convertToObject(resData,Movie.class);

                        view.navigateToHomePage(movieList);



                    }
                    else
                    {
                        view.getErr(resData.getCode() + "");
                    }
                }
                else
                {
                    view.getErr(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {

            }
        });
    }


    public void GetListTheater()
    {

        repository.GetListTheater(new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if(response.isSuccessful())
                {
                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        ArrayList<Theater> theaterList = dbHelper.convertToObject(resData,Theater.class);

                        view.navigateToShowTimePage(theaterList);
                    }
                    else
                    {
                        view.getErr(resData.getCode() + "");
                    }
                }
                else
                {
                    view.getErr(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                view.getErr(throwable.getMessage());
            }
        });
    }
    public void GetBillofUser(int idUser)
    {

        repository.GetBillofUser(idUser,new Callback<ResData>() {

            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {

                if(response.isSuccessful())
                {

                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        ArrayList<Bill> billList = dbHelper.convertToObject(resData,Bill.class);

                        if(billList.isEmpty())
                        {
                            view.showAlertDialog("Bạn chưa có hoạt động nào!");
                        }
                        view.navigateToOrderingPage(billList);
                    }
                    else
                    {
                        view.getErr(resData.getCode() + "");
                    }
                }
                else
                {
                    view.getErr(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {

            }
        });
    }

    public void GetBillCancelofUser(int idUser)
    {

        repository.GetBillRefundofUser(idUser,new Callback<ResData>() {

            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {

                if(response.isSuccessful())
                {

                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        ArrayList<Bill> billList = dbHelper.convertToObject(resData,Bill.class);

                        if(billList.isEmpty())
                        {
                            view.showAlertDialog("Bạn chưa có hoạt động nào!");
                        }
                        view.navigateToOrderCancelPage(billList);
                    }
                    else
                    {
                        view.getErr(resData.getCode() + "");
                    }
                }
                else
                {
                    view.getErr(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {

            }
        });
    }

    public void checkUserLogin(Customer customer) {
        repository.checkLogin1(customer, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if (response.isSuccessful()) {
                    ResData resData = response.body();
                    if (resData.getCode() == 201) {
                        Gson gson = new Gson();
                        Customer customer1 = gson.fromJson(gson.toJson(resData.getData()), Customer.class);

                        IDUser = customer1.getIdCustomer();
                        Usersession.idUser = customer1.getIdCustomer();
                        view.saveAccount(customer1);
                        Home_Activity.LOGINED = 1;
                        view.updateLoginMenuItem();
                        view.Checklogin();
                        view.IntentHome();

                    } else {
                        view.getErr(resData.getMessage());
                    }
                } else {
                    if (response.code() == 400) {
                        view.getErr("Invalid email or password"); // Thông báo lỗi khi email hoặc mật khẩu không hợp lệ
                    } else {
                        view.getErr(response.message());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                // Xử lý khi request thất bại
                view.getErr(throwable.getMessage());
            }
        });
    }


    public void ChangePassWord(int IDUser, PassWord passWord) {
        repository.ChangePass(IDUser, passWord, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if(response.isSuccessful())
                {
                    ResData resData = response.body();
                    if(resData.getCode() == 201)
                    {
                        view.showAlertDialog(resData.getMessage());
                    }
                    else

                    {
                        view.showAlertDialog(resData.getMessage());
                    }
                }
                else {
                    view.showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable throwable) {
                view.showAlertDialog(throwable.getMessage());
            }
        });
    }
}
