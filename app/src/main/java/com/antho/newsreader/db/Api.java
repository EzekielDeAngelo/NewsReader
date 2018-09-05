package com.antho.newsreader.db;
/****/
import com.antho.newsreader.model.NewsListJsonModel;
import retrofit2.Call;
import retrofit2.http.GET;
/****/
public interface Api
{
    String BASE_URL = "https://api.nytimes.com/svc/";
    @GET("topstories/v2/home.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<NewsListJsonModel> getTopStories();
    @GET("mostpopular/v2/mostviewed/all-sections/7/.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<NewsListJsonModel> getMostPopular();
    @GET("search/v2/articlesearch.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<NewsListJsonModel> getArticleSearch();
}
