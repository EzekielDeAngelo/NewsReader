package com.antho.newsreader.model;
/****/
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/****/
@AutoValue
public abstract class Thumbnail
{
    // Thumbnail Url fetched from TopStories API
    @Nullable
    @Json (name="url")
    public abstract  String thumbnailUrl();
    // Thumbnail Url located in media-metadata from MostPopular API
    @Nullable
    @Json (name="media-metadata")
    public abstract List<MediaThumbnail> mediaThumbnailList();

    public static JsonAdapter<Thumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Thumbnail.MoshiJsonAdapter(moshi);
    }
}
