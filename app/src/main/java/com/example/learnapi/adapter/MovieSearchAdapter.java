package com.example.learnapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.learnapi.R;
import com.example.learnapi.module.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Movie> originalMovieList;
    private List<Movie> filteredMovieList;
    private MovieFilter movieFilter;

    public MovieSearchAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.originalMovieList = new ArrayList<>(movieList);
        this.filteredMovieList = new ArrayList<>(movieList);
    }

    @Override
    public int getCount() {
        return filteredMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie_search, parent, false);
            holder = new ViewHolder();
            holder.movieImage = convertView.findViewById(R.id.img_movie);
            holder.movieTitle = convertView.findViewById(R.id.namemovie);
            holder.typemovie = convertView.findViewById(R.id.typemovie);
            holder.timemovie = convertView.findViewById(R.id.time);
            holder.releaseddate = convertView.findViewById(R.id.releaseddate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Movie movie = filteredMovieList.get(position);

        Glide.with(context)
                .load(movie.getImage())
                .into(holder.movieImage);

        holder.movieTitle.setText(movie.getNameMovie());

        holder.timemovie.setText(String.valueOf(movie.getTime()));
        holder.releaseddate.setText(movie.getReleasedDate().toString());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (movieFilter == null) {
            movieFilter = new MovieFilter();
        }
        return movieFilter;
    }

    private class MovieFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Movie> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(originalMovieList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Movie movie : originalMovieList) {
                    if (movie.getNameMovie().toLowerCase().contains(filterPattern)) {
                        suggestions.add(movie);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredMovieList.clear();
            filteredMovieList.addAll((List<Movie>) results.values);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        ImageView movieImage;
        TextView movieTitle;
        TextView typemovie;
        TextView timemovie;
        TextView releaseddate;
    }
}
