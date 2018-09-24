package com.antho.newsreader.model.articlesearch;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.antho.newsreader.model.articlesearch.AutoValue_Example;
/****/
@AutoValue
public abstract class Example
{
    @Json(name = "status")
    public abstract String status();
    @Json(name = "copyright")
    public abstract String copyright();
    @Json(name = "response")
    public abstract Response response();
    //
    public static JsonAdapter<Example> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Example.MoshiJsonAdapter(moshi);
    }
}