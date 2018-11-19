package com.antho.newsreader.view.fragments;
/** A simple {@link Fragment} subclass. **/
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseFragment;
import com.antho.newsreader.view.fragments.adapter.PopularAdapter;
import com.antho.newsreader.viewmodel.PopularViewModel;

import butterknife.BindView;

/** Handle the most popular stories fragment for viewpager **/
public class PopularFragment extends BaseFragment
{

    @BindView(R.id.recyclerview_top_stories) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;

    private PopularViewModel viewModel;

    // Required empty public constructor
    public PopularFragment()
    {
    }
    public static PopularFragment newInstance() {
        return new PopularFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PopularViewModel.class);
        newsList.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        newsList.setAdapter(new PopularAdapter(viewModel, this, (PopularAdapter.OnStoryClickedListener) getActivity()));//(((PopularAdapter.OnStoryClickedListener) getActivity())));
        observeViewModel();

    }
    //
    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_most_popular);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        PopularViewModel model = ViewModelProviders.of(this).get(PopularViewModel.class);
        model.getNews().observe(this, new Observer<PopularList>()
        {
            @Override
            public void onChanged(@Nullable PopularList newsList)
            {
                MostPopularAdapter adapter = new MostPopularAdapter(getContext(), newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }*/
    @SuppressWarnings("ConstantConditions")
    private void observeViewModel() {

        viewModel.getLoading().observe(this, isLoading -> {
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            newsList.setVisibility(isLoading ? View.GONE : View.VISIBLE);
            errorText.setVisibility(isLoading ? View.GONE : errorText.getVisibility());
        });

        viewModel.getError().observe(this, isError -> {
            if(isError) {
                errorText.setVisibility(View.VISIBLE);
                newsList.setVisibility(View.GONE);
                errorText.setText("PRUIT");
            } else {
                errorText.setVisibility(View.GONE);
                errorText.setText(null);
            }
        });
        viewModel.getPopularStories().observe(this, stories -> {
            PopularAdapter adapter = (PopularAdapter) newsList.getAdapter();
            adapter.setData(stories);
        });

    }

    @Override
    public int layoutRes() {
        return R.layout.fragment_top_stories;
    }

}
