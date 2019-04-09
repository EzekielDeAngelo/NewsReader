package com.antho.newsreader.db;
import com.antho.newsreader.model.AdapterFactory;
import com.antho.newsreader.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
/** Initialize database **/
public class NewsApi
{
    private static final String BASE_URL = "https://api.nytimes.com/svc/";
    private static Retrofit retrofit;
    private static NewsService newsService;
    private static Moshi moshi;
    // Creates an instance of news service
    public static NewsService getInstance()
    {
        if(newsService != null)
        {
            return newsService;
        }
        if(retrofit == null)
        {
            initMoshi();
            initRetrofit();
        }
        newsService = retrofit.create(NewsService.class);
        return newsService;
    }
    // Initialize Moshi
    private static void initMoshi()
    {
        moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();
    }
    // Initialize Retrofit
    private static void initRetrofit()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
