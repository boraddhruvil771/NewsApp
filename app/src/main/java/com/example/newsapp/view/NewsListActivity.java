package com.example.newsapp.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsListAdapter;
import com.example.newsapp.listner.PaginationScrollListener;
import com.example.newsapp.model.Article;
import com.example.newsapp.viewmodel.NewListActivityViewModel;

import java.util.ArrayList;
import java.util.List;
public class NewsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;

    private boolean isFirst = true;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 10;
    private int currentPage = PAGE_START;
    ArrayList<Article> updated_articles_list;
    NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_list);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        updated_articles_list = new ArrayList<>();
        NewListActivityViewModel activityViewModel = ViewModelProviders.of(this).get(NewListActivityViewModel.class);
        activityViewModel.getAllArticle(currentPage).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                progressBar.setVisibility(View.GONE);
                if(articles!=null) {
                    updated_articles_list.addAll(articles);
                }
                if(isFirst) {
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter =  new NewsListAdapter(updated_articles_list, NewsListActivity.this);
                    recyclerView.setAdapter(adapter);
                    isFirst = false;
                }else {
                    adapter.notifyDataSetChanged();
                }

            }
        });

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                progressBar.setVisibility(View.VISIBLE);
                isLoading = true;
                currentPage += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                          loadNextPage(currentPage);
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    private void loadNextPage(int currentPage) {
        isLoading = false;
        NewListActivityViewModel activityViewModel = ViewModelProviders.of(this).get(NewListActivityViewModel.class);
        activityViewModel.getAllArticle(currentPage).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {

            }
        });
    }


}
