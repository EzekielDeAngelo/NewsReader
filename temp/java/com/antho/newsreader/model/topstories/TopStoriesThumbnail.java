package com.antho.newsreader.model.news;
/****/
import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/****/
@AutoValue
public abstract class TopStoriesThumbnail
{
    // Thumbnail Url fetched from TopStories API
    @Nullable
    @Json (name="url")
    public abstract  String thumbnailUrl();
    // Thumbnail Url located in media-metadata from MostPopular API
    public static JsonAdapter<TopStoriesThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_TopStoriesThumbnail.MoshiJsonAdapter(moshi);
    }
}
