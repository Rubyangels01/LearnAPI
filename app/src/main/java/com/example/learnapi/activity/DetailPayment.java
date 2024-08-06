package com.example.learnapi.activity;


import static com.example.learnapi.activity.ChooseChair.idRoom;
import static com.example.learnapi.activity.ChooseChair.listIDChair;
import static com.example.learnapi.activity.DetailMovie_Activity.movie;
import static com.example.learnapi.activity.TestActivity.idMovie;
import static com.example.learnapi.adapter.DateAdapter.selectedDate;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHeader;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.movie.HomePageController;
import com.example.learnapi.controller.ticket.DetailPaymentController;
import com.example.learnapi.databinding.ActivityDetailPaymentBinding;
import com.example.learnapi.module.Bill;
import com.example.learnapi.module.Bills;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.Ticket2;
import com.example.learnapi.module.Usersession;
import com.example.learnapi.setupgeneral.dbHelper;
import com.example.learnapi.zalopay.Api.CreateOrder;

import org.json.JSONObject;

import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class DetailPayment extends baseActivity<DetailPaymentController> {
    private CountDownTimer countDownTimer;

    private long timeLeftInMillis;
    private SharedPreferences sharedPreferences;
    Context context;
    ActivityDetailPaymentBinding binding;
    int numberchair = 0;
    int total = 0;
    Movie movie1;
    Bill bill1 = new Bill();
    Bills bill2 = new Bills();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new DetailPaymentController(this);
        context = this;
        sharedPreferences = getSharedPreferences("CountDownPrefs", MODE_PRIVATE);


        Intent intent = getIntent();
        if (intent != null) {
            numberchair = intent.getIntExtra("numberchair", 0);
            total = intent.getIntExtra("total", 0);
            binding.tvcountticket.setText(numberchair + " Vé");
            binding.tvnumberchair.setText(numberchair + " x seat");
            timeLeftInMillis = intent.getLongExtra("timeLeftInMillis", 0);
            movie1 = intent.getParcelableExtra("movie");

        }
        Glide.with(this)
                .load(movie1.getImage())
                .into(binding.imgmovie);
        binding.namemovie.setText(movie1.getNameMovie());
        binding.tvtheater.setText(selectedHeader);
        binding.tvtimedate.setText(dbHelper.convertDateToDetaildate(selectedDate) + " - " + selectedHour);
        binding.tvtotal.setText(dbHelper.ConvertPrice(String.valueOf(total)));
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);
        binding.btnbooking.setOnClickListener(v -> showAlertDialogYesNo("No exchanges or refunds")
        );
        startTimer();


    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                binding.tvCountdownTimer.setText("00:00");
                showAlertDialog("Thời gian giữ chỗ đã hết, vui lòng thử lại.");
                Intent intent = new Intent(DetailPayment.this, Home_Activity.class);
                startActivity(intent);
            }
        }.start();
    }

    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeft = String.format("%02d:%02d", minutes, seconds);

        binding.tvCountdownTimer.setText(timeLeft);
    }

    public void showAlertDialogYesNo(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(String.valueOf(total));
                    String code = data.getString("return_code");


                    if (code.equals("1")) {

                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(DetailPayment.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                Bill bill = new Bill();
                                bill.setPayment("Ví ZALOPay");
                                bill.setTotal(String.valueOf(total));
                                controller.SaveBillOfUser(HomePageController.IDUser,bill);
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                Bill bill = new Bill();
                                bill.setPayment("Ví ZALOPay");
                                bill.setTotal(String.valueOf(total));
                                bill1 = bill;

                               controller.SaveBillOfUser(HomePageController.IDUser,bill);
                               // showAlertDialog("Vé bị huỷ do thanh toán không thành công");

                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                showAlertDialog("Thanh toán không thành công");

                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
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

    @Override
    public void onBackPressed() {
        // Lưu thời gian còn lại khi quay lại từ DetailPayment
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("timeLeftInMillis", timeLeftInMillis);
        editor.apply();
        super.onBackPressed();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    public void SaveTicket(int idBill) {
        Ticket2 ticket2 = new Ticket2();
        ticket2.setIdBill(idBill);
        ticket2.setIdRoom(idRoom);
        ticket2.setIdMove(idMovie);
        String date = dbHelper.ConvertStringToDate(selectedDate) + " " + selectedHour;

        ticket2.setShowdate(date);
        ticket2.setListIDChair(listIDChair);

        controller.SaveTicketOfUser(ticket2);
    }

public Bills getBill(Bills bill)
{
    bill2 = bill;
    return  bill2;

}
    public void IntentDetailTicket() {

        Intent intent = new Intent(DetailPayment.this, DetailBill_Activity.class);
        intent.putExtra("bill",bill2);
        
        startActivity(intent);

    }
}
