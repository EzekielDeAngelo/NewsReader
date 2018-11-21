package com.antho.newsreader.model.search;
/** Article search news list response **/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/** Data model for news list response in article search API **/
@AutoValue
public abstract class SearchListResponse
{
    @Json(name = "response")
    public abstract SearchList searchNewsList();
    // Creates a Moshi adapter for this data
    public static JsonAdapter<SearchListResponse> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_SearchListResponse.MoshiJsonAdapter(moshi);
    }
}