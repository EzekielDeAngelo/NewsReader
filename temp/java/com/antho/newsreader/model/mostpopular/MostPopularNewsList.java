package com.antho.newsreader.model.popular;
/****/
import com.antho.newsreader.model.popular.AutoValue_MostPopularNewsList;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/****/
@AutoValue
public abstract class MostPopularNewsList
{
    @Json(name="results")
    public abstract List<MostPopularNews> results();
    public static JsonAdapter<MostPopularNewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularNewsList.MoshiJsonAdapter(moshi);
    }
}
