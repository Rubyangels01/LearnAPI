package com.example.learnapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.databinding.ActivityChooseTimeBinding;
import com.example.learnapi.databinding.ActivityDetailMovieBinding;
import com.example.learnapi.module.Movie;

public class ChooseTime_Activity extends AppCompatActivity {
    Movie movie = new Movie();
    ActivityChooseTimeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseTimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra("movie");
            if (movie != null) {
                binding.time.setText(movie.getTime() + " Phút");
                Glide.with(this)
                        .load(movie.getImage())
                        .into(binding.imgMovie);
                binding.nameMovie.setText(movie.getNameMovie());
            }
        }
        Set_Event();

    }

    private void Set_Event() {
        Set_Button();
    }

    private void Set_Button() {
        binding.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_choose_chair = new Intent(ChooseTime_Activity.this,ChooseChair.class);
                startActivity(intent_choose_chair);
            }
        });
    }
}