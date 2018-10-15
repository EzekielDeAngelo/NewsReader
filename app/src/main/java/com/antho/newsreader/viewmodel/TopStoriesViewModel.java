package com.antho.newsreader.viewmodel;
/** Top stories viewmodel **/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.db.TopStoriesApi;
import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.antho.newsreader.model.topstories.TopStoriesNewsList;
import com.squareup.moshi.Moshi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
/**  **/
public class TopStoriesViewModel extends ViewModel
{
    private static TopStoriesViewModel ourInstance = null;
    private MutableLiveData<TopStoriesNewsList> topStoriesList;
    private MutableLiveData<TopStoriesNewsList> sportsList;
    private String storiesCategory;
    // Test
    public MutableLiveData<TopStoriesNewsList> getNotes() {
        return topStoriesList;
    }
    public static TopStoriesViewModel getInstance()
    {
        if(ourInstance == null)
        {
            ourInstance = new TopStoriesViewModel();
            ourInstance.getNews("Top Stories");

        }
        return ourInstance;
    }
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<TopStoriesNewsList> getNews(String category)
    {
        if (topStoriesList == null && category == "Top Stories")
        {
            storiesCategory = category;
            loadNews();
            return topStoriesList;
        }
        else if (topStoriesList == null && category == "Sports")
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

        Call<TopStoriesNewsList> call;
        if (storiesCategory == "Top Stories")
        {
            topStoriesList = new MutableLiveData<TopStoriesNewsList>();
            call = api.getTopStories();
            call.enqueue(new Callback<TopStoriesNewsList>()
            {
                @Override
                public void onResponse(Call<TopStoriesNewsList> call, Response<TopStoriesNewsList> response)
                {
                    topStoriesList.setValue(response.body());
                }
                @Override
                public void onFailure(Call<TopStoriesNewsList> call, Throwable t)
                {
                }
            });
        }
        else
        {
            sportsList = new MutableLiveData<TopStoriesNewsList>();
            call = api.getSports();
            call.enqueue(new Callback<TopStoriesNewsList>()
            {
                @Override
                public void onResponse(Call<TopStoriesNewsList> call, Response<TopStoriesNewsList> response)
                {
                    sportsList.setValue(response.body());
                }
                @Override
                public void onFailure(Call<TopStoriesNewsList> call, Throwable t)
                {
                }
            });
        }
    }
}
