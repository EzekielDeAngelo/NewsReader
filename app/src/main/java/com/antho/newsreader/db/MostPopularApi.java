package com.antho.newsreader.db;
/****/

import com.antho.newsreader.model.mostpopular.MostPopularNewsList;
import com.antho.newsreader.model.topstories.TopStoriesNewsList;

import retrofit2.Call;
import retrofit2.http.GET;
/****/
public interface MostPopularApi
{
    String BASE_URL = "https://api.nytimes.com/svc/";
    @GET("mostpopular/v2/mostviewed/all-sections/7/.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<MostPopularNewsList> getMostPopular();
    /*@GET("search/v2/articlesearch.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<NewsListJsonModel> getArticleSearch();*/
}
