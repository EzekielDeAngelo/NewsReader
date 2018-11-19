package com.antho.newsreader.model.search;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue

public abstract class SearchMultimedia {

    @Json(name = "url")

    public abstract String imageUrl();



    public static JsonAdapter<SearchMultimedia> jsonAdapter(Moshi moshi) {

        return new AutoValue_SearchMultimedia.MoshiJsonAdapter(moshi);

    }

}
