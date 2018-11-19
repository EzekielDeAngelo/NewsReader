package com.antho.newsreader.model.search;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/****/
@AutoValue
public abstract class SearchList
{
    @Json(name = "docs")
    public abstract List<Search> searchNews();
    //
    public static JsonAdapter<SearchList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_SearchList.MoshiJsonAdapter(moshi);
    }
}
