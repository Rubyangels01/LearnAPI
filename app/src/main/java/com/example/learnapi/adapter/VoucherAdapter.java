package com.example.learnapi.adapter;

import static com.example.learnapi.activity.Home_Activity.LOGINED;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.activity.ChooseTime_Activity;
import com.example.learnapi.activity.DetailMovie_Activity;
import com.example.learnapi.activity.TestActivity;
import com.example.learnapi.activity.frugment.Frugment_Login;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.Voucher;

import java.util.ArrayList;
import java.util.List;

public class VoucherAdapter extends BaseAdapter {
    private Context context;
    private List<Voucher> vouchersList;


    public VoucherAdapter(Context context, ArrayList<Voucher> voucherList) {
        this.context = context;
        this.vouchersList = voucherList;
    }

    @Override
    public int getCount() {
        return vouchersList.size();
    }

    @Override
    public Object getItem(int position) {
        return vouchersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent, false);
            holder = new ViewHolder();
            holder.nameVoucher = convertView.findViewById(R.id.namevoucher);
            holder.enddate = convertView.findViewById(R.id.endDate);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Voucher voucher = vouchersList.get(position);



        holder.nameVoucher.setText(voucher.getNamePromotion());

        // Xử lý sự kiện khi click vào hình ảnh
//        holder.movieImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Tạo intent để chuyển tới DetailMovie_Activity
//                Intent intent = new Intent(context, DetailMovie_Activity.class);
//                intent.putExtra("movie", movie);
//                context.startActivity(intent);
//            }
//        });

//        holder.btn_booking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (LOGINED == 0) {
//                    // Chuyển đến Fragment_Login trong Activity
//                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    Fragment fragment = new Frugment_Login();
//                    fragmentTransaction.replace(R.id.content_frame, fragment); // R.id.fragment_container là ID của container chứa Fragment
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                } else if (LOGINED == 1) {
//                    // Chuyển đến TestActivity
//                    Intent intent = new Intent(context, TestActivity.class);
//                    intent.putExtra("movie", movie);
//                    context.startActivity(intent);
//                }
//            }
//        });

        return convertView;
    }

    static class ViewHolder {
        TextView nameVoucher;
        TextView enddate;

    }

}
