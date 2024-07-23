package com.example.learnapi.activity.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.learnapi.adapter.MovieAdapter;
import com.example.learnapi.databinding.FragmentHomeBinding;
import com.example.learnapi.module.Movie;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<Movie> movies = bundle.getParcelableArrayList("movieList");
            if (movies != null) {
                MovieAdapter adapter = new MovieAdapter(getContext(), movies);
                binding.listmovie.setAdapter(adapter);
            }
        }


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
