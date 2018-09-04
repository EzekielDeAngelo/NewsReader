package com.antho.newsreader.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.antho.newsreader.view.fragment.ArticleSearch;
import com.antho.newsreader.view.fragment.MostPopular;
import com.antho.newsreader.view.fragment.TopStories;

public class PagerAdapter extends FragmentPagerAdapter
{
    int tabsCount;
    // Constructor
    public PagerAdapter(FragmentManager fm, int tabsCount)
    {
        super(fm);
        this.tabsCount = tabsCount;
    }
    @Override    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: return new TopStories();
            case 1: return new MostPopular();
            case 2: return new ArticleSearch();
            default: return null;
        }

    }
    @Override
    public int getCount()
    {
        return tabsCount;
    }
    @Override    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: return "Top Stories";
            case 1: return "Most Popular";
            default: return null;
        }
    }
}
