package com.antho.newsreader.model.news;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import androidx.annotation.Nullable;
/** Data model for thumbnail objects in top stories API **/
@AutoValue
public abstract class NewsThumbnail
{
    @Nullable
    @Json (name="url")
    public abstract  String thumbnailUrl();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<NewsThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_NewsThumbnail.MoshiJsonAdapter(moshi);
    }
}
