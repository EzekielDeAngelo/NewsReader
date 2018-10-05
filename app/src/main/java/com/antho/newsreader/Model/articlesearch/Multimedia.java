package com.antho.newsreader.model.articlesearch;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue

public abstract class Multimedia {

    @Json(name = "url")

    public abstract String imageUrl();



    public static JsonAdapter<Multimedia> jsonAdapter(Moshi moshi) {

        return new AutoValue_Multimedia.MoshiJsonAdapter(moshi);

    }

}
