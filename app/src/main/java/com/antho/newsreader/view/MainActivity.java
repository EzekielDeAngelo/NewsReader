package com.antho.newsreader.view;
/****/
import android.app.ActionBar;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


import com.antho.newsreader.model.NewsListJsonModel;
import com.antho.newsreader.R;
import com.antho.newsreader.viewmodel.NewsViewModel;
/****/
public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    NewsAdapter adapter;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
// Display icon in the toolbar
        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_menu);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("News");
        }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.settings_notifications)
        {
            return true;
        }
        if (id == R.id.settings_help)
        {
            return true;
        }
        if (id == R.id.settings_about)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
