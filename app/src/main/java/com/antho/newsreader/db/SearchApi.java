package com.antho.newsreader.db;
/** Search API **/
import com.antho.newsreader.model.articlesearch.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/** Fetch data from NYTimes API for search queries **/
public interface SearchApi
{
    String BASE_URL = "https://api.nytimes.com/svc/";
    // Fetch search articles from article search API
    @GET("search/v2/articlesearch.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<Response> getArticleSearch();
}
