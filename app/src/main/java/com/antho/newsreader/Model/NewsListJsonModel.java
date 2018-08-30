package com.antho.newsreader.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewsListJsonModel
{
    private ArrayList<News> results;
    public ArrayList<News> getResults()
    {
        return results;
    }

    public void setNews(ArrayList<News> news)
    {
        this.results = results;
    }
}
