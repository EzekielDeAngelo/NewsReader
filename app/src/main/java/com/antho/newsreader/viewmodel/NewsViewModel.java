package com.antho.newsreader.viewmodel;
/****/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.antho.newsreader.Model.NewsListJsonModel;
import com.antho.newsreader.db.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/****/
public class NewsViewModel extends ViewModel
{
    private MutableLiveData<NewsListJsonModel> newsList;
    //
    public LiveData<NewsListJsonModel> getNews()
    {
        if (newsList == null)
        {
            newsList = new MutableLiveData<NewsListJsonModel>();
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
        Call<NewsListJsonModel> call = api.getResults();
        call.enqueue(new Callback<NewsListJsonModel>()
        {
            @Override
            public void onResponse(Call<NewsListJsonModel> call, Response<NewsListJsonModel> response)
            {
                newsList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<NewsListJsonModel> call, Throwable t)
            {
            }
        });
    }
}