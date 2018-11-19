package com.antho.newsreader.db;
/****/
import com.antho.newsreader.model.news.TopStoriesNewsList;

import retrofit2.Call;
import retrofit2.http.GET;
/****/
public interface TopStoriesApi
{
    String BASE_URL = "https://api.nytimes.com/svc/";
    @GET("topstories/v2/home.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<TopStoriesNewsList> getTopStories();
}
