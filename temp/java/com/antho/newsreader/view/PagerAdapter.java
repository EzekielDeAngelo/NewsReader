package com.antho.newsreader.view;
/****/
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.Log;

import com.antho.newsreader.view.articlesearch.ArticleSearch;
import com.antho.newsreader.view.mostpopular.MostPopular;
import com.antho.newsreader.view.topstories.TopStories;
/****/
public class PagerAdapter extends FragmentPagerAdapter
{
    int tabsCount;
    // Constructor
    public PagerAdapter(FragmentManager fm, int tabsCount)
    {
        super(fm);
        this.tabsCount = tabsCount;
    }
    //
    @Override
    public Fragment getItem(int position)
    {
        Log.d("OOOOOOOOOOOOOOOOOOOOOOOOO",""+position);
        switch (position)
        {

            case 0: return new TopStories();
            case 1: return new MostPopular();
            case 2: return new ArticleSearch();
            default: return null;
        }
    }
    //
    @Override
    public int getCount()
    {
        return tabsCount;
    }
    //
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
