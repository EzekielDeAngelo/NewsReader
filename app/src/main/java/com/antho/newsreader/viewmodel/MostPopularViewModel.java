package com.antho.newsreader.viewmodel;
/** Most popular viewmodel**/
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.antho.newsreader.db.MostPopularApi;
import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.antho.newsreader.model.mostpopular.MostPopularNewsList;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
/**  **/
public class MostPopularViewModel extends ViewModel
{
    private MutableLiveData<MostPopularNewsList> mostPopularList;
    // This method is using Retrofit to get the JSON data from URL
    public LiveData<MostPopularNewsList> getNews()
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
        mostPopularList = new MutableLiveData<MostPopularNewsList>();
        Call<MostPopularNewsList> call = api.getMostPopular();
        call.enqueue(new Callback<MostPopularNewsList>()
        {
            @Override
            public void onResponse(Call<MostPopularNewsList> call, Response<MostPopularNewsList> response)
            {
                mostPopularList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<MostPopularNewsList> call, Throwable t)
            {
            }
        });
    }
}
