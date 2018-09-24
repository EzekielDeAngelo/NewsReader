package com.antho.newsreader.model.topstories;
/** Top stories news list **/
import com.antho.newsreader.model.topstories.AutoValue_TopStoriesNewsList;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for news list object in top stories API **/
@AutoValue
public abstract class TopStoriesNewsList
{
    @Json(name="results")
    public abstract List<TopStoriesNews> results();
    // Static factory method to access the package
    public static JsonAdapter<TopStoriesNewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_TopStoriesNewsList.MoshiJsonAdapter(moshi);
    }
}
