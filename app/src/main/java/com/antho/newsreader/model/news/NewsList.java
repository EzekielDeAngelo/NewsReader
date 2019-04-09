package com.antho.newsreader.model.news;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for news list object in top stories API **/
@AutoValue
public abstract class NewsList
{
    @Json(name="results")
    public abstract List<News> results();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<NewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_NewsList.MoshiJsonAdapter(moshi);
    }
}
