package com.antho.newsreader.model.news;
/** Top stories thumbnail **/
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/** Data model for thumbnail objects in top stories API **/
@AutoValue
public abstract class NewsThumbnail
{
    @Nullable
    @Json (name="url")
    public abstract  String thumbnailUrl();
    // Static factory method to access the package
    public static JsonAdapter<NewsThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_NewsThumbnail.MoshiJsonAdapter(moshi);
    }
}
