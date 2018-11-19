package com.antho.newsreader.db;

import com.antho.newsreader.model.popular.PopularList;
import com.antho.newsreader.model.news.NewsList;
import com.antho.newsreader.model.search.SearchListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("topstories/v2/home.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Single<NewsList> getTopStories();

    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=829b53058f9f400d94c5d44245489fdf")
    Single<PopularList> getPopularStories();

    @GET("topstories/v2/business.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<NewsList> getBusinessStories();

    @GET("topstories/v2/sports.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<NewsList> getSportsStories();

    @GET("search/v2/articlesearch.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<SearchListResponse> getSearchStories(
            @Query("q") String query,
            @Query("fq") String categories,
            @Query("begin_date") String beginDate,
            @Query("end_date") String endDate,
            @Query("sort") String sortingOrder
    );

}
