package com.antho.newsreader.model.articlesearch;
import android.support.annotation.NonNull;

import com.antho.newsreader.model.articlesearch.AutoValue_ArticleSearchNewsSubList;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
@AutoValue
public abstract class ArticleSearchNewsSubList
    {

        @Json(name="docs")
        public abstract List<ArticleSearchNews> results();

        public static JsonAdapter<ArticleSearchNewsSubList> jsonAdapter(Moshi moshi)
        {
            return new AutoValue_ArticleSearchNewsSubList.MoshiJsonAdapter(moshi);
        }
    }


