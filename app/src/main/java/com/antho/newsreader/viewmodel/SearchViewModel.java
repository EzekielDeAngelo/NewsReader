package com.antho.newsreader.viewmodel;
/** Search viewmodel **/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


import com.antho.newsreader.db.SearchApi;
/**  **/
public class SearchViewModel extends ViewModel
{
    private MutableLiveData<com.antho.newsreader.model.articlesearch.Response> articleSearchList;
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<com.antho.newsreader.model.articlesearch.Response> getNews()
    {
        if (articleSearchList == null)
        {
            loadNews();
        }
        return articleSearchList;
    }
    //
    private void loadNews()
    {
        Moshi moshi = new Moshi.Builder()
            .add(AdapterFactory.create())
            .add(new ZonedDateTimeAdapter())
            .build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SearchApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build();
        SearchApi api = retrofit.create(SearchApi.class);
        articleSearchList = new MutableLiveData<com.antho.newsreader.model.articlesearch.Response>();
        Call<com.antho.newsreader.model.articlesearch.Response> call = api.getArticleSearch("docs");
        call.enqueue(new Callback<com.antho.newsreader.model.articlesearch.Response>()
        {
            @Override
            public void onResponse(Call<com.antho.newsreader.model.articlesearch.Response> call, Response<com.antho.newsreader.model.articlesearch.Response> response)
            {
                articleSearchList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<com.antho.newsreader.model.articlesearch.Response> call, Throwable t)
            { }
        });
    }
}

