package com.antho.newsreader.view.fragments;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseFragment;
import com.antho.newsreader.view.fragments.adapter.PopularAdapter;
import com.antho.newsreader.viewmodel.PopularViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
/** Creates fragment to display news from most popular API **/
public class PopularFragment extends BaseFragment
{
    @BindView(R.id.fragment_news_recyclerview) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;
    private PopularViewModel viewModel;
    // Constructor
    public PopularFragment() {}
    // Return new instance of popular fragment
    public static PopularFragment newInstance() {
        return new PopularFragment();
    }
    // Set viewmodel and adapter for popular fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PopularViewModel.class);
        newsList.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new PopularAdapter(viewModel, this, (PopularAdapter.OnStoryClickedListener) getActivity()));
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
        viewModel.getPopularNews().observe(this, stories ->
        {
            PopularAdapter adapter = (PopularAdapter) newsList.getAdapter();
            adapter.setData(stories);
        });
    }
    // Return fragment layout
    @Override
    public int layoutRes() {
        return R.layout.fragment_news;
    }
}
