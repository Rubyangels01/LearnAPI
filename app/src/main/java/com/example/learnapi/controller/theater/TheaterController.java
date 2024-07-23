package com.example.learnapi.controller.theater;

import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.controller.base.basecontroller.baseController;
import com.example.learnapi.repository.MovieRepository;

public class TheaterController extends baseController<Home_Activity, MovieRepository> {
    public TheaterController(Home_Activity activity)
    {
        this.view = activity;
        this.repository = new MovieRepository();
    }
}
