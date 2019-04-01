package com.antho.newsreader.viewmodel;
/** Most popular viewmodel**/
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.antho.newsreader.db.NewsApi;
import com.antho.newsreader.model.news.News;
import com.antho.newsreader.model.popular.Popular;
import com.antho.newsreader.model.popular.PopularList;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
/** Viewmodel for most popular API **/
public class PopularViewModel extends ViewModel
{
    private MutableLiveData<List<Popular>> popularList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> popularLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Disposable disposable;
    // Constructor
    public PopularViewModel()
    {
        loadPopularStories();
    }
    // Return news list from most popular API
    public LiveData<List<Popular>> getPopularNews() {
        return popularList;
    }
    // Return news loading errors
    public LiveData<Boolean> getError() {
        return popularLoadError;
    }
    // Return loading status
    public LiveData<Boolean> getLoading() {
        return loading;
    }
    // Return MutableLiveData for testing purpose
    public MutableLiveData<List<Popular>> getMutableLiveData() {
        return popularList;
    }
    // Load popular news from most popular API
    private void loadPopularStories()
    {
        loading.setValue(true);
        Single<PopularList> newsCall = NewsApi.getInstance().getPopularNews();
        disposable = newsCall.subscribeOn(Schedulers.io())
           .subscribe(popularNewsList ->
           {
               popularLoadError.postValue(false);
               popularList.postValue(popularNewsList.results());
               loading.postValue(false);
           }, throwable ->
           {
               Timber.e(throwable.getLocalizedMessage());
               popularLoadError.postValue(true);
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
