package com.antho.newsreader.model.mostpopular;
/****/
import android.support.annotation.Nullable;
import com.antho.newsreader.model.mostpopular.AutoValue_MostPopularMediaThumbnail;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/****/
@AutoValue
public abstract class MostPopularMediaThumbnail
{
    // Thumbnail Url located in media-metadata from MostPopular API
    @Nullable
    @Json (name="media-metadata")
    public abstract List<MostPopularThumbnail> mediaThumbnailList();

    public static JsonAdapter<MostPopularMediaThumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularMediaThumbnail.MoshiJsonAdapter(moshi);
    }
}
