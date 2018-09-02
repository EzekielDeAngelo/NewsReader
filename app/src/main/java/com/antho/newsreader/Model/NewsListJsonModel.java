package com.antho.newsreader.model;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/****/
@AutoValue
public abstract class NewsListJsonModel
{
    @Json(name="results")
    public abstract List<News> results();
    public static JsonAdapter<NewsListJsonModel> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_NewsListJsonModel.MoshiJsonAdapter(moshi);
    }
}
