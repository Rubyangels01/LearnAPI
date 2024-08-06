package com.example.learnapi.activity;

import static com.example.learnapi.activity.Home_Activity.LOGINED;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.activity.frugment.Frugment_Login;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.movie.DetailMovieController;
import com.example.learnapi.controller.movie.HomePageController;

import com.example.learnapi.databinding.ActivityDetailMovieBinding;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.TypeMovie;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.List;

public class DetailMovie_Activity extends baseActivity<DetailMovieController> {
    static Movie movie;
    private ActivityDetailMovieBinding binding;
    public static String typeMovie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new DetailMovieController(this);
        Intent intent = getIntent();
        if (intent != null) {
             movie = intent.getParcelableExtra("movie");
            if (movie != null) {
                displayMovieDetails(movie);
                controller.GetTypeMovie(movie.getIdMovie());
            }
        }

        setupBookingButton();
    }
    public static Movie GetMovie()
    {
        return movie;
    }

    private void setupBookingButton() {
        binding.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LOGINED == 0)
                {
                    Intent intent = new Intent(DetailMovie_Activity.this, Login_Activity.class);
                    startActivity(intent);
                }
                else if(LOGINED == 1)
                {
                    Intent intent = new Intent(DetailMovie_Activity.this, TestActivity.class);
                    intent.putExtra("movie", movie);

                    startActivity(intent);
                }
            }
        });
    }

    private void displayMovieDetails(Movie movie) {
        Glide.with(this)
                .load(movie.getImage())
                .into(binding.imgMovie);
        binding.nameMovie.setText(movie.getNameMovie());
        binding.description.setText(movie.getDescription());
        binding.releaseddate.setText(movie.getReleasedDate().toString());
        binding.language.setText(movie.getLanguage());
        binding.cast.setText(movie.getCast());
    }
    public void DisplayMovieType(List<TypeMovie> list)
    {
        typeMovie = dbHelper.ConvertTypeMovie(list);
        binding.typemovie.setText(dbHelper.ConvertTypeMovie(list));
    }
    public  void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
