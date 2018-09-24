package com.antho.newsreader.model.mostpopular;
/** Most popular thumbnail**/
import android.support.annotation.Nullable;

import com.antho.newsreader.model.mostpopular.AutoValue_MostPopularThumbnail;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/** Data model for thumbnail in most popular API **/
@AutoValue
public abstract class MostPopularThumbnail
{
    @Nullable
    @Json(name="url")
    public abstract  String thumbnailUrl();
    // Static factory method to access the package
    public static JsonAdapter<MostPopularThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularThumbnail.MoshiJsonAdapter(moshi);
    }
}
