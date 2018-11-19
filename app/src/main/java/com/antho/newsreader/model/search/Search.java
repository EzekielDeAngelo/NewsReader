package com.antho.newsreader.model.search;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import javax.annotation.Nullable;

/****/
@AutoValue
public abstract class Search
{
    @Json(name = "_id")
    public abstract String id();
    @Json(name = "web_url")
    public abstract String webUrl();
    @Json(name = "multimedia")
    public abstract List<SearchMultimedia> multimedia();
    @Json (name = "headline")
    public abstract SearchHeadline headline();
    @Json(name = "pub_date")
    public abstract  String pubDate();
    @Nullable
    @Json(name = "section_name")
    public abstract String sectionName();

    /*
    @Json(name = "document_type")
    public abstract String documentType();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    @Json(name = "type_of_material")
    public abstract String typeOfMaterial();

    @Json(name = "word_count")
    public abstract Integer wordCount();
    @Json(name = "score")
    public abstract Integer score();
    @Json(name = "news_desk")
    public abstract String newsDesk();
    @Json(name = "snippet")
    public abstract String snippet();
    @Json(name = "abstract")
    public abstract String _abstract();
    @Json(name = "print_page")
    public abstract String printPage();
    @Json(name = "source")
    puzblic abstract String source();*/
    //
    public static JsonAdapter<Search> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Search.MoshiJsonAdapter(moshi);
    }
}
