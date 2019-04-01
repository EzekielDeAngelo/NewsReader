package com.antho.newsreader.model.popular;

import androidx.annotation.Nullable;
import com.antho.newsreader.model.popular.AutoValue_MostPopularThumbnail;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/****/
@AutoValue
public abstract class MostPopularThumbnail
{
    // Thumbnail Url fetched from MostPopular API
    @Nullable
    @Json(name="url")
    public abstract  String thumbnailUrl();

    public static JsonAdapter<MostPopularThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularThumbnail.MoshiJsonAdapter(moshi);
    }
}
