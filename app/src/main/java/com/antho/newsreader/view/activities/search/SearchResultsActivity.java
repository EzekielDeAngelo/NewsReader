package com.antho.newsreader.view.activities.search;
/** Search results activity **/
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.articlesearch.Response;
import com.antho.newsreader.view.activities.MainActivity;
import com.antho.newsreader.viewmodel.SearchViewModel;
/** Handle response from search activity **/
public class SearchResultsActivity extends MainActivity
{
    private RecyclerView recyclerView;
    // Set up a recyclerview to display to display search results
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview4);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        SearchViewModel model = ViewModelProviders.of(this).get(SearchViewModel.class);
        model.getNews().observe(this, new Observer<Response>()
        {
            @Override
            public void onChanged(@Nullable Response newsList)
            {
                SearchResultsAdapter adapter = new SearchResultsAdapter(getApplicationContext(), newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}