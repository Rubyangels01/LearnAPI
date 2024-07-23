package com.example.learnapi.activity;

import static com.example.learnapi.adapter.DateAdapter.selectedDate;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHeader;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.adapter.DateAdapter;
import com.example.learnapi.adapter.ExpandableListAdapter;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.movie.HomePageController;
import com.example.learnapi.controller.ticket.ChooTimeController;
import com.example.learnapi.interfacecallback.OnTextClickListener;
import com.example.learnapi.module.DateShow;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.Theater;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestActivity extends baseActivity<ChooTimeController> implements OnTextClickListener {

    ExpandableListAdapter listAdapter;
    DateAdapter listdateAdapter;
    private RecyclerView recyclerView;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Button btn_booking;
    List<DateShow> listdate;
    Movie movie;
    ImageView imgview;
    TextView tvname;
    TextView tvtime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imgview = findViewById(R.id.imgmovie);
        tvname = findViewById(R.id.namemovie);
        tvtime = findViewById(R.id.tvtime);
        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra("movie");
            if (movie != null) {
                Glide.with(this)
                        .load(movie.getImage())
                        .into(imgview);
                tvname.setText(movie.getNameMovie());
                tvtime.setText(String.valueOf(movie.getTime()));
            }
        }
        btn_booking = findViewById(R.id.btnbooking);
        btn_booking.setEnabled(false);
        controller = new ChooTimeController(this);

        listdate = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild,this.btn_booking);
        SetEvent();
    }
    public void getDateShowofMovie(List<DateShow> listdateshow)
    {
        listdate.clear();
        listdate.addAll(listdateshow);

        listdateAdapter = new DateAdapter(this,listdate,this);
        recyclerView.setAdapter(listdateAdapter);
    }

    private void SetEvent() {
        DisplayDateShow();
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogYesNo("Vui lòng đặt vé trong vòng 15p");
            }
        });
    }

    public void DisplayDateShow()
    {
        controller.GetDateShowofMovie(movie.getIdMovie());
    }

    public void SetDataListTheater(List<Theater> listtheater) {
        listDataHeader.clear();
        listDataChild.clear();

        if (listtheater != null) {
            for (Theater theater : listtheater) {
                listDataHeader.add(theater.getNameTheater());
                listDataChild.put(theater.getNameTheater(), theater.getShowtimes());
            }
        }
        else
        {
            Toast.makeText(this, "Listheater null", Toast.LENGTH_SHORT).show();
        }

        listAdapter.notifyDataSetChanged();  // Gọi phương thức notifyDataSetChanged() nếu listAdapter đã được tạo trước đó
        expListView.setAdapter(listAdapter);
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

    public void showAlertDialogYesNo(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);

        // Nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(TestActivity.this, ChooseChair.class);
                intent.putExtra("nameTheater",selectedHeader);
                intent.putExtra("dateShow",selectedDate);
                intent.putExtra("hourShow",selectedHour);
                intent.putExtra("movie", movie);
                startActivity(intent);
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
    public void onTextClick(DateShow dateShow) {

       controller.GetTheaterShowofMovie(movie.getIdMovie(),dbHelper.ConvertStringToDate(dateShow.getDateShow()));
    }




}
