package com.example.newsapp.remote;


import com.example.newsapp.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NewsListServiceApi {

    @GET
    Call<News> getAllNews(@Url String urlString);

    @GET("movie/top_rated")
    Call<News> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int pageIndex
    );

}
