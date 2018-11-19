package com.antho.newsreader.view.fragments;
/** A simple {@link Fragment} subclass.**/
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
import com.antho.newsreader.view.fragments.adapter.NewsAdapter;
import com.antho.newsreader.viewmodel.NewsViewModel;
import com.antho.newsreader.viewmodel.factory.ViewModelFactory;

import butterknife.BindView;

/** Handle the top stories fragment for viewpager **/
public class WorldFragment extends BaseFragment
{
    @BindView(R.id.recyclerview_top_stories) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;
    private NewsViewModel viewModel;

    private RecyclerView recyclerView;
    // Required empty public constructor
    public WorldFragment()
    {
    }
    // Inflate the layout for this fragment
    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return (ViewGroup)inflater.inflate(R.layout.fragment_top_stories, container, false);
    }*/

    public static WorldFragment newInstance() {
        return new WorldFragment();
    }
    //
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory("top")).get(NewsViewModel.class);
        newsList.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        newsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        newsList.setAdapter(new NewsAdapter(((NewsAdapter.OnStoryClickedListener) getActivity())));
        observeViewModel();
    }

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
                errorText.setText("PROUT");
            } else {
                errorText.setVisibility(View.GONE);
                errorText.setText(null);
            }
        });
        viewModel.getTopStories().observe(this, stories -> {
            NewsAdapter adapter = (NewsAdapter) newsList.getAdapter();
            adapter.setData(stories);
        });

    }
    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_top_stories);
        recyclerView.setLayoutManager(layoutManager);*/
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
     /*   NewsViewModel model = ViewModelProviders.of(this).get(NewsViewModel.class);
        model.getNews("Top Stories").observe(this, new Observer<NewsList>()
        {
            @Override
            public void onChanged(@Nullable NewsList newsList)
            {
                TopStoriesAdapter adapter = new TopStoriesAdapter(getContext(), newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }*/
    @Override
    public int layoutRes() {
        return R.layout.fragment_top_stories;
    }


}
