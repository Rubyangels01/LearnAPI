package com.example.learnapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.databinding.ActivityDetailPaymentBinding;
import com.example.learnapi.databinding.ActivityDetailTicketBinding;
import com.example.learnapi.module.Bill;
import com.example.learnapi.setupgeneral.dbHelper;

public class DetailTicket_Activity extends AppCompatActivity {
    ActivityDetailTicketBinding binding;
    Bill bill = new Bill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            bill = intent.getParcelableExtra("bill");
            if (bill != null) {

                displayMovieDetails(bill);
            }
        }
    }

    private void displayMovieDetails(Bill bill) {

        binding.namemovie.setText(bill.getNameMovie());
       binding.tvtimedate.setText(dbHelper.formatDate(bill.getNameTheater()));
        binding.tvnameroom.setText(bill.getNameRoom());
        binding.tvpayment.setText(dbHelper.ConvertPrice(bill.getPrice()));
        binding.tvnumberchair.setText(bill.getNamechair());


    }
}