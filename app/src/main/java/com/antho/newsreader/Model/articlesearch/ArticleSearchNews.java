package com.antho.newsreader.model.articlesearch;

/****/
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.antho.newsreader.model.articlesearch.AutoValue_ArticleSearchNews;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;
/****/
@AutoValue
public abstract class ArticleSearchNews
{
    //public abstract String title();
    @NonNull
    @Json (name="section_name")
    public abstract String section();
    @NonNull
public abstract String web_url();
    public abstract String snippet();
    @NonNull
    public abstract String sourc();
    /*public abstract String url();
    @Nullable
    @Json (name="published_date")
    public abstract String date();
    @Nullable
    @Json (name="media")
    public abstract List<MostPopularMediaThumbnail> mediaThumbnail();*/
    public static JsonAdapter<ArticleSearchNews> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_ArticleSearchNews.MoshiJsonAdapter(moshi);
    }
}
