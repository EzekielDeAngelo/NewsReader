package com.antho.newsreader.db;
/**Top stories API **/
import com.antho.newsreader.model.topstories.TopStoriesNewsList;

import retrofit2.Call;
import retrofit2.http.GET;
/** Fetch data from NYTimes API for top stories**/
public interface TopStoriesApi
{
    String BASE_URL = "https://api.nytimes.com/svc/";
    // Fetch all sections articles from top stories API
    @GET("topstories/v2/home.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<TopStoriesNewsList> getTopStories();
    // Fetch sports articles from top stories API
    @GET("topstories/v2/sports.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<TopStoriesNewsList> getSports();
}
