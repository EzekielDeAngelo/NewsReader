package com.antho.newsreader.viewmodel;
/** Search viewmodel **/
import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.antho.newsreader.db.NewsApi;
import com.antho.newsreader.model.search.Search;
import com.antho.newsreader.model.search.SearchListResponse;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/** View model for article search API **/
public class SearchViewModel extends ViewModel
{
    private final MutableLiveData<List<Search>> searchList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> newsLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;
    // Constructor
    public SearchViewModel() {}
    // Call for article search news based on query parameters
    public void setQueryParameters(String queryText, String categoryText, String beginDate, String endDate)
    {
        makeCallForSearch(queryText, categoryText, beginDate, endDate);
    }
    // Return news list from article search API based on query parameters
    public LiveData<List<Search>> getSearchNews() {
        return searchList;
    }
    // Return news loading errors
    public LiveData<Boolean> getError() {
        return newsLoadError;
    }
    // Return loading status
    public LiveData<Boolean> getLoading() {
        return loading;
    }
    // Return MutableLiveData for testing purpose
    public MutableLiveData<List<Search>> getMutableLiveData() {
        return searchList;
    }
    // Search for news based on query parameters
    @SuppressLint("CheckResult")
    public void makeCallForSearch(String query, String categories, String beginDate, String endDate)
    {
        Single<SearchListResponse> newsCall = NewsApi.getInstance().getSearchNews(query, categories, beginDate, endDate,"oldest");
        disposable = newsCall.subscribeOn(Schedulers.io())
            .subscribe(SearchNewsListResponse ->
            {
               searchList.postValue(SearchNewsListResponse.searchNewsList().searchNews());
               newsLoadError.postValue(false);
               loading.postValue(false);
            }, throwable ->
            {
               newsLoadError.postValue(true);
               loading.postValue(false);
            });
    }
    // Clear method
    @Override
    protected void onCleared()
    {
        if (disposable != null && !disposable.isDisposed())
        {
            disposable.dispose();
            disposable = null;
        }
        super.onCleared();
    }
}

