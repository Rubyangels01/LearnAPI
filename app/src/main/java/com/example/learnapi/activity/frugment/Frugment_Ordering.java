package com.example.learnapi.activity.frugment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnapi.R;
import com.example.learnapi.adapter.OrderAdapter;
import com.example.learnapi.module.Bill;

import java.util.ArrayList;

public class Frugment_Ordering extends Fragment {
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ordering, container, false);
        listView = view.findViewById(R.id.lvordering);

        // Nhận dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<Bill> billList = bundle.getParcelableArrayList("billList");

            if (billList != null) {
                OrderAdapter orderAdapter = new OrderAdapter(requireContext(), billList);
                listView.setAdapter(orderAdapter);
            }
        }

        return view;
    }
}
