package com.antho.newsreader.model.search;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;
/** Data model for multimedia in article search API **/
@AutoValue
public abstract class SearchMultimedia
{
    @Nullable
    @Json(name = "url")
    public abstract String thumbnailUrl();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<SearchMultimedia> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_SearchMultimedia.MoshiJsonAdapter(moshi);
    }
}
