package com.antho.newsreader.db;
/****/
import com.antho.newsreader.model.articlesearch.ArticleSearchNewsList;
import retrofit2.Call;
import retrofit2.http.GET;
/****/
public interface ArticleSearchApi
{
    String BASE_URL = "https://api.nytimes.com/svc/";

    @GET("search/v2/articlesearch.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Call<ArticleSearchNewsList> getArticleSearch();
}
