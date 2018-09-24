package com.antho.newsreader.model.mostpopular;
/** Most popular media thumbnail **/
import android.support.annotation.Nullable;
import com.antho.newsreader.model.mostpopular.AutoValue_MostPopularMediaThumbnail;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for media thumbnail in most popular API **/
@AutoValue
public abstract class MostPopularMediaThumbnail
{
    // Thumbnail Url located in media-metadata from MostPopular API
    @Nullable
    @Json (name="media-metadata")
    public abstract List<MostPopularThumbnail> mediaThumbnailList();
    // Static factory method to access the package
    public static JsonAdapter<MostPopularMediaThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularMediaThumbnail.MoshiJsonAdapter(moshi);
    }
}
