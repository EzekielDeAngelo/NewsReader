package com.antho.newsreader.Model;
/****/
import com.google.gson.annotations.SerializedName;
/****/
public class News
{
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;
    //
    public News(Integer albumId, Integer id, String title, String url, String thumbnailUrl)
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
    }
}
