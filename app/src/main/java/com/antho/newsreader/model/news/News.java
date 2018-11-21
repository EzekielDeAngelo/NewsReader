package com.antho.newsreader.model.news;
/** Top stories news **/
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.ZonedDateTime;

import java.util.List;
/** Data model for news objects in top stories API **/
@AutoValue
public abstract class News
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
    public abstract List<NewsThumbnail> multimedia();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<News> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_News.MoshiJsonAdapter(moshi);
    }
}
