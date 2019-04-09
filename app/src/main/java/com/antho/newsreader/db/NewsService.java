package com.antho.newsreader.db;
import com.antho.newsreader.model.news.NewsList;
import com.antho.newsreader.model.popular.PopularList;
import com.antho.newsreader.model.search.SearchListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
/** Instantiate calls to New York Times API **/
public interface NewsService
{
    // Call to top stories API
    @GET("topstories/v2/home.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs")
    Single<NewsList> getWorldNews();
    // Call to most popular API
    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs")
    Single<PopularList> getPopularNews();
    // Call to business API
    @GET("topstories/v2/business.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs")
    Single<NewsList> getBusinessNews();
    // Call to sports API
    @GET("topstories/v2/sports.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs")
    Single<NewsList> getSportsNews();
    // Call to article search API
    @GET("search/v2/articlesearch.json?api-key=C0v60kXXPtAEzaF1eYZL8oeBnGWvVlxs")
    Single<SearchListResponse> getSearchNews(
            @Query("q") String query,
            @Query("fq") String categories,
            @Query("begin_date") String beginDate,
            @Query("end_date") String endDate,
            @Query("sort") String sortingOrder);
}
