package com.example.learnapi.activity.frugment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnapi.R;
import com.example.learnapi.activity.DetailMovie_Activity;
import com.example.learnapi.adapter.MovieAdapter;
import com.example.learnapi.adapter.MovieSearchAdapter;
import com.example.learnapi.module.Movie;

import java.util.ArrayList;

public class Frugment_Home extends Fragment {
    ListView listView;
    ArrayList<Movie> movieList;
    int numberTicket;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Retrieve bundle data
        progressBar = view.findViewById(R.id.progressBar);
        Bundle bundle = getArguments();
        if (bundle != null) {
            listView = view.findViewById(R.id.listmovie);
            movieList = bundle.getParcelableArrayList("movieList");

            if (movieList != null) {
                MovieAdapter movieAdapter = new MovieAdapter(requireContext(), movieList);
                listView.setAdapter(movieAdapter);

                AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
                MovieSearchAdapter movieSearchAdapter = new MovieSearchAdapter(requireContext(), movieList);
                autoCompleteTextView.setAdapter(movieSearchAdapter);

                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ImageButton imagesearch = view.findViewById(R.id.btn_search);
                imagesearch.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = autoCompleteTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                autoCompleteTextView.setLayoutParams(params);

                // Set item click listener for AutoCompleteTextView
                autoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {

                    Movie selectedMovie = (Movie) parent.getItemAtPosition(position);
                    autoCompleteTextView.setText(selectedMovie.getNameMovie());
                    progressBar.setVisibility(View.VISIBLE);

                    // Sử dụng Handler để ẩn ProgressBar sau 2 giây (2000 milliseconds)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    }, 2000);
                    Intent intent = new Intent(getContext(), DetailMovie_Activity.class);
                    intent.putExtra("movie",selectedMovie);

                    startActivity(intent);
                });

                // Handle search button click
                imagesearch.setOnClickListener(v -> {
                    String query = autoCompleteTextView.getText().toString();
                    performSearch(query);
                });

                // Add TextWatcher to AutoCompleteTextView
                autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // No action needed
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().isEmpty()) {
                            imagesearch.setVisibility(View.GONE);
                            ViewGroup.LayoutParams params = autoCompleteTextView.getLayoutParams();
                            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            autoCompleteTextView.setLayoutParams(params);
                        } else {
                            imagesearch.setVisibility(View.VISIBLE);
                            ViewGroup.LayoutParams params = autoCompleteTextView.getLayoutParams();
                            params.width = dpToPx(300);
                            autoCompleteTextView.setLayoutParams(params);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // No action needed
                    }
                });
            }
        }

        return view;
    }

    private void performSearch(String query) {
        // controller.....
    }

    private String capitalizeWords(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalizedWords = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedWords.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalizedWords.toString().trim();
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
