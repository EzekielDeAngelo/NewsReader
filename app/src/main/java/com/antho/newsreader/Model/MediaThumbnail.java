package com.antho.newsreader.model;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/****/
@AutoValue
public abstract class MediaThumbnail
{
    // Thumbnail Url fetched from MostPopular API
    @Nullable
    @Json (name="url")
    public abstract  String thumbnailUrl();

    public static JsonAdapter<MediaThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MediaThumbnail.MoshiJsonAdapter(moshi);
    }
}
