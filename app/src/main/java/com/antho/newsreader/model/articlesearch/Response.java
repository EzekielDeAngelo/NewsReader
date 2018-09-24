package com.antho.newsreader.model.articlesearch;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.antho.newsreader.model.articlesearch.AutoValue_Response;
import java.util.List;
/****/
@AutoValue
public abstract class Response
{
    @Json(name = "docs")
    public abstract List<Doc> docs();
    //
    public static JsonAdapter<Response> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Response.MoshiJsonAdapter(moshi);
    }
}
