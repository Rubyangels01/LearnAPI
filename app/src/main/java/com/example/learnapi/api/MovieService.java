package com.example.learnapi.api;

import com.example.learnapi.module.Movie;
import com.example.learnapi.module.ResData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {
    @GET("movies/listmovie")
    Call<ResData> getListMovie();
    @GET("movies/theaters")
    Call<ResData> getListTheaters();
    @GET("movies/type/movie/{id}")
    Call<ResData> getTypeMovie(@Path("id") int idMovie);

}
