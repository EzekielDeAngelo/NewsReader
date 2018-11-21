package com.antho.newsreader.model.search;
/** Article search news list **/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/** Data model for news list in article search API **/
@AutoValue
public abstract class SearchList
{
    @Json(name = "docs")
    public abstract List<Search> searchNews();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<SearchList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_SearchList.MoshiJsonAdapter(moshi);
    }
}
