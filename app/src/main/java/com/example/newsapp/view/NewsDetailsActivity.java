package com.example.newsapp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;


public class NewsDetailsActivity extends AppCompatActivity {

    TextView content,title,author;
    ImageView image_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        content = findViewById(R.id.news_content);
        title = findViewById(R.id.news_title);
        author = findViewById(R.id.news_author);
        image_news = findViewById(R.id.img);


        if(getIntent()!=null){
/*            String content_str = getIntent().getStringExtra("content");
            Log.d("content", "onCreate: "+content_str);*/
            content.setText(getIntent().getStringExtra("content"));
            title.setText(getIntent().getStringExtra("title"));
            author.setText(getIntent().getStringExtra("author"));
            Picasso.get().load(getIntent().getStringExtra("urlToImage")).into(image_news);

        }
    }
}
