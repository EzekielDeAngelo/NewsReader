package com.antho.newsreader.view.fragments.topstories;
/** A simple {@link Fragment} subclass.**/
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antho.newsreader.R;
import com.antho.newsreader.model.topstories.TopStoriesNewsList;
import com.antho.newsreader.viewmodel.TopStoriesViewModel;
/** Handle the top stories fragment for viewpager **/
public class TopStories extends Fragment
{
    private RecyclerView recyclerView;
    // Required empty public constructor
    public TopStories()
    {
    }
    // Inflate the layout for this fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return (ViewGroup)inflater.inflate(R.layout.fragment_top_stories, container, false);
    }
    //
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_top_stories);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TopStoriesViewModel model = ViewModelProviders.of(this).get(TopStoriesViewModel.class);
        model.getNews("Top Stories").observe(this, new Observer<TopStoriesNewsList>()
        {
            @Override
            public void onChanged(@Nullable TopStoriesNewsList newsList)
            {
                TopStoriesAdapter adapter = new TopStoriesAdapter(getContext(), newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
