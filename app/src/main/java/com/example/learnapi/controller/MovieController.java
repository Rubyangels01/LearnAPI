package com.example.learnapi.controller;

import com.example.learnapi.activity.DetailMovie_Activity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.module.ResData;
import com.example.learnapi.repository.MovieRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieController extends baseController<DetailMovie_Activity, MovieRepository> {
    public MovieController(DetailMovie_Activity activity)
    {
        this.view = activity;
        this.repository = new MovieRepository();
    }

}
