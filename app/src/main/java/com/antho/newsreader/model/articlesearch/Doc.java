package com.antho.newsreader.model.articlesearch;
/****/
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.antho.newsreader.model.articlesearch.AutoValue_Doc;
import java.util.List;
/****/
@AutoValue
public abstract class Doc
{
    @Json(name = "web_url")
    public abstract String webUrl();
    @Json(name = "snippet")
    public abstract String snippet();
    @Json(name = "abstract")
    public abstract String _abstract();
    @Json(name = "print_page")
    public abstract String printPage();
    @Json(name = "source")
    public abstract String source();
    @Json(name = "multimedia")
    public abstract List<Object> multimedia();
    @Json(name = "pub_date")
    public abstract  String pubDate();
    @Json(name = "document_type")
    public abstract String documentType();
    @Json(name = "type_of_material")
    public abstract String typeOfMaterial();
    @Json(name = "_id")
    public abstract String id();
    @Json(name = "word_count")
    public abstract Integer wordCount();
    @Json(name = "score")
    public abstract Integer score();
    @Json(name = "news_desk")
    public abstract String newsDesk();
    //
    public static JsonAdapter<Doc> jsonAdapter(Moshi moshi)
    {
        return new AutoValue_Doc.MoshiJsonAdapter(moshi);
    }
}
