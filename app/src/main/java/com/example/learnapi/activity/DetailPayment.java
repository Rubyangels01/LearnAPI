package com.example.learnapi.activity;

import static com.example.learnapi.activity.DetailMovie_Activity.movie;
import static com.example.learnapi.adapter.DateAdapter.selectedDate;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHeader;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.databinding.ActivityChooseChairBinding;
import com.example.learnapi.databinding.ActivityDetailPaymentBinding;
import com.example.learnapi.payment.MoMoUtil;
import com.example.learnapi.setupgeneral.dbHelper;

public class DetailPayment extends AppCompatActivity {
    Context context;
    ActivityDetailPaymentBinding binding;
    int numberchair = 0;
    int total = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        Intent intent = getIntent();
        if (intent != null) {
            numberchair = intent.getIntExtra("numberchair",0);
            total = intent.getIntExtra("total",0);
            binding.tvcountticket.setText( numberchair + " Vé");
            binding.tvnumberchair.setText(numberchair + "  x seat");

        }
        Glide.with(this)
                .load(movie.getImage())
                .into(binding.imgmovie);
        binding.namemovie.setText(movie.getNameMovie());
        binding.tvtheater.setText(selectedHeader);
        binding.tvtimedate.setText(dbHelper.convertDateToDetaildate(selectedDate) + " - " + selectedHour);
        binding.tvtotal.setText(String.valueOf(total));

        binding.btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogYesNo("No exchanges or refunds");
            }
        });
    }
    public void showAlertDialogYesNo(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("vnpay://"); // Thay đổi thành URL sâu chính xác nếu cần
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "ko mở được", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Nút Cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}