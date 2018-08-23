package com.antho.newsreader.db;
/****/
import com.antho.newsreader.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
/****/
public interface Api
{
    String BASE_URL = "https://jsonplaceholder.typicode.com";
    //https://api.nytimes.com/svc/topstories/v2/home.json
    @GET("photos")
    Call<List<News>> getNews();
}
