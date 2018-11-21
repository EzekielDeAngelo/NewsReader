package com.antho.newsreader.db;
/** News service **/
import com.antho.newsreader.model.popular.PopularList;
import com.antho.newsreader.model.news.NewsList;
import com.antho.newsreader.model.search.SearchListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
/** Instantiate calls to New York Times API **/
public interface NewsService
{
    // Call to top stories API
    @GET("topstories/v2/home.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Single<NewsList> getWorldNews();
    // Call to most popular API
    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Single<PopularList> getPopularNews();
    // Call to business API
    @GET("topstories/v2/business.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<NewsList> getBusinessNews();
    // Call to sports API
    @GET("topstories/v2/sports.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<NewsList> getSportsNews();
    // Call to article search API
    @GET("search/v2/articlesearch.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<SearchListResponse> getSearchNews(
            @Query("q") String query,
            @Query("fq") String categories,
            @Query("begin_date") String beginDate,
            @Query("end_date") String endDate,
            @Query("sort") String sortingOrder);
}
