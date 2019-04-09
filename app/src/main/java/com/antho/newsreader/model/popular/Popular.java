package com.antho.newsreader.model.popular;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import androidx.annotation.Nullable;
/** Data model for news objects in most popular API **/
@SuppressWarnings("SameReturnValue")
@AutoValue
public abstract class Popular
{
    public abstract String title();
    public abstract String section();
    public abstract String url();
    @Nullable
    @Json (name="published_date")
    public abstract String date();
    @Nullable
    @Json (name="media")
    public abstract List<PopularMedia> multimedia();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<Popular> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Popular.MoshiJsonAdapter(moshi);
    }
}
