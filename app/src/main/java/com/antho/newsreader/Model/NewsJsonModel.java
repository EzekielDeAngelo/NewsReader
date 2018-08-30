package com.antho.newsreader.Model;
/****/
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/****/
public class NewsJsonModel
{
    @SerializedName("title")
    private String title;
    @SerializedName("copyright")
    private String subSection;
    @SerializedName("subsection")
    private String section;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("num_results")
    private String numResults;

    public NewsJsonModel(String title, String subSection, String section)
    {
        this.title = title;
        this.subSection = subSection;
        this.section = section;
    }
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getSubSection()
    {
        return subSection;
    }

    public void setSubSection(String subSection)
    {
        this.subSection = subSection;
    }
    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section = section;
    }

    /*

    @SerializedName("status")
    private String status;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("section")
    private String section;
    @SerializedName("last_updated")
    private Date lastUpdated;
    @SerializedName("num_results")
    private Date numResults;
    @SerializedName("results")
    private List<News> news;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getCopyright()
    {
        return copyright;
    }

    public void setCopyright(String copyright)
    {
        this.copyright = copyright;
    }
    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section = section;
    }
    public List<New> getNews ()
    {
        return news;
    }

    public void setNews(List<New> news)
    {
        this.news = news;
    }
 /*   @SerializedName("id")
    private Integer id;
    @SerializedName("results/title")
    @Expose
    private String title;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;
    @SerializedName("thumbnail_standard")
    private String thumbnailUrl;
    //
    public News(Integer id, String title, String thumbnailUrl)
    {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }
    //
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    //
    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }
    //
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }*/
}
