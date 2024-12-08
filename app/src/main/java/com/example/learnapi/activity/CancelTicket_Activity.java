package com.example.learnapi.activity;

import static com.example.learnapi.activity.DetailBill_Activity.billcancel;
import static com.example.learnapi.activity.DetailBill_Activity.idBill;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learnapi.R;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.movie.HomePageController;
import com.example.learnapi.controller.ticket.CancelTicketController;
import com.example.learnapi.controller.ticket.DetailBillController;
import com.example.learnapi.databinding.ActivityCancelTicketBinding;
import com.example.learnapi.databinding.ActivityDetailBillBinding;
import com.example.learnapi.module.Bill;
import com.example.learnapi.setupgeneral.dbHelper;

public class CancelTicket_Activity extends baseActivity<CancelTicketController> {
    ActivityCancelTicketBinding binding;
    Bill bill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCancelTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new CancelTicketController(this);
       // showAlertDialog(billcancel.getNameMovie() + " " + billcancel.getNameTheater() + " " + billcancel.getTotal()+ " " + dbHelper.formatDate(billcancel.getShowdate()) );
        Toast.makeText(this, idBill + "", Toast.LENGTH_SHORT).show();
            controller.GetTimeRefund(idBill);
            binding.namemovie.setText(billcancel.getNameMovie());
            binding.nametheater.setText(billcancel.getNameTheater());
            binding.tvshowdate.setText(dbHelper.formatDate(billcancel.getShowdate()));
           binding.amountticket.setText("Amount total: " +dbHelper.ConvertPrice(billcancel.getTotal()));
           int fee = Integer.parseInt(billcancel.getTotal()) * 15 / 100;
           binding.feecancel.setText("Fee Refund: " + dbHelper.ConvertPrice(String.valueOf(fee)));
           int totalcancel = (Integer.parseInt(billcancel.getTotal()) - (Integer.parseInt(billcancel.getTotal()) * 15 / 100));
           binding.amountcancel.setText("Refund amount: " + dbHelper.ConvertPrice(String.valueOf(totalcancel)));




        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("We look forward to your continued support in the future");
            }
        });

    }

    public  void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent home_itent = new Intent(CancelTicket_Activity.this, Home_Activity.class);

                startActivity(home_itent);

            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void DisplayTimeRefund(String timeRefund) {
        binding.timerefund.setText("Thời gian huỷ: " + dbHelper.formatDate(timeRefund) );
    }
}