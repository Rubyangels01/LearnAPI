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
import com.example.learnapi.module.Movie;

import java.util.ArrayList;

public class Frugment_Home extends Fragment {
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Retrieve bundle data
        Bundle bundle = getArguments();
        if (bundle != null) {
            listView = view.findViewById(R.id.listmovie); // Corrected findViewById usage
            ArrayList<Movie> movieList = bundle.getParcelableArrayList("movieList");

            MovieAdapter movieAdapter = new MovieAdapter(requireContext(), movieList); // Use requireContext() to get the context of the fragment
            listView.setAdapter(movieAdapter);
        }

        return view;
    }
}
