package com.antho.newsreader.viewmodel;
/****/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.Model.News;
import com.antho.newsreader.db.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/****/
public class NewsViewModel extends ViewModel
{
    private MutableLiveData<List<News>> newsList;
    //
    public LiveData<List<News>> getNews()
    {
        if (newsList == null)
        {
            newsList = new MutableLiveData<List<News>>();
            loadNews();
        }
        return newsList;
    }
    //This method is using Retrofit to get the JSON data from URL
    private void loadNews()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<News>> call = api.getNews();

        call.enqueue(new Callback<List<News>>()
        {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response)
            {
                newsList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<News>> call, Throwable t)
            {
            }
        });
    }
}