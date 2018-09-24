package com.antho.newsreader.model.mostpopular;
/**Most popular news list **/
import com.antho.newsreader.model.mostpopular.AutoValue_MostPopularNewsList;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for news list in most popular API **/
@AutoValue
public abstract class MostPopularNewsList
{
    @Json(name="results")
    public abstract List<MostPopularNews> results();
    // Static factory method to access the package
    public static JsonAdapter<MostPopularNewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_MostPopularNewsList.MoshiJsonAdapter(moshi);
    }
}
