package com.example.learnapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnapi.R;
import com.example.learnapi.interfacecallback.OnTextClickListener;
import com.example.learnapi.module.DateShow;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {
    private Context mContext;
    private List<DateShow> mDateList;
    public static String selectedDate;
    private ViewHolder selectedViewHolder = null;
    private OnTextClickListener onTextClickListener;

    public DateAdapter(Context context, List<DateShow> dateList, OnTextClickListener onTextClickListener) {
        this.mContext = context;
        this.mDateList = dateList;
        this.onTextClickListener = onTextClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateShow date = mDateList.get(position);

        // Cập nhật màu sắc cho item dựa trên việc có được chọn không
        if (selectedViewHolder == holder) {


            holder.day.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green));
            holder.date.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green));
        } else {
            holder.day.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
            holder.date.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
        }

        holder.day.setText(dbHelper.Datetoday(date.getDateShow())); // Đổi thành cách xử lý ngày tháng nếu cần
        holder.date.setText(dbHelper.ConvertDateToDay(date.getDateShow())); // Đổi thành cách xử lý ngày tháng nếu cần

        holder.date.setOnClickListener(v -> selectItem(holder));
        holder.day.setOnClickListener(v -> {
            selectItem(holder);
            if (onTextClickListener != null) {
                selectedDate = date.getDateShow();
                onTextClickListener.onTextClick(date);
            }
        });
    }

    private void selectItem(ViewHolder holder) {
        // Deselect the previously selected item
        if (selectedViewHolder != null) {
            selectedViewHolder.day.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
            selectedViewHolder.date.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
        }

        // Select the new item
        selectedViewHolder = holder;
        holder.day.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green));
        holder.date.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green));

    }

    @Override
    public int getItemCount() {
        return mDateList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView date;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
        }
    }
}
