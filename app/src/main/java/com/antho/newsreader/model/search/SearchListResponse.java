package com.antho.newsreader.model.search;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SearchListResponse {

    @Json(name = "response")
    public abstract SearchList searchNewsList();

    public static JsonAdapter<SearchListResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchListResponse.MoshiJsonAdapter(moshi);
    }
}