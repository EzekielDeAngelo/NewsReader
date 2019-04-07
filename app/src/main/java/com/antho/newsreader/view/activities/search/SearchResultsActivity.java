package com.antho.newsreader.view.activities.search;
/** Search results activity **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseActivity;
import com.antho.newsreader.view.activities.WebViewActivity;
import com.antho.newsreader.view.activities.search.adapter.SearchResultsAdapter;
import com.antho.newsreader.viewmodel.SearchViewModel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
/** Activity to display news from article search API **/
public class SearchResultsActivity  extends BaseActivity implements SearchResultsAdapter.OnStoryClickedListener
{
    @BindView(R.id.news_list) RecyclerView newsList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;
    private SearchViewModel viewModel;
    // Set string queries, viewmodel and adapter for search results activity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            setTitle("Search results");
            String queryText = bundle.getString("query");
            String categoriesText = bundle.getString("categories");
            String beginDate = bundle.getString("begin");
            String endDate = bundle.getString("end");
            viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
            viewModel.setQueryParameters(queryText, categoriesText, beginDate, endDate);
            newsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            newsList.setLayoutManager(new LinearLayoutManager(this));
            newsList.setAdapter(new SearchResultsAdapter(this));
            observeViewModel();
        }
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
            if (isError)
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
        viewModel.getSearchNews().observe(this, searchNews ->
        {
            SearchResultsAdapter adapter = (SearchResultsAdapter) newsList.getAdapter();
            adapter.setData(searchNews);
        });
    }
    // On click listener with url and section as parameters to display news in webview
    @Override
    public void onItemClicked (String url, String section)
    {
        Intent intent = new Intent(SearchResultsActivity.this, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("section", section);
        startActivity(intent);
    }
    // Return activity layout
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_search_results;
    }
}
