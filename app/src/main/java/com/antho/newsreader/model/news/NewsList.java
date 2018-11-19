package com.antho.newsreader.model.news;
/** Top stories news list **/
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
    // Static factory method to access the package
    public static JsonAdapter<NewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_NewsList.MoshiJsonAdapter(moshi);
    }
}
