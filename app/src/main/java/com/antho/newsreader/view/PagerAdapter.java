package com.antho.newsreader.view;
/** Pager adapter **/
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.antho.newsreader.view.fragments.sport.Sports;
import com.antho.newsreader.view.fragments.mostpopular.MostPopular;
import com.antho.newsreader.view.fragments.topstories.TopStories;
/** Handle viewpager navigation **/
public class PagerAdapter extends FragmentPagerAdapter
{
    int tabsCount;
    // Constructor
    public PagerAdapter(FragmentManager fm, int tabsCount)
    {
        super(fm);
        this.tabsCount = tabsCount;
    }
    // Return fragment based on given parameter
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: return new TopStories();
            case 1: return new MostPopular();
            case 2: return new Sports();
            default: return null;
        }
    }
    // Return the number of viewpager's tab
    @Override
    public int getCount()
    {
        return tabsCount;
    }
    // Return fragment title based on given parameter
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: return "Top Stories";
            case 1: return "Most Popular";
            case 2: return "Article Search";
            default: return null;
        }
    }
}
