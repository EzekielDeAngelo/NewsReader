package com.antho.newsreader.db;
/****/
import com.antho.newsreader.Model.NewsListJsonModel;
import retrofit2.Call;
import retrofit2.http.GET;
/****/
public interface Api
{
    String BASE_URL = "https://api.nytimes.com/svc/topstories/v2/";
    @GET("home.json?api-key=829b53058f9f400d94c5d44245489fdf")

    Call<NewsListJsonModel> getResults();
}
