package com.antho.newsreader.model;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/****/
@AutoValue
public abstract class News
{
    @Json (name="title")
    public abstract  String title();
    public static JsonAdapter<News> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_News.MoshiJsonAdapter(moshi);
    }
}
