package com.antho.newsreader.view.activities.search;
/** Search results activity **/
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseActivity;
import com.antho.newsreader.view.activities.WebViewActivity;
import com.antho.newsreader.view.activities.search.adapter.SearchResultsAdapter;
import com.antho.newsreader.view.fragments.adapter.NewsAdapter;
import com.antho.newsreader.viewmodel.SearchViewModel;
import com.antho.newsreader.viewmodel.factory.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

/** Handle response from search activity **/
public class SearchResultsActivity  extends BaseActivity implements NewsAdapter.OnStoryClickedListener {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.news_list) RecyclerView newsList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;

    private SearchViewModel viewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyApplication.getAppComponent(this).inject(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String queryText = bundle.getString("query");
            String categoriesText = bundle.getString("categories");
            String beginDate = bundle.getString("begin");
            String endDate = bundle.getString("end");

            viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

            viewModel.setQueryParameters(
                    queryText,
                    categoriesText,
                    beginDate,
                    endDate
            );

            newsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            newsList.setLayoutManager(new LinearLayoutManager(this));
            newsList.setAdapter(new SearchResultsAdapter(this));
            observeViewModel();
        }
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
                errorText.setText("PRUUUUT");
            } else {
                errorText.setVisibility(View.GONE);
                errorText.setText(null);
            }

        });

        viewModel.getSearchStories().observe(this, searchDocuments -> {
            SearchResultsAdapter adapter = (SearchResultsAdapter) newsList.getAdapter();

            adapter.setData(searchDocuments);
        });

    }



    @Override
    protected int layoutRes() {
        return R.layout.activity_search_results;
    }


    @Override
    public void onItemClicked(String url) {
        Intent intent = new Intent(SearchResultsActivity.this, WebViewActivity.class);
        intent.putExtra("article_url", url);
        startActivity(intent);

    }
}
