package com.antho.newsreader.model;
/****/
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/****/
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
    /*@Json (name="abstract")
    public abstract String article();*/
    /*@Nullable
    @Json (name="published_date")
    public abstract ZonedDateTime date();*/
    @Nullable
    @Json (name="multimedia")
    public abstract List<Thumbnail> thumbnail();
    @Nullable
    @Json (name="media")
    public abstract List<Thumbnail> mediaThumbnail();
    public static JsonAdapter<News> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_News.MoshiJsonAdapter(moshi);
    }
}
