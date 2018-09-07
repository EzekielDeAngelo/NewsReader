package com.antho.newsreader.model.mostpopular;

/****/
import android.support.annotation.Nullable;

import com.antho.newsreader.model.mostpopular.AutoValue_MostPopularNews;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.time.ZonedDateTime;
import java.util.List;

/****/
@AutoValue
public abstract class MostPopularNews
{
    public abstract String title();
    public abstract String section();
    public abstract String url();
    @Nullable
    @Json (name="published_date")
    public abstract String date();
    @Nullable
    @Json (name="media")
    public abstract List<MostPopularMediaThumbnail> mediaThumbnail();
    public static JsonAdapter<MostPopularNews> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularNews.MoshiJsonAdapter(moshi);
    }
}
