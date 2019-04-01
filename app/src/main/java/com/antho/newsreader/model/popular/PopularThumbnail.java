package com.antho.newsreader.model.popular;
/** Most popular thumbnail**/
import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/** Data model for thumbnail in most popular API **/
@AutoValue
public abstract class PopularThumbnail
{
    @Nullable
    @Json(name="url")
    public abstract  String thumbnailUrl();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<PopularThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_PopularThumbnail.MoshiJsonAdapter(moshi);
    }
}
