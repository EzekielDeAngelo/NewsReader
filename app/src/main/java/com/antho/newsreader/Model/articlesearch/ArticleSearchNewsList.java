package com.antho.newsreader.model.articlesearch;

import android.support.annotation.NonNull;

import com.antho.newsreader.model.articlesearch.AutoValue_ArticleSearchNewsList;
import com.antho.newsreader.view.articlesearch.ArticleSearchAdapter;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/****/
@AutoValue
public abstract class ArticleSearchNewsList
{
    @Json(name="response")
    //public abstract Map<String, ArticleSearchNewsSubList> results();
    //public abstract ArrayList<ArticleSearchNewsSubList> results();
    public abstract List<ArticleSearchNewsSubList> results();

    public static JsonAdapter<ArticleSearchNewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_ArticleSearchNewsList.MoshiJsonAdapter(moshi);
    }
}

