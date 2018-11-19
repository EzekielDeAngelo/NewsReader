package com.antho.newsreader.viewmodel;
/** Search viewmodel **/
import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.db.NewsApi;
import com.antho.newsreader.db.NewsService;
import com.antho.newsreader.model.search.Search;
import com.antho.newsreader.model.search.SearchListResponse;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**  **/
public class SearchViewModel extends ViewModel
{

    private final MutableLiveData<List<Search>> searchDocs = new MutableLiveData<>();
    private final MutableLiveData<Boolean> storiesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;
    private NewsService newsService;
    private String queryText;
    private String categoryText;
    private String beginDate;
    private String endDate;



    public SearchViewModel() {

    }

    public void setQueryParameters(
            String queryText,
            String categoryText,
            String beginDate,
            String endDate) {

        makeCallForSearch(
                queryText,
                categoryText,
                beginDate,
                endDate
        );
    }




    public LiveData<List<Search>> getSearchStories() {
        return searchDocs;
    }

    public LiveData<Boolean> getError() {
        return storiesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    @SuppressLint("CheckResult")
    public void makeCallForSearch(
            String query,
            String categories,
            String beginDate,
            String endDate) {

        Single<SearchListResponse> searchStories = NewsApi.getInstance().getSearchStories(
                query,
                categories,
                beginDate,
                endDate,
                "oldest");
        disposable = searchStories.subscribeOn(Schedulers.io())
                .subscribe(SearchNewsListResponse -> {
                    searchDocs.postValue(SearchNewsListResponse.searchNewsList().searchNews());
                    storiesLoadError.postValue(false);
                    loading.postValue(false);
                }, throwable -> {
                    storiesLoadError.postValue(true);
                    loading.postValue(false);
                });

    }



    @Override
    protected void onCleared() {
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
        super.onCleared();
    }





}

