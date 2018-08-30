package com.antho.newsreader.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class News
{
    @SerializedName("title")
    private String title;
    @SerializedName("copyright")
    private String subSection;
    @SerializedName("subsection")
    private String section;
    @SerializedName("last_updated")
    private Date lastUpdated;
    @SerializedName("num_results")
    private Date numResults;


    public News(String title, String subSection, String section)
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
}
