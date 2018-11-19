package com.antho.newsreader.model.news;
/****/
import com.antho.newsreader.model.news.AutoValue_TopStoriesNewsList;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/****/
@AutoValue
public abstract class TopStoriesNewsList
{
    @Json(name="results")
    public abstract List<TopStoriesNews> results();
    public static JsonAdapter<TopStoriesNewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_TopStoriesNewsList.MoshiJsonAdapter(moshi);
    }
}
