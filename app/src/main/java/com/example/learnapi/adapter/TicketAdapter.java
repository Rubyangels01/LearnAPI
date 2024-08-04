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

public class TicketAdapter extends BaseAdapter {
    private Context context;
    private List<Bill> orderList;

    // Constructor
    public TicketAdapter(Context context, List<Bill> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return (orderList != null) ? orderList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (orderList != null) ? orderList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
            holder = new ViewHolder();
            holder.idTicket = convertView.findViewById(R.id.idticket);
            holder.nameroom = convertView.findViewById(R.id.tvroom);
            holder.numberChair = convertView.findViewById(R.id.tvchair);
            holder.price = convertView.findViewById(R.id.tvprice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bill bill = orderList.get(position);

        if (bill != null) {
            holder.idTicket.setText("TKHHSSM" + bill.getIdTicket());
            holder.nameroom.setText(bill.getNameRoom());
            holder.numberChair.setText(bill.getNamechair());
            holder.price.setText(SetPrice(bill));
        }


        return convertView;
    }
    public int SetPrice(Bill bill) {
        int price = 0;
        if (dbHelper.isFridayOrSaturday(bill.getShowdate())) {
            price = Integer.parseInt(bill.getPrice()) + (Integer.parseInt(bill.getPrice()) * 15 / 100);
        } else {
            price = Integer.parseInt(bill.getPrice());
        }
        return price;
    }

    // ViewHolder class
    static class ViewHolder {
        TextView idTicket;
        TextView numberChair;
        TextView nameroom;
        TextView price;
    }
}
