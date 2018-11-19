package com.antho.newsreader.model.search;

import android.support.annotation.NonNull;
import android.util.Log;

import com.antho.newsreader.model.search.AutoValue_ArticleSearchNewsList;
import com.antho.newsreader.view.articlesearch.ArticleSearchAdapter;
import com.google.auto.value.AutoValue;
import com.google.gson.JsonObject;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/****/
@AutoValue
public abstract class ArticleSearchNewsList
{
    /*@Json(name="response")
    //public abstract Map<String, ArticleSearchNewsSubList> results();
    //public abstract Map<String, String > results();
    //public abstract ArrayList<ArticleSearchNewsSubList> results();
    public abstract List<ArticleSearchNewsSubList> results();*/
    @Json(name="response")
    public abstract List<ArticleSearchNewsSubList> results();

    public static JsonAdapter<ArticleSearchNewsList> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_ArticleSearchNewsList.MoshiJsonAdapter(moshi);
    }
}


