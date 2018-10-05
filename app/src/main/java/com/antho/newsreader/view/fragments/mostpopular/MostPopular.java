package com.antho.newsreader.view.fragments.mostpopular;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antho.newsreader.R;
import com.antho.newsreader.model.mostpopular.MostPopularNewsList;
import com.antho.newsreader.viewmodel.MostPopularViewModel;
/** Handle the most popular stories fragment for viewpager **/
public class MostPopular extends Fragment
{
    private RecyclerView recyclerView;
    // Required empty public constructor
    public MostPopular()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_most_popular, container, false);
    }
    //
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_most_popular);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        MostPopularViewModel model = ViewModelProviders.of(this).get(MostPopularViewModel.class);
        model.getNews().observe(this, new Observer<MostPopularNewsList>()
        {
            @Override
            public void onChanged(@Nullable MostPopularNewsList newsList)
            {
                MostPopularAdapter adapter = new MostPopularAdapter(getContext(), newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
