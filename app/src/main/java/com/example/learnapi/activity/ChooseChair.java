package com.example.learnapi.activity;

import static com.example.learnapi.activity.DetailMovie_Activity.GetMovie;
import static com.example.learnapi.adapter.ExpandableListAdapter.getTheaterIdByName;
import static com.example.learnapi.adapter.ExpandableListAdapter.selectedHour;
import static com.example.learnapi.setupgeneral.Setup_Text.RandomSeatId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.learnapi.R;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.ticket.ChoochairController;
import com.example.learnapi.databinding.ActivityChooseChairBinding;
import com.example.learnapi.module.Chairs;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.Rooms;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.ArrayList;
import java.util.List;

public class ChooseChair extends baseActivity<ChoochairController> {
    ActivityChooseChairBinding binding;
    public long timeLeftInMillis;
    Context context;
    String nameTheater = "";
    String showDate = "";
    String hourDate = "";
    int coutchair = 0;
    Movie movie;
    int Sum = 0;
    public static int idRoom;
    public static String nameSeat;
    public static int idSeat;

    private CountDownTimer countDownTimer;
    private static final long START_TIME_IN_MILLIS = 900000; // 15 phút
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static List<Integer> listIDChair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseChairBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listIDChair = new ArrayList<>();
        controller = new ChoochairController(this);

        context = this;
        binding.total.setText(String.valueOf(Sum));

        timeLeftInMillis = START_TIME_IN_MILLIS;
        startCountdownTimer(timeLeftInMillis);

        Intent intent = getIntent();
        if (intent != null) {
            nameTheater = intent.getStringExtra("nameTheater");
            showDate = intent.getStringExtra("dateShow");
            hourDate = intent.getStringExtra("hourShow");
            movie = intent.getParcelableExtra("movie");
            binding.nametheater.setText(nameTheater);
            binding.inforticket.setText(showDate + " - " + hourDate);
            GetRoom();
        }
        SetEvent();
    }

    public void SetEvent() {
        sharedPreferences = getSharedPreferences("CountDownPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        try {

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage() + "", Toast.LENGTH_SHORT).show();
        }

        binding.btnpayment.setOnClickListener(v -> {
            Intent intent1 = new Intent(ChooseChair.this, DetailPayment.class);
            intent1.putExtra("numberchair", coutchair);
            intent1.putExtra("total", Sum);
            intent1.putExtra("nameSeat", nameSeat);
            intent1.putExtra("idSeat", idSeat);
            intent1.putExtra("timeLeftInMillis", timeLeftInMillis);
            intent1.putExtra("movie", movie);
            for(int i : listIDChair)
            {
                controller.UpdateChair(i,idRoom,dbHelper.ConvertStringToDate(showDate) + " " + hourDate);
            }

            startActivity(intent1);
        });
    }

    public void DisplayChair(ArrayList<Chairs> listchair) {

        int totalChairs = listchair.size();

        int numRows = (int) Math.ceil(totalChairs / 10.0); // Số hàng ghế bạn muốn hiển thị
        int numSeatsPerRow = 10; // Số ghế mỗi hàng

        for (int i = 0; i < numRows; i++) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            rowLayout.setLayoutParams(rowLayoutParams);

            for (int j = 0; j < numSeatsPerRow; j++) {
                int position = i * numSeatsPerRow + j;
                if (position >= totalChairs) break; // Kiểm tra nếu vị trí vượt quá số lượng ghế

                Chairs chair = listchair.get(position);
                ImageView seatImageView = new ImageView(this);
                seatImageView.setId(chair.getIdChair()); // Đặt id cho ghế
                seatImageView.setTag(chair.getNameChair()); // Đặt tag cho ghế để lưu tên

                seatImageView.setImageResource(R.drawable.chair_icon);

                LinearLayout.LayoutParams seatLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                seatLayoutParams.setMargins(4, 0, 4, 0); // Điều chỉnh khoảng cách giữa các ghế
                seatImageView.setLayoutParams(seatLayoutParams);
                if(chair.getStatus() == 1)
                {
                    seatImageView.setEnabled(false);
                    seatImageView.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN);
                }
                seatImageView.setOnClickListener(v -> {
                    int chairId = v.getId();
                    String chairName = (String) v.getTag();
                    if (!seatImageView.isSelected()) {
                        seatImageView.setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.SRC_IN);
                        seatImageView.setSelected(true);

                        nameSeat = chairName;
                        idSeat = chairId;
                        Toast.makeText(context, chairName + "", Toast.LENGTH_SHORT).show();

                        listIDChair.add(idSeat);

                        Sum = Sum + SetPrice();
                        coutchair = coutchair + 1;
                        binding.total.setText(String.valueOf(Sum));
                        binding.coutchair.setText(coutchair + " seat");
                    } else {
                        seatImageView.setColorFilter(null); // Xóa màu
                        seatImageView.setSelected(false);
                        Sum = Sum - SetPrice();

                        listIDChair.remove((Integer) chairId);
                        coutchair = coutchair - 1;
                        binding.total.setText(String.valueOf(Sum));
                        binding.coutchair.setText(coutchair + " seat");
                    }
                    if (coutchair >= 1) {
                        binding.btnpayment.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.green));
                        binding.btnpayment.setEnabled(true);
                    } else {
                        binding.btnpayment.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.gray));
                        binding.btnpayment.setEnabled(false);
                    }
                });
                rowLayout.addView(seatImageView);
            }
            binding.seatLayoutContainer.addView(rowLayout);
        }
    }

    public void GetRoom() {
        String timeShow = dbHelper.ConvertStringToDate(showDate) + " " + hourDate;
        controller.GetRoomBySchedule(getTheaterIdByName(nameTheater), timeShow, movie.getIdMovie());
    }

public void setChair(int idseat, int idRoom, int status)
{

}

    public int SetPrice() {
        int price = 0;
        if (dbHelper.isFridayOrSaturday(showDate)) {
            price = Integer.parseInt(movie.getPrice()) + (Integer.parseInt(movie.getPrice()) * 15 / 100);
        } else {
            price = Integer.parseInt(movie.getPrice());
        }
        return price;
    }

    public void DisplayRoom(Rooms rooms) {
        binding.namescreen.setText("Room " + rooms.getNameRoom());
        controller.GetAllChairs(rooms.getIdRoom(),dbHelper.ConvertStringToDate(showDate) + " " + hourDate);
    }

    public static Rooms getRoom(Rooms rooms) {
        idRoom = rooms.getIdRoom();

        return rooms;
    }

    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void startCountdownTimer(long durationInMillis) {
        countDownTimer = new CountDownTimer(durationInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                String timeLeft = String.format("%02d:%02d", minutes, seconds);
                binding.tvCountdownTimer.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                binding.tvCountdownTimer.setText("00:00");
                showAlertDialog("Thời gian giữ chỗ đã hết, vui lòng thử lại.");
                Intent intent = new Intent(ChooseChair.this, Home_Activity.class);
                startActivity(intent);
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCountdownTimer(timeLeftInMillis);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("timeLeftInMillis", timeLeftInMillis);
            editor.apply();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for(int i : listIDChair)
        {
            controller.DeleteStatusChair(i,idRoom,dbHelper.ConvertStringToDate(showDate) + " " + hourDate);
        }
    }
}
