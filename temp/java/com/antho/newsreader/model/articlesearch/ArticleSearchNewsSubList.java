package com.antho.newsreader.model.search;
import androidx.annotation.NonNull;

import com.antho.newsreader.model.search.AutoValue_ArticleSearchNewsSubList;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
@AutoValue
public abstract class ArticleSearchNewsSubList
    {
/*
        @Json(name="docs")
        public abstract List<ArticleSearchNews> results();*/
        @Json(name="docs")
        public abstract List<ArticleSearchNews> results();
        public static JsonAdapter<ArticleSearchNewsSubList> jsonAdapter(Moshi moshi)
        {
            return new AutoValue_ArticleSearchNewsSubList.MoshiJsonAdapter(moshi);
        }
    }


