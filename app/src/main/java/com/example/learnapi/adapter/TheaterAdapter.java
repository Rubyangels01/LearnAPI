package com.example.learnapi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.activity.ChooseTime_Activity;
import com.example.learnapi.activity.DetailMovie_Activity;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.Theater;

import java.util.List;

public class TheaterAdapter extends BaseAdapter {
    private Context context;
    private List<Theater> theaterList;

    public TheaterAdapter(Context context, List<Theater> theaterList) {
        this.context = context;
        this.theaterList = theaterList;
    }

    @Override
    public int getCount() {
        return theaterList.size();
    }

    @Override
    public Object getItem(int position) {
        return theaterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_theater, parent, false);
            holder = new ViewHolder();
            holder.theaterImage = convertView.findViewById(R.id.imagetheater);
            holder.theaterTitle = convertView.findViewById(R.id.nametheater);
            holder.theateraddress = convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Theater theater = theaterList.get(position);

        Glide.with(context)
                .load(theater.getImage())
                .into(holder.theaterImage);

        holder.theaterTitle.setText(theater.getNameTheater());

        // Xử lý sự kiện khi click vào hình ảnh
        holder.theaterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Tạo intent để chuyển tới DetailMovie_Activity
//                Intent intent = new Intent(context, DetailMovie_Activity.class);
//                intent.putExtra("movie", movie);
//                context.startActivity(intent);
            }
        });
        holder.theateraddress.setText(theater.getAddress());
//        holder.btn_booking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(context, ChooseTime_Activity.class);
////                //intent.putExtra("movie", movie);
////                context.startActivity(intent);
//            }
//        });
        return convertView;
    }

    static class ViewHolder {

        ImageView theaterImage;
        TextView theaterTitle;
        TextView theateraddress;
    }

}
