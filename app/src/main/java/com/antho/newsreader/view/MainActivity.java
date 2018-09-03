package com.antho.newsreader.view;
/****/
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.antho.newsreader.model.NewsListJsonModel;
import com.antho.newsreader.R;
import com.antho.newsreader.viewmodel.NewsViewModel;
/****/
public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    NewsAdapter adapter;
    //ProgressBar progressBar;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //progressBar = new ProgressBar(MainActivity.this);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NewsViewModel model = ViewModelProviders.of(this).get(NewsViewModel.class);

        model.getNews().observe(this, new Observer<NewsListJsonModel>()
        {
            @Override
            public void onChanged(@Nullable NewsListJsonModel newsList) {
                adapter = new NewsAdapter(MainActivity.this, newsList);
                recyclerView.setAdapter(adapter);
            }
        });


    }
}
