package com.antho.newsreader.model.popular;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for news list in most popular API **/
@AutoValue
public abstract class PopularList
{
    @Json(name="results")
    public abstract List<Popular> results();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<PopularList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_PopularList.MoshiJsonAdapter(moshi);
    }
}
