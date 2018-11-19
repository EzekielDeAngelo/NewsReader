package com.antho.newsreader.view.activities;
/** Main activity **/
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseActivity;
import com.antho.newsreader.view.activities.notifications.NotificationsActivity;
import com.antho.newsreader.view.activities.search.SearchActivity;
import com.antho.newsreader.view.fragments.BusinessFragment;
import com.antho.newsreader.view.fragments.PopularFragment;
import com.antho.newsreader.view.fragments.SportsFragment;
import com.antho.newsreader.view.fragments.WorldFragment;
import com.antho.newsreader.view.fragments.adapter.NewsAdapter;
import com.antho.newsreader.view.fragments.adapter.PopularAdapter;

import butterknife.BindView;

/** Load UI elements on application startup **/
public class MainActivity extends BaseActivity implements NewsAdapter.OnStoryClickedListener, PopularAdapter.OnStoryClickedListener//, NavigationView.OnNavigationItemSelectedListener
{
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private static final String SELECTED_INDEX_KEY = "selected_index";

    private static final String WORLD_NEWS_TAG = "TOP_NEWS_TAG";
    private static final String POPULAR_NEWS_TAG = "TRENDING_NEWS_TAG";
    private static final String BUSINESS_NEWS_TAG = "BUSINESS_NEWS_TAG ";
    private static final String SPORTS_NEWS_TAG = "SPORTS_NEWS_TAG";

    private static final int WORLD_NEWS_INDEX = 0;
    private static final int POPULAR_NEWS_INDEX = 1;
    private static final int BUSINESS_NEWS_INDEX = 2;
    private static final int SPORTS_NEWS_INDEX = 3;

    private FragmentManager fragmentManager;
    // Set up toolbar, navigation drawer and viewpager
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        int selectedIndex = savedInstanceState == null ? 0 : savedInstanceState.getInt(SELECTED_INDEX_KEY);
        loadFirstFragment(selectedIndex);
        setUpBottomNavigation();
        //Toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Navigation drawer
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        //TabLayout
        /*TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("TOP STORIES"));
        tabLayout.addTab(tabLayout.newTab().setText("MOST POPULAR"));
        tabLayout.addTab(tabLayout.newTab().setText("SPORTS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/
        // ViewPager
       /* final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
            }
        });
        if (getSupportActionBar() != null)
    {
        getSupportActionBar().setTitle("News");
    }*/
    }
    /*
   Loads fragment on basis of selected index
   Default: 0
    */
    private void loadFirstFragment(int selectedIndex) {
        Fragment fragment = null;
        String tag = null;

        switch (selectedIndex) {
            case 0:
                tag = WORLD_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(WORLD_NEWS_TAG);
                if (fragment == null) {
                    fragment = WorldFragment.newInstance();
                }
                break;
            case 1:
                tag = POPULAR_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(POPULAR_NEWS_TAG);
                if (fragment == null) {
                    fragment = PopularFragment.newInstance();
                }
                break;
            case 2:
                tag = BUSINESS_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(BUSINESS_NEWS_TAG);
                if (fragment == null) {
                    fragment = BusinessFragment.newInstance();
                }
                break;
            case 3:
                tag = SPORTS_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(SPORTS_NEWS_TAG);
                if (fragment == null) {
                    fragment = SportsFragment.newInstance();
                }
                break;
        }

        if (fragment != null) {
            changeFragment(fragment, tag);
        }
    }

    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_world:
                    Fragment fragmentTop = fragmentManager.findFragmentByTag(WORLD_NEWS_TAG);
                    if (fragmentTop == null) fragmentTop = WorldFragment.newInstance();
                    changeFragment(fragmentTop, WORLD_NEWS_TAG);
                    return true;
                case R.id.action_popular:
                    Fragment fragmentTrending = fragmentManager.findFragmentByTag(POPULAR_NEWS_TAG);
                    if (fragmentTrending == null)
                        fragmentTrending = PopularFragment.newInstance();
                    changeFragment(fragmentTrending, POPULAR_NEWS_TAG);
                    return true;
                case R.id.action_business:
                    Fragment fragmentBusiness = fragmentManager.findFragmentByTag(BUSINESS_NEWS_TAG);
                    if (fragmentBusiness == null)
                        fragmentBusiness = BusinessFragment.newInstance();
                    changeFragment(fragmentBusiness, BUSINESS_NEWS_TAG);
                    return true;
                case R.id.action_sports:
                    Fragment fragmentSports = fragmentManager.findFragmentByTag(SPORTS_NEWS_TAG);
                    if (fragmentSports == null) fragmentSports = SportsFragment.newInstance();
                    changeFragment(fragmentSports, SPORTS_NEWS_TAG);
                    return true;
            }
            return false;
        });
    }

    public void changeFragment(Fragment fragment, String tagFragmentName) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.fragment_frame, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    // Inflate the menu : this adds items to the action bar if it is present
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.search_item:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings_notifications:
                intent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings_help:
                return true;
            case R.id.settings_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Handle navigation view item clicks here
    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        switch (item.getItemId())
        {
            case R.id.nav_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_notifications:
                intent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_top_stories:
                viewPager.setCurrentItem(0);
                tabLayout.getTabAt(0).select();
                break;
            case R.id.nav_most_popular:
                viewPager.setCurrentItem(1);
                tabLayout.getTabAt(1).select();
                break;
            case R.id.nav_sports:
                viewPager.setCurrentItem(2);
                tabLayout.getTabAt(2).select();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/
    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }
    @Override
    public void onItemClicked(String url) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        Log.d("url:" , url);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}