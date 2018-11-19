package com.antho.newsreader.model.popular;
/** Most popular news **/
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for news objects in most popular API **/
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
    public abstract List<PopularMedia> mediaThumbnail();
    // Static factory method to access the package
    public static JsonAdapter<Popular> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Popular.MoshiJsonAdapter(moshi);
    }
}
