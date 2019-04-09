package com.antho.newsreader.viewmodel;
import com.antho.newsreader.db.NewsApi;
import com.antho.newsreader.model.news.News;
import com.antho.newsreader.model.news.NewsList;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
/** ViewModel for top stories API **/
public class NewsViewModel extends ViewModel
{
    private final MutableLiveData<List<News>> newsList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> topStoriesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;
    private final String newsCategory;
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
    private void loadTopStories()
    {
        loading.setValue(true);
        Single<NewsList> newsCall;
        if (Objects.equals(newsCategory, SPORTS_NEWS_TAG))
        {
            newsCall = NewsApi.getInstance().getSportsNews();
        }
        else if (Objects.equals(newsCategory, BUSINESS_NEWS_TAG))
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
