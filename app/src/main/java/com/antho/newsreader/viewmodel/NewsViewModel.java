package com.antho.newsreader.viewmodel;
/** Top stories viewmodel **/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.db.NewsApi;
import com.antho.newsreader.model.news.News;
import com.antho.newsreader.model.news.NewsList;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
/** ViewModel for top stories API **/
public class NewsViewModel extends ViewModel
{
    private MutableLiveData<List<News>> newsList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> topStoriesLoadError = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;
    private String newsCategory;
    private static final String BUSINESS_NEWS_TAG = "BUSINESS_NEWS_TAG";
    private static final String SPORTS_NEWS_TAG = "SPORTS_NEWS_TAG";
    // Constructor
    public NewsViewModel(String newsCategory)
    {
        this.newsCategory = newsCategory;
        loadTopStories();
    }
    // Return news list from top stories API
    public LiveData<List<News>> getNews() { return newsList; }
    // Return news loading errors
    public LiveData<Boolean> getError() { return topStoriesLoadError; }
    // Return loading status
    public LiveData<Boolean> getLoading() { return loading; }
    // Return MutableLiveData for testing purpose
    public MutableLiveData<List<News>> getMutableLiveData() {
        return newsList;
    }
    // Load news from top stories API
     void loadTopStories()
    {
        loading.setValue(true);
        Single<NewsList> newsCall;
        if (newsCategory == SPORTS_NEWS_TAG)
        {
            newsCall = NewsApi.getInstance().getSportsNews();
        }
        else if (newsCategory == BUSINESS_NEWS_TAG)
        {
            newsCall = NewsApi.getInstance().getBusinessNews();
        }
        else
        {
            newsCall = NewsApi.getInstance().getWorldNews();
        }
        disposable = newsCall.subscribeOn(Schedulers.io())
           .subscribe(topStoriesNewsList ->
           {
               topStoriesLoadError.postValue(false);
               newsList.postValue(topStoriesNewsList.results());
               loading.postValue(false);
           }, throwable ->
           {
               Timber.e(throwable.getLocalizedMessage());
               topStoriesLoadError.postValue(true);
               loading.postValue(false);
           });
    }
    // Clear method
    @Override
    protected void onCleared()
    {
        if(disposable != null && !disposable.isDisposed())
        {
            disposable.dispose();
            disposable = null;
        }
        super.onCleared();
    }
}
