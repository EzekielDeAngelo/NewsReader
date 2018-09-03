package com.antho.newsreader.model;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
/****/
@AutoValue
public abstract class Thumbnail
{
    @Json (name="url")
    public abstract  String thumbnailUrl();

    public static JsonAdapter<Thumbnail> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Thumbnail.MoshiJsonAdapter(moshi);
    }
}
