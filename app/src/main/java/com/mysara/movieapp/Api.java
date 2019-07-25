package com.mysara.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("movie/popular")
    Call<ListResponse> movies(@Query("api_key") String apiKey, @Query("page") int page);
}
