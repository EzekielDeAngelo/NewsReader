package com.antho.newsreader.model.news;
/****/
import androidx.annotation.Nullable;

import com.antho.newsreader.model.news.AutoValue_TopStoriesNews;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.time.ZonedDateTime;
import java.util.List;

/****/
@AutoValue
public abstract class TopStoriesNews
{
    @Json (name="title")
    public abstract String title();
    @Json (name="section")
    public abstract String section();
    @Nullable
    @Json (name="subsection")
    public abstract String subsection();
    @Json (name="url")
    public abstract String url();

    @Nullable
    @Json (name="published_date")
    public abstract ZonedDateTime date();
    @Nullable
    @Json (name="multimedia")
    public abstract List<TopStoriesThumbnail> thumbnail();

    public static JsonAdapter<TopStoriesNews> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_TopStoriesNews.MoshiJsonAdapter(moshi);
    }
}
