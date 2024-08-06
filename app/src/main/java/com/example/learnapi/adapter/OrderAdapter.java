package com.example.learnapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learnapi.R;
import com.example.learnapi.module.Bill;
import com.example.learnapi.setupgeneral.dbHelper;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Bill> billList;

    public OrderAdapter(Context context, List<Bill> billList) {
        this.context = context;
        this.billList = billList;
    }

    @Override
    public int getCount() {
        return billList.size();
    }

    @Override
    public Object getItem(int position) {
        return billList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
            holder = new ViewHolder();
            holder.nameMovie = convertView.findViewById(R.id.namemovie);
            holder.tvtheater = convertView.findViewById(R.id.tvtheater);
            holder.tvtimeshow = convertView.findViewById(R.id.tvtimedate);
            holder.tvprice = convertView.findViewById(R.id.tvprice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bill bill = billList.get(position);

        holder.nameMovie.setText(bill.getNameMovie());
        holder.tvtheater.setText(bill.getNameTheater());
        holder.tvtimeshow.setText(dbHelper.formatDate(bill.getShowdate()));
        holder.tvprice.setText(dbHelper.ConvertPrice(bill.getTotal())  + " VND");

        return convertView;
    }

    static class ViewHolder {
        TextView nameMovie;
        TextView tvtheater;
        TextView tvtimeshow;
        TextView tvprice;
    }
}
