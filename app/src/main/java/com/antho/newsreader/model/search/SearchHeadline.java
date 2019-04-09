package com.antho.newsreader.model.search;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/** Data model for headline objects in article search API **/
@AutoValue
public abstract class SearchHeadline
{
    @Json(name = "main")
    public abstract String title();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<SearchHeadline> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_SearchHeadline.MoshiJsonAdapter(moshi);
    }
}