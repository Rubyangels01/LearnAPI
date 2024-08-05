package com.example.learnapi.activity.frugment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnapi.R;
import com.example.learnapi.activity.DetailBill_Activity;
import com.example.learnapi.activity.DetailTicket_Activity;
import com.example.learnapi.activity.Home_Activity;
import com.example.learnapi.adapter.OrderAdapter;
import com.example.learnapi.module.Bill;

import java.util.ArrayList;

public class Frugment_Ordering extends Fragment {
    ListView listView;
    ArrayList<Bill> billList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ordering, container, false);
        listView = view.findViewById(R.id.lvordering);


        // Nhận dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            billList = bundle.getParcelableArrayList("billList");

            if (billList != null) {
                OrderAdapter orderAdapter = new OrderAdapter(requireContext(), billList);
                listView.setAdapter(orderAdapter);
            }
        }

        // Thiết lập OnItemClickListener cho ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill selectedBill = billList.get(position);


                    Intent intent = new Intent(getContext(), DetailBill_Activity.class);
                    intent.putExtra("bill", selectedBill.getIdBill());
                    startActivity(intent);

            }
        });

        return view;
    }
}
