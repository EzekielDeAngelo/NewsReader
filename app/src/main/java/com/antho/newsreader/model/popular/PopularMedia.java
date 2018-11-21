package com.antho.newsreader.model.popular;
/** Most popular media thumbnail **/
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for media thumbnail in most popular API **/
@AutoValue
public abstract class PopularMedia
{
    @Nullable
    @Json (name="media-metadata")
    public abstract List<PopularThumbnail> media();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<PopularMedia> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_PopularMedia.MoshiJsonAdapter(moshi);
    }
}
