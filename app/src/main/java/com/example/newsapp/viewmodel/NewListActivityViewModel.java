package com.example.newsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.repository.NewsRepository;

import java.util.List;

public class NewListActivityViewModel extends AndroidViewModel {

    NewsRepository newsRepository;

    public NewListActivityViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<Article>> getAllArticle(int currentPage){
        return newsRepository.getMutableLiveData(currentPage);
    }


}
