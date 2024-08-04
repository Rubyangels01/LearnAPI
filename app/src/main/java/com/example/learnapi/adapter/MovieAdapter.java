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

import java.util.List;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
            holder = new ViewHolder();
            holder.movieImage = convertView.findViewById(R.id.image);
            holder.movieTitle = convertView.findViewById(R.id.namemovie);
            holder.btn_booking = convertView.findViewById(R.id.btn_booking);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Movie movie = movieList.get(position);

        Glide.with(context)
                .load(movie.getImage())
                .into(holder.movieImage);

        holder.movieTitle.setText(movie.getNameMovie());

        // Xử lý sự kiện khi click vào hình ảnh
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo intent để chuyển tới DetailMovie_Activity
                Intent intent = new Intent(context, DetailMovie_Activity.class);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            }
        });

        holder.btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LOGINED == 0) {
                    // Chuyển đến Fragment_Login trong Activity
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = new Frugment_Login();
                    fragmentTransaction.replace(R.id.content_frame, fragment); // R.id.fragment_container là ID của container chứa Fragment
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (LOGINED == 1) {
                    // Chuyển đến TestActivity
                    Intent intent = new Intent(context, TestActivity.class);
                    intent.putExtra("movie", movie);
                    context.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        public Button btn_booking;
        ImageView movieImage;
        TextView movieTitle;
    }

}
