package com.antho.newsreader.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import com.antho.newsreader.db.ArticleSearchApi;
import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.antho.newsreader.model.search.ArticleSearchNewsList;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ArticleSearchViewModel extends ViewModel
{
    private MutableLiveData<ArticleSearchNewsList> articleSearchList;
    //private MutableLiveData<NewsListJsonModel> mostPopularList;
    //private MutableLiveData<NewsListJsonModel> articleSearchList;
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<ArticleSearchNewsList> getNews()
    {
        if (articleSearchList == null)
        {
            loadNews();
        }
        return articleSearchList;
    }
    private void loadNews()
    {
        Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())

                //.add(new SearchAdapter())

                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ArticleSearchApi.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        ArticleSearchApi api = retrofit.create(ArticleSearchApi.class);
        articleSearchList = new MutableLiveData<ArticleSearchNewsList>();
        //Options.of("","")
        Log.d("LOADNEWS", "222222222222222222");
        Call<ArticleSearchNewsList> call = api.getArticleSearch();
        call.enqueue(new Callback<ArticleSearchNewsList>()
        {
            @Override
            public void onResponse(Call<ArticleSearchNewsList> call, Response<ArticleSearchNewsList> response)
            {
                Log.d("ONRESPONSE", "222222222222222222");
                articleSearchList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ArticleSearchNewsList> call, Throwable t)
            {
            }
        });
    }
}

