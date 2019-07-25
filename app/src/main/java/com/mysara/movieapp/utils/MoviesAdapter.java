package com.mysara.movieapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mysara.movieapp.R;
import com.mysara.movieapp.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private ArrayList<Result> mData;
    private Context mContext;

    public MoviesAdapter(ArrayList<Result> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = mData.get(position);

        holder.tvTitle.setText(result.getTitle());
        holder.rbMovie.setRating((float) (result.getVoteAverage()/2));
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500"+result.getPosterPath()).into(holder.ivMovie);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivMovie;
        RatingBar rbMovie;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvMovie);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            rbMovie = itemView.findViewById(R.id.rbMovie);
        }
    }

}
