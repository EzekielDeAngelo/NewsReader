package com.antho.newsreader.model.search;
/** Article search news multimedia **/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/** Data model for multimedia in article search API **/
@AutoValue
public abstract class SearchMultimedia
{
    @Json(name = "url")
    public abstract String thumbnailUrl();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<SearchMultimedia> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_SearchMultimedia.MoshiJsonAdapter(moshi);
    }
}
