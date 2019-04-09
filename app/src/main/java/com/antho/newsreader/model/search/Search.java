package com.antho.newsreader.model.search;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import javax.annotation.Nullable;
/** Data model for news objects in article search API **/
@SuppressWarnings("SameReturnValue")
@AutoValue
public abstract class Search
{
    @Json(name = "web_url")
    public abstract String url();
    @Json(name = "multimedia")
    public abstract List<SearchMultimedia> multimedia();
    @Json (name = "headline")
    public abstract SearchHeadline headline();
    @Json(name = "pub_date")
    public abstract  String date();
    @Nullable
    @Json(name = "section_name")
    public abstract String section();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<Search> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Search.MoshiJsonAdapter(moshi);
    }
}
