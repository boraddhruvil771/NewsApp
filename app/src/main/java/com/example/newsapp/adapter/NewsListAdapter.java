package com.example.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.model.Article;
import com.example.newsapp.view.NewsDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    List<Article> articles;
    private Context context;

    public NewsListAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_news_view, parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        holder.title.setText(articles.get(position).getTitle());
        holder.date.setText(articles.get(position).getPublishedAt());

        String photoUrl = articles.get(position).getUrlToImage();
        Picasso.get().load(photoUrl).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title, date;
        ImageView imageView;

        public NewsViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Article article =  articles.get(position);

                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("author",article.getAuthor());
                    intent.putExtra("content",article.getContent());
                    intent.putExtra("urlToImage",article.getUrlToImage());
                    intent.putExtra("title",article.getTitle());
                    context.startActivity(intent);

                }
            });
        }
    }

}