package com.antho.newsreader.view;
/****/
import android.net.Uri;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.antho.newsreader.R;
import com.antho.newsreader.view.articlesearch.ArticleSearch;
import com.antho.newsreader.view.mostpopular.MostPopular;
import com.antho.newsreader.view.topstories.TopStories;

/****/
public class MainActivity extends AppCompatActivity implements TopStories.OnFragmentInteractionListener, MostPopular.OnFragmentInteractionListener, ArticleSearch.OnFragmentInteractionListener
{
    RecyclerView recyclerView;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

// Display icon in the toolbar
        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_menu);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("TOP STORIES"));
        tabLayout.addTab(tabLayout.newTab().setText("MOST POPULAR"));
        tabLayout.addTab(tabLayout.newTab().setText("ARTICLE SEARCH"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("News");
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.settings_notifications)
        {
            return true;
        }
        if (id == R.id.settings_help)
        {
            return true;
        }
        if (id == R.id.settings_about)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
