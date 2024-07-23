package com.example.learnapi.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.learnapi.activity.frugment.Frugment_Ordered;
import com.example.learnapi.activity.frugment.Frugment_Ordering;
import com.example.learnapi.module.Bill;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Bill> bills;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<Bill> bills) {
        super(fm, behavior);
        this.bills = bills;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new Frugment_Ordering();
                break;
            case 1:
                fragment = new Frugment_Ordered();
                break;
            default:
                fragment = new Frugment_Ordering();
                break;
        }

        // Truyền dữ liệu đến Fragment con
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("billList", bills);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 2; // Số lượng trang của ViewPager
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Ordering";
            case 1:
                return "Ordered";
            default:
                return null;
        }
    }
}
