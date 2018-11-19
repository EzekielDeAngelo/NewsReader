package com.antho.newsreader.viewmodel;
/** Most popular viewmodel**/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.db.NewsApi;
import com.antho.newsreader.model.popular.Popular;
import com.antho.newsreader.model.popular.PopularList;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**  **/
public class PopularViewModel extends ViewModel
{
 //   private MutableLiveData<PopularList> popularList;
    private static PopularViewModel ourInstance = null;
    private MutableLiveData<List<Popular>> popularList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> popularLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private String storiesCategory;
    private Disposable disposable;

    public PopularViewModel() {
        loadPopularStories();
    }


    public LiveData<List<Popular>> getPopularStories() {
        return popularList;
    }

    public LiveData<Boolean> getError() {
        return popularLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void loadPopularStories() {
        loading.setValue(true);

        Single<PopularList> storyCall = NewsApi.getInstance().getPopularStories();
        disposable = storyCall.subscribeOn(Schedulers.io())
                //.observeOn(Schedulers.io())
                .subscribe(mostPopularNewsList -> {
                    popularLoadError.postValue(false);
                    popularList.postValue(mostPopularNewsList.results());
                    loading.postValue(false);
                }, throwable -> {
                    Timber.e(throwable.getLocalizedMessage());
                    popularLoadError.postValue(true);
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

   /*
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<PopularList> getNews()
    {
        if (mostPopularList == null)
        {
            loadNews();
        }
        return mostPopularList;
    }
    //
    private void loadNews()
    {
        Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MostPopularApi.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
        MostPopularApi api = retrofit.create(MostPopularApi.class);
        mostPopularList = new MutableLiveData<PopularList>();
        Call<PopularList> call = api.getMostPopular();
        call.enqueue(new Callback<PopularList>()
        {
            @Override
            public void onResponse(Call<PopularList> call, SearchList<PopularList> response)
            {
                mostPopularList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<PopularList> call, Throwable t)
            {
            }
        });
    }*/
}
