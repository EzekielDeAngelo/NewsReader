package com.antho.newsreader.model;
/****/
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
    public abstract  String title();
    @Json (name="section")
    public abstract String section();
    @Json (name="subsection")
    public abstract String subsection();
    /*@Json (name="abstract")
    public abstract String article();*/
    @Json (name="published_date")
    public abstract ZonedDateTime date();
    @Json (name="multimedia")
    public abstract List<Thumbnail> thumbnail();
    public static JsonAdapter<News> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_News.MoshiJsonAdapter(moshi);
    }
}
