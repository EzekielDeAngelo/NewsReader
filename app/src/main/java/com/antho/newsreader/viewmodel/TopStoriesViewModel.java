package com.antho.newsreader.viewmodel;
/****/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.antho.newsreader.db.TopStoriesApi;

import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.antho.newsreader.model.topstories.TopStoriesNewsList;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.moshi.MoshiConverterFactory;
/****/
public class TopStoriesViewModel extends ViewModel
{
    private MutableLiveData<TopStoriesNewsList> topStoriesList;
    //private MutableLiveData<NewsListJsonModel> mostPopularList;
    //private MutableLiveData<NewsListJsonModel> articleSearchList;
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<TopStoriesNewsList> getNews()
    {
        if (topStoriesList == null)
        {
            loadNews();
        }
        return topStoriesList;
    }
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
        topStoriesList = new MutableLiveData<TopStoriesNewsList>();
        Call<TopStoriesNewsList> call = api.getTopStories();
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
}

        /*case "MostPopular":
                if (mostPopularList == null)
                {
                    mostPopularList = new MutableLiveData<NewsListJsonModel>();
                    call = api.getMostPopular();
                    call.enqueue(new Callback<NewsListJsonModel>()
                    {
                        @Override
                        public void onResponse(Call<NewsListJsonModel> call, Response<NewsListJsonModel> response)
                        {
                            mostPopularList.setValue(response.body());

                        }
                        @Override
                        public void onFailure(Call<NewsListJsonModel> call, Throwable t)
                        {
                        }
                    });
                }
                return mostPopularList;
            case "ArticleSearch":
                if (articleSearchList == null)
                {
                    articleSearchList = new MutableLiveData<NewsListJsonModel>();
                    call = api.getArticleSearch();
                    call.enqueue(new Callback<NewsListJsonModel>()
                    {
                        @Override
                        public void onResponse(Call<NewsListJsonModel> call, Response<NewsListJsonModel> response)
                        {
                            articleSearchList.setValue(response.body());
                        }
                        @Override
                        public void onFailure(Call<NewsListJsonModel> call, Throwable t)
                        {
                        }
                    });
                }
                return articleSearchList;*/