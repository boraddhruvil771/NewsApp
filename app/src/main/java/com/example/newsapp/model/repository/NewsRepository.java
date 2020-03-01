package com.example.newsapp.model.repository;

import android.app.Application;


import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.News;
import com.example.newsapp.remote.NewsListServiceApi;
import com.example.newsapp.remote.RetrofitClientNewsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    MutableLiveData<List<Article>> mutableLiveData;
    private Application application;


    public final static String BASE_URL = "https://newsapi.org/v2/";


    public NewsRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Article>> getMutableLiveData(int currentPage){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        NewsListServiceApi serviceApi = RetrofitClientNewsList.getClient(BASE_URL).create(NewsListServiceApi.class);
        String news_url_ = String.format("everything?q=bitcoin&pageSize=10&page="+currentPage+"&apiKey=3b2738eee23c47379aa1b1a15fc68aab");


        serviceApi.getAllNews(news_url_)
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()){
                            News newsRes = response.body();
                            List<Article> articleList = newsRes.getArticles();
                            mutableLiveData.setValue(articleList);
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                    }
                });
        return mutableLiveData;
    }

}
