package com.example.learnapi.activity;

import static com.example.learnapi.activity.DetailMovie_Activity.GetMovie;
import static com.example.learnapi.setupgeneral.Setup_Text.RandomSeatId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.controller.base.baseactivity.baseActivity;
import com.example.learnapi.controller.ticket.ChoochairController;
import com.example.learnapi.databinding.ActivityChooseChairBinding;
import com.example.learnapi.databinding.ActivityDetailMovieBinding;
import com.example.learnapi.module.Movie;
import com.example.learnapi.setupgeneral.dbHelper;

public class ChooseChair extends baseActivity<ChoochairController> {
    ActivityChooseChairBinding binding;

    int numRows = 5; // Số hàng ghế bạn muốn hiển thị
    int numSeatsPerRow = 10; // Số ghế trên mỗi hàng
    Context context;
    String nameTheater = "";
    String showDate = "";
    String hourDate = "";
    int k = 0;
    int coutchair = 0;
    Movie movie;
    int Sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseChairBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new ChoochairController(this);

        context = this;
        binding.total.setText(String.valueOf(Sum));
        Intent intent = getIntent();
        if (intent != null) {
            nameTheater = intent.getStringExtra("nameTheater");
            showDate = intent.getStringExtra("dateShow");
            hourDate = intent.getStringExtra("hourShow");
            movie = intent.getParcelableExtra("movie");
            binding.nametheater.setText(nameTheater);

            binding.inforticket.setText(showDate + " - " + hourDate);
        }
        //controller.GetRoomBySchedule();
        for (int i = 0; i < numRows; i++) {

            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            rowLayout.setLayoutParams(rowLayoutParams);

            for (int j = 0; j < numSeatsPerRow; j++) {
                k++;
                ImageView seatImageView = new ImageView(this);
                seatImageView.setId(k); // Đặt id cho ghế

                seatImageView.setImageResource(R.drawable.chair_icon);

                LinearLayout.LayoutParams seatLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                seatLayoutParams.setMargins(4, 0, 4, 0); // Điều chỉnh khoảng cách giữa các ghế
                seatImageView.setLayoutParams(seatLayoutParams);
                rowLayout.addView(seatImageView);
            }

           binding.seatLayoutContainer.addView(rowLayout);
        }
        GetID();
        binding.btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChooseChair.this,DetailPayment.class);
                intent1.putExtra("numberchair",coutchair);
                intent1.putExtra("total",Sum);
                startActivity(intent1);
            }
        });

}

    public int SetPrice()
    {
        int price = 0;
        if(dbHelper.isFridayOrSaturday(showDate))
        {
            price =Integer.parseInt(movie.getPrice()) + (Integer.parseInt(movie.getPrice()) * 15 /100 );

        }
        else
        {
            price =Integer.parseInt(movie.getPrice());
        }
        return price;
    }

    private void GetID() {

        for (int i = 0; i < binding.seatLayoutContainer.getChildCount(); i++) {
            View row = binding.seatLayoutContainer.getChildAt(i);
            if (row instanceof LinearLayout) {
                LinearLayout rowLayout = (LinearLayout) row;
                for (int j = 0; j < rowLayout.getChildCount(); j++) {
                    View child = rowLayout.getChildAt(j);
                    if (child instanceof ImageView) {
                        final ImageView seat = (ImageView) child;
                        seat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!seat.isSelected()) {
                                    seat.setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.SRC_IN);
                                    seat.setSelected(true);
                                    Sum = Sum + SetPrice();
                                    coutchair = coutchair + 1;
                                    binding.total.setText(String.valueOf(Sum));
                                    binding.coutchair.setText(coutchair + " seat");
                                } else {
                                    seat.setColorFilter(null); // Xóa màu
                                    seat.setSelected(false);
                                    Sum = Sum - SetPrice();
                                    coutchair = coutchair - 1;
                                    binding.total.setText(String.valueOf(Sum));
                                    binding.coutchair.setText(coutchair + " seat");
                                }
                                if(coutchair >= 1)
                                {
                                    binding.btnpayment.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.green));
                                    binding.btnpayment.setEnabled(true);
                                }
                                else
                                {
                                    binding.btnpayment.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.gray));
                                    binding.btnpayment.setEnabled(false);
                                }
                                int seatId = seat.getId();

                                //Toast.makeText(context, "Seat ID: " + seatId, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        }
    }


}