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

/**  **/
public class NewsViewModel extends ViewModel
{
    private static NewsViewModel ourInstance = null;
    private MutableLiveData<List<News>> topStoriesList = new MutableLiveData<>();
    private MutableLiveData<List<News>> sportsStoriesList = new MutableLiveData<>();
    private MutableLiveData<NewsList> sportsList;
    private final MutableLiveData<Boolean> topStoriesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private String storiesCategory;
    private Disposable disposable;
    // Test
    /*public MutableLiveData<NewsList> getNotes() {
        return topStoriesList;
    }*/
    /*public static NewsViewModel getInstance()
    {
        if(ourInstance == null)
        {
            ourInstance = new NewsViewModel();
            ourInstance.getNews("Top Stories");

        }
        return ourInstance;
    }
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<NewsList> getNews(String category)
    {
        if (topStoriesList == null && category == "Top Stories")
        {
            storiesCategory = category;
            loadNews();
            return topStoriesList;
        }
        else if (topStoriesList == null && category == "SportsFragment")
        {
            storiesCategory = category;
            loadNews();
            return sportsList;
        }
        else return null;
    }
    //
    private void loadNews()
    {
        Moshi moshi = new Moshi.Builder()
            .add(AdapterFactory.create())
            .add(new ZonedDateTimeAdapter())
            .build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TopStoriesApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build();
        TopStoriesApi api = retrofit.create(TopStoriesApi.class);

        Call<NewsList> call;
        if (storiesCategory == "Top Stories")
        {
            topStoriesList = new MutableLiveData<NewsList>();
            call = api.getTopStories();
            call.enqueue(new Callback<NewsList>()
            {
                @Override
                public void onResponse(Call<NewsList> call, SearchList<NewsList> response)
                {
                    topStoriesList.setValue(response.body());
                }
                @Override
                public void onFailure(Call<NewsList> call, Throwable t)
                {
                }
            });
        }
        else
        {
            sportsList = new MutableLiveData<NewsList>();
            call = api.getSports();
            call.enqueue(new Callback<NewsList>()
            {
                @Override
                public void onResponse(Call<NewsList> call, SearchList<NewsList> response)
                {
                    sportsList.setValue(response.body());
                }
                @Override
                public void onFailure(Call<NewsList> call, Throwable t)
                {
                }
            });
        }
    }*/
    public NewsViewModel(String storiesCategory) {
        this.storiesCategory = storiesCategory;
        loadTopStories();
    }

    public LiveData<List<News>> getSportsStories() {
        return topStoriesList;
    }
    public LiveData<List<News>> getTopStories() {
        return topStoriesList;
    }

    public LiveData<Boolean> getError() {
        return topStoriesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void loadTopStories() {
        loading.setValue(true);
        Single<NewsList> storyCall;
if (storiesCategory== "sports")
{
    storyCall = NewsApi.getInstance().getSportsStories();
}
else if (storiesCategory =="business")
    storyCall = NewsApi.getInstance().getBusinessStories();
else

    storyCall = NewsApi.getInstance().getTopStories();
    disposable = storyCall.subscribeOn(Schedulers.io())
            //.observeOn(Schedulers.io())
            .subscribe(topStoriesNewsList -> {
                topStoriesLoadError.postValue(false);
                topStoriesList.postValue(topStoriesNewsList.results());
                loading.postValue(false);
            }, throwable -> {
                Timber.e(throwable.getLocalizedMessage());
                topStoriesLoadError.postValue(true);
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
