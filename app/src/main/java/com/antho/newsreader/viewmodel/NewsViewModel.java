package com.antho.newsreader.viewmodel;
/****/
import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.antho.newsreader.db.Api;
import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.NewsListJsonModel;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.ProgressBar;

import retrofit2.converter.moshi.MoshiConverterFactory;
/****/
public class NewsViewModel extends ViewModel
{
    private MutableLiveData<NewsListJsonModel> topStoriesList;
    private MutableLiveData<NewsListJsonModel> mostPopularList;
    private MutableLiveData<NewsListJsonModel> articleSearchList;
    private Api api;
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<NewsListJsonModel> getNews (String fragmentName)
    {
        Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
        api = retrofit.create(Api.class);
        Call<NewsListJsonModel> call;
        switch (fragmentName)
        {
            case "TopStories":
                if (topStoriesList == null)
                {
                    topStoriesList = new MutableLiveData<NewsListJsonModel>();
                    call = api.getTopStories();
                    call.enqueue(new Callback<NewsListJsonModel>()
                    {
                        @Override
                        public void onResponse(Call<NewsListJsonModel> call, Response<NewsListJsonModel> response)
                        {
                            topStoriesList.setValue(response.body());
                        }
                        @Override
                        public void onFailure(Call<NewsListJsonModel> call, Throwable t)
                        {
                        }
                    });
                }
                return topStoriesList;
            case "MostPopular":
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
                return articleSearchList;
            default:
                return null;
        }
    }
}