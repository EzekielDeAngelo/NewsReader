package com.antho.newsreader.view.fragments;
/** A simple {@link Fragment} subclass.**/
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseFragment;
import com.antho.newsreader.view.fragments.adapter.NewsAdapter;
import com.antho.newsreader.viewmodel.NewsViewModel;
import com.antho.newsreader.viewmodel.factory.ViewModelFactory;

import butterknife.BindView;
/** Creates fragment to display news from top stories API **/
public class WorldFragment extends BaseFragment
{
    @BindView(R.id.fragment_news_recyclerview) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;
    private NewsViewModel viewModel;
    // Constructor
    public WorldFragment() {}
    // Return new instance of world fragment
    public static WorldFragment newInstance()
    {
        return new WorldFragment();
    }
    // Set viewmodel and adapter for world fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory("top")).get(NewsViewModel.class);
        newsList.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new NewsAdapter(((NewsAdapter.OnStoryClickedListener) getActivity())));
        observeViewModel();
    }
    // Observe viewmodel to update loading status, news and show errors
    @SuppressWarnings("ConstantConditions")
    private void observeViewModel()
    {
        viewModel.getLoading().observe(this, isLoading ->
        {
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            newsList.setVisibility(isLoading ? View.GONE : View.VISIBLE);
            errorText.setVisibility(isLoading ? View.GONE : errorText.getVisibility());
        });
        viewModel.getError().observe(this, isError ->
        {
            if(isError)
            {
                errorText.setVisibility(View.VISIBLE);
                newsList.setVisibility(View.GONE);
                errorText.setText(R.string.api_loading_error);
            } else
            {
                errorText.setVisibility(View.GONE);
                errorText.setText(null);
            }
        });
        viewModel.getNews().observe(this, stories ->
        {
            NewsAdapter adapter = (NewsAdapter) newsList.getAdapter();
            adapter.setData(stories);
        });
    }
    // Return fragment layout
    @Override
    public int layoutRes()
    {
        return R.layout.fragment_news;
    }
}
