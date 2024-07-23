package com.example.learnapi.api;

import com.example.learnapi.module.Movie;
import com.example.learnapi.module.ResData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("movies/listmovie")
    Call<ResData> getListMovie();
    @GET("movies/theaters")
    Call<ResData> getListTheaters();
}
