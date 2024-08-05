package com.example.learnapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.adapter.OrderAdapter;
import com.example.learnapi.adapter.TicketAdapter;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.movie.HomePageController;
import com.example.learnapi.controller.ticket.ChoochairController;
import com.example.learnapi.controller.ticket.DetailBillController;
import com.example.learnapi.databinding.ActivityDetailBillBinding;
import com.example.learnapi.databinding.ActivityDetailPaymentBinding;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.Bills;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.List;

public class DetailBill_Activity extends baseActivity<DetailBillController> {
    Bills bill = new Bills();
    ActivityDetailBillBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new DetailBillController(this);
        Intent intent = getIntent();
        if (intent != null) {
            bill = intent.getParcelableExtra("bill");
            if (bill != null) {
               binding.tvAmount.setText(bill.getTotal());
                controller.GetTicketByBill(HomePageController.IDUser,bill.getIdBill());
            }

        }


        binding.backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailBill_Activity.this, "Trở về home", Toast.LENGTH_SHORT).show();
                Intent intenhomet = new Intent(DetailBill_Activity.this, Home_Activity.class);
                startActivity(intenhomet);

            }
        });



    }

    public void DislayInfor(Bill bill)
    {
        binding.tvMerchantName.setText(bill.getNameMovie());
        binding.tvOrderInfo.setText(bill.getNameTheater());
        binding.tvshowdate.setText(dbHelper.formatDate(bill.getShowdate()));
    }
    public void DisplayBill(List<Bill> listbill)
    {

        TicketAdapter adapter = new TicketAdapter(this,listbill);
        binding.lvordered.setAdapter(adapter);
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