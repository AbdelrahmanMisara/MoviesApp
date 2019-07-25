package com.mysara.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mysara.movieapp.utils.Constants;
import com.mysara.movieapp.utils.MoviesAdapter;
import com.mysara.movieapp.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class HomeActivity extends AppCompatActivity {
    Api api;
    ArrayList<Result> list = new ArrayList<>();
    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter =  new MoviesAdapter(list);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(moviesAdapter);
       api = RetrofitClient.getInstance().create(Api.class);

       api.movies(Constants.API_KEY, 1).enqueue(new Callback<ListResponse>() {
           @Override
           public void onResponse(@NonNull Call<ListResponse> call, @NonNull Response<ListResponse> response) {
               if(response.isSuccessful()){
                   list.addAll(response.body().getResults());
                   moviesAdapter.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(@NonNull Call<ListResponse> call, @NonNull Throwable t) {
               Toast.makeText(HomeActivity.this, "No internet conniction", Toast.LENGTH_SHORT).show();
           }
       });

        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }


        };

    }
}
