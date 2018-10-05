package com.antho.newsreader.model.articlesearch;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue

public abstract class Headline {

    @Json(name = "main")

    public abstract String mainHeadline();

    @Json(name = "print_headline")

    public abstract String printHeadline();



    public static JsonAdapter<Headline> jsonAdapter(Moshi moshi) {

        return new AutoValue_Headline.MoshiJsonAdapter(moshi);

    }

}