package com.antho.newsreader;
/****/
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.antho.newsreader.Model.News;
import com.antho.newsreader.view.NewsAdapter;
import com.antho.newsreader.viewmodel.NewsViewModel;

import java.util.List;
/****/
public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NewsViewModel model = ViewModelProviders.of(this).get(NewsViewModel.class);

        model.getNews().observe(this, new Observer<List<News>>()
        {
            @Override
            public void onChanged(@Nullable List<News> newsList) {
                adapter = new NewsAdapter(MainActivity.this, newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
