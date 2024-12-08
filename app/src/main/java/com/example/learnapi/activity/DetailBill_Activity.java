package com.example.learnapi.activity;

import static com.example.learnapi.adapter.DateAdapter.selectedDate;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHeader;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
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
import com.example.learnapi.module.Voucher;
import com.example.learnapi.setupgeneral.dbHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailBill_Activity extends baseActivity<DetailBillController> {
    Bills bill = new Bills();
    Bill bill1 = new Bill();
    public static Bill billcancel = new Bill();
    String totalBill = "";
    public static int idBill = 0;
    ArrayList<Bill> bills = new ArrayList<>();
    public static ArrayList<Integer> idChairList = new ArrayList<>();
    public static int idRoom =0;
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
                idBill = bill.getIdBill();
                controller.GetTicketByBill(HomePageController.IDUser,bill.getIdBill());
            }
            else
            {
                bill1 = intent.getParcelableExtra("bill1");
                if (bill1 != null) {
                    idBill = bill1.getIdBill();


                    binding.tvAmount.setText(bill1.getTotal());
                    controller.GetTicketByBill(HomePageController.IDUser,bill1.getIdBill());
                }
            }

        }
        binding.backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intenhomet = new Intent(DetailBill_Activity.this, Home_Activity.class);
                Home_Activity.LOGINED = 1;
                startActivity(intenhomet);

            }
        });
        binding.lvordered.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill billselected = bills.get(position);
                Intent intent2 = new Intent(DetailBill_Activity.this,DetailTicket_Activity.class);
                intent2.putExtra("bill",billselected);
                startActivity(intent2);
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DetailBill_Activity.this,totalBill + "", Toast.LENGTH_SHORT).show();
                if(billcancel.getIdPromotion() > 0)
                {
                    showAlertDialog("The ticket does not meet the conditions for a refund");

                }
                else {
                    showAlertDialogYesNo("Are you sure you want to cancel your ticket?");
                }


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
                showBottomSheetDialog();

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

    private void showBottomSheetDialog() {
        // Tạo BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.popup_cancelticket);

        // Ánh xạ các view trong BottomSheet
        TextView totalPrice = bottomSheetDialog.findViewById(R.id.totalPrice);
        TextView refundfee = bottomSheetDialog.findViewById(R.id.refundfee);
        TextView refundamount = bottomSheetDialog.findViewById(R.id.refundamount);
        Button refundbtn = bottomSheetDialog.findViewById(R.id.refundbtn);
        totalPrice.setText(dbHelper.ConvertPrice(billcancel.getTotal()));
        refundfee.setText(dbHelper.ConvertPrice(String.valueOf(Integer.parseInt(billcancel.getTotal())  * 15 /100)));
        int refuntamount = Integer.parseInt(billcancel.getTotal()) - Integer.parseInt(billcancel.getTotal())  * 15 /100;
        refundamount.setText(dbHelper.ConvertPrice(String.valueOf(refuntamount)));

        // Thiết lập hành động cho nút thanh toán
        if (refundbtn != null) {
            refundbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showAlertDialog( idBill+ " " + getCurrentDateTimeFormatted());
                    controller.UpdateTimeRefund(idBill,getCurrentDateTimeFormatted());
                    UpDateChair();

                }
            });
        }


        bottomSheetDialog.show();
    }
    private void UpDateChair()
    {
        for(Integer idchair : idChairList)
        {
            controller.DeleteStatusChair(idchair,idRoom, billcancel.getShowdate());
        }
    }
    public void Intent_Cancel()
    {
        billcancel.setCancelDate(getCurrentDateTimeFormatted());
        Intent cancelticket_intent = new Intent(DetailBill_Activity.this, CancelTicket_Activity.class);
        startActivity(cancelticket_intent);
    }
    public  String getCurrentDateTimeFormatted() {
        // Định dạng ngày giờ theo kiểu ISO
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        return formatter.format(now);
    }



    public void GetPromotion(int idPromotion)
    {
        controller.GetPromotion(idPromotion);
    }

    private boolean isCurrentTimeLessThan3Hours(String targetTime) {
        try {
            // Định dạng thời gian đầu vào
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            }

            // Chuyển chuỗi thành LocalDateTime
            LocalDateTime targetDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                targetDateTime = LocalDateTime.parse(targetTime, formatter);
            }

            // Lấy thời gian hiện tại
            LocalDateTime currentDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
            }

            // Kiểm tra khoảng cách thời gian
            long hoursDifference = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                hoursDifference = ChronoUnit.HOURS.between(currentDateTime, targetDateTime);
            }
            return hoursDifference > 3; // Kiểm tra nếu nhỏ hơn 3 tiếng
        } catch (Exception e) {
            System.err.println("Lỗi khi xử lý thời gian: " + e.getMessage());
            return false;
        }
    }


    public void DislayInfor(Bill bill)
    {

        totalBill = bill.getTotal();
        billcancel = bill;

        binding.tvAmount.setText(dbHelper.ConvertPrice(bill.getTotal()));
        binding.tvMerchantName.setText(bill.getNameMovie());
        binding.tvOrderInfo.setText(bill.getNameTheater());
        binding.tvshowdate.setText(dbHelper.formatDate(bill.getShowdate()));
        String date = dbHelper.FormatDateToType(bill.getShowdate(),"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","dd/MM/yyyy HH:MM");
        if(isCurrentTimeLessThan3Hours(date))
        {
            binding.btnCancel.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.btnCancel.setVisibility(View.GONE);
        }
        if (bill.getIdPromotion() != 0) {
            GetVoucher(bill);
        }
        else
        {
            binding.tvvouchername.setText("Không áp dụng");
        }


    }
    public void DisplayBill(List<Bill> listbill)
    {

        for(Bill bill2 : listbill)
        {
            idChairList.add(bill2.getIdChair());
        }
        idRoom = listbill.get(0).getIdRoom();
        TicketAdapter adapter = new TicketAdapter(this,listbill);
        binding.lvordered.setAdapter(adapter);
    }
    public void GetVoucher(Bill bill)
    {
        controller.GetPromotion(bill.getIdPromotion());
    }
    public void DisplayVoucher(Voucher voucher)
    {
        binding.tvvouchername.setText(voucher.getNamePromotion());
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

    @Override
    public void onBackPressed() {
        Intent intenhomet = new Intent(DetailBill_Activity.this, Home_Activity.class);
        startActivity(intenhomet);
        super.onBackPressed();
    }

    public ArrayList<Bill> GetBillList(ArrayList<Bill> billList) {
        bills.addAll(billList);
        return bills;
    }
}