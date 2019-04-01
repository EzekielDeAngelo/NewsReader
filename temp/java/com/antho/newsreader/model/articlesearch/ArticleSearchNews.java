package com.antho.newsreader.model.search;

/****/
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antho.newsreader.model.search.AutoValue_ArticleSearchNews;
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

    @Json (name="section_name")
    public abstract String section();

public abstract String web_url();
    public abstract String snippet();

    public abstract String source();
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
