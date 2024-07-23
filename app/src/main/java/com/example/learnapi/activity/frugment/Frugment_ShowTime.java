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
import com.example.learnapi.adapter.MovieAdapter;
import com.example.learnapi.adapter.TheaterAdapter;
import com.example.learnapi.module.Movie;
import com.example.learnapi.module.Theater;

import java.util.ArrayList;

public class Frugment_ShowTime extends Fragment {
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_showtime,container,false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            listView = view.findViewById(R.id.listtheater); // Corrected findViewById usage
            ArrayList<Theater> theaterList = bundle.getParcelableArrayList("theaterList");

            TheaterAdapter theaterAdapter = new TheaterAdapter(requireContext(), theaterList); // Use requireContext() to get the context of the fragment
            listView.setAdapter(theaterAdapter);
        }
        return view;
    }
}
