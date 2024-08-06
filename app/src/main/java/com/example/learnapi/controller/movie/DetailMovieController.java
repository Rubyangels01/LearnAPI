package com.example.learnapi.controller.movie;

import com.example.learnapi.activity.DetailMovie_Activity;
import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.ResData;
import com.example.learnapi.module.TypeMovie;
import com.example.learnapi.repository.MovieRepository;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieController extends baseController<DetailMovie_Activity, MovieRepository> {
    public DetailMovieController(DetailMovie_Activity activity)
    {

        this.view = activity;
        this.repository = new MovieRepository();

    }

    public void GetTypeMovie(int idMovie)
    {

        repository.GetTypeMovie(idMovie,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {

                if(response.isSuccessful())
                {

                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        ArrayList<TypeMovie> typemovieList = dbHelper.convertToObject(resData,TypeMovie.class);
                        view.DisplayMovieType(typemovieList);
                    }
                    else
                    {
                        view.showAlertDialog("Lỗi hệ thống!");
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

    public void GetNumberTicket(int idMovie)
    {

        repository.GetNumberTicketMovie(idMovie,new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {

                if(response.isSuccessful())
                {

                    ResData resData = response.body();
                    if(resData.getCode() == 200)
                    {
                        ArrayList<Movie> typemovieList = dbHelper.convertToObject(resData,Movie.class);
                        view.DisplayNumberTicket(typemovieList);
                    }
                    else
                    {
                        view.showAlertDialog("Lỗi hệ thống!");
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
