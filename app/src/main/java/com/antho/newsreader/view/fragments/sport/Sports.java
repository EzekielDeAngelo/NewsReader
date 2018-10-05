package com.antho.newsreader.view.fragments.sport;
/** A simple {@link Fragment} subclass. **/
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
import com.antho.newsreader.view.fragments.topstories.TopStoriesAdapter;
import com.antho.newsreader.viewmodel.TopStoriesViewModel;
/** Handle the sports stories fragment for viewpager **/
public class Sports extends Fragment
{
    RecyclerView recyclerView;
    // Required empty public constructor
    public Sports()
    {
    }
    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sports, container, false);
    }
    //
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_sports);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        TopStoriesViewModel model = ViewModelProviders.of(this).get(TopStoriesViewModel.class);
        model.getNews("Sports").observe(this, new Observer<TopStoriesNewsList>()
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
