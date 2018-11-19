package com.antho.newsreader.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.antho.newsreader.model.search.ArticleSearchNews;
import com.antho.newsreader.model.search.ArticleSearchNewsSubList;
import com.google.gson.JsonObject;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
/*
public class SearchAdapter {
    @FromJson
    ArticleSearchNewsSubList fromJson(Map<String, ArticleSearchNewsSubList> raw)
    {
        //String typename = raw.get("docs").toString();
        Log.d("xxx", "got" + raw.toString());
return new ArticleSearchNewsSubList() {
    @Override
    public List<ArticleSearchNews> results() {
        return null;
    }
};
}


    @ToJson
    String toJson(@NonNull ZonedDateTime value) {
        return value != null ? value.toString() : null;
    }
}*/
public class SearchAdapter
{
    @FromJson
    ArticleSearchNews fromJson(Map<String, ArticleSearchNews> json)
    {
        Log.d("xxx", "got" + json.toString());
        return new ArticleSearchNews() {
            @Override
            public String section() {
                return null;
            }

            @Override
            public String web_url() {
                return null;
            }

            @Override
            public String snippet() {
                return null;
            }

            @Override
            public String source() {
                return null;
            }
        };

    }
}