package com.antho.newsreader.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseActivity;
import com.antho.newsreader.view.activities.notifications.NotificationsActivity;
import com.antho.newsreader.view.activities.notifications.handlers.NotificationUtil;
import com.antho.newsreader.view.activities.search.SearchActivity;
import com.antho.newsreader.view.fragments.BusinessFragment;
import com.antho.newsreader.view.fragments.PopularFragment;
import com.antho.newsreader.view.fragments.SportsFragment;
import com.antho.newsreader.view.fragments.WorldFragment;
import com.antho.newsreader.view.fragments.adapter.NewsAdapter;
import com.antho.newsreader.view.fragments.adapter.PopularAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
/** Load UI elements on application startup **/
public class MainActivity extends BaseActivity implements NewsAdapter.OnStoryClickedListener, PopularAdapter.OnStoryClickedListener, NavigationView.OnNavigationItemSelectedListener
{
    @BindView(R.id.navigation) BottomNavigationView bottomNavigationView;
    private static final String SELECTED_INDEX_KEY = "selected_index";
    private static final String WORLD_NEWS_TAG = "TOP_NEWS_TAG";
    private static final String POPULAR_NEWS_TAG = "TRENDING_NEWS_TAG";
    private static final String BUSINESS_NEWS_TAG = "BUSINESS_NEWS_TAG";
    private static final String SPORTS_NEWS_TAG = "SPORTS_NEWS_TAG";
    public static String NOTIFICATION_CHANNEL_ID;
    private FragmentManager fragmentManager;
    // Loads first fragment, set fragment manager and bottom navigation
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        int selectedIndex = savedInstanceState == null ? 0 : savedInstanceState.getInt(SELECTED_INDEX_KEY);
        loadFirstFragment(selectedIndex);
        setUpBottomNavigation();
        NOTIFICATION_CHANNEL_ID = NotificationUtil.createNotificationChannel(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    // Loads fragment based on index given as parameter
    private void loadFirstFragment(int selectedIndex)
    {
        Fragment fragment = null;
        String tag = null;
        switch (selectedIndex)
        {
            case 0:
                tag = WORLD_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(WORLD_NEWS_TAG);
                if (fragment == null)
                {
                    fragment = WorldFragment.newInstance();
                }
                break;
            case 1:
                tag = POPULAR_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(POPULAR_NEWS_TAG);
                if (fragment == null)
                {
                    fragment = PopularFragment.newInstance();
                }
                break;
            case 2:
                tag = BUSINESS_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(BUSINESS_NEWS_TAG);
                if (fragment == null)
                {
                    fragment = BusinessFragment.newInstance();
                }
                break;
            case 3:
                tag = SPORTS_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(SPORTS_NEWS_TAG);
                if (fragment == null)
                {
                    fragment = SportsFragment.newInstance();
                }
                break;
        }
        if (fragment != null)
        {
            changeFragment(fragment, tag);
        }
    }
    // Set up buttons for bottom navigation view
    private void setUpBottomNavigation()
    {
        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem ->
        {
            switch (menuItem.getItemId())
            {
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
    // Load and unload fragments when they are changed
    public void changeFragment(Fragment fragment, String tagFragmentName)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null)
        {
            fragmentTransaction.hide(currentFragment);
        }
        Fragment fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null)
        {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.fragment_frame, fragmentTemp, tagFragmentName);
        } else
        {
            fragmentTransaction.show(fragmentTemp);
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
    // Creates the options menu for action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // Handles action bar item clicks
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

    // On click listener with url and section as parameters to display news in webview
    @Override
    public void onItemClicked(String url, String section)
    {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("section", section);
        startActivity(intent);
    }
    // Handles navigation drawer behavior
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        bottomNavigationView.setSelectedItemId(id);

        if (id == R.id.nav_world)
        {
            Fragment fragmentTop = fragmentManager.findFragmentByTag(WORLD_NEWS_TAG);
            if (fragmentTop == null) fragmentTop = WorldFragment.newInstance();
            changeFragment(fragmentTop, WORLD_NEWS_TAG);
        }
        if (id == R.id.nav_popular)
        {
            Fragment fragmentTrending = fragmentManager.findFragmentByTag(POPULAR_NEWS_TAG);
            if (fragmentTrending == null)
                fragmentTrending = PopularFragment.newInstance();
            changeFragment(fragmentTrending, POPULAR_NEWS_TAG);
        }
        if (id == R.id.nav_sports)
        {
            Fragment fragmentBusiness = fragmentManager.findFragmentByTag(BUSINESS_NEWS_TAG);
            if (fragmentBusiness == null)
                fragmentBusiness = BusinessFragment.newInstance();
            changeFragment(fragmentBusiness, BUSINESS_NEWS_TAG);
        }
        if (id == R.id.nav_business)
        {
            Fragment fragmentSports = fragmentManager.findFragmentByTag(SPORTS_NEWS_TAG);
            if (fragmentSports == null) fragmentSports = SportsFragment.newInstance();
            changeFragment(fragmentSports, SPORTS_NEWS_TAG);
        }
        if (id == R.id.nav_search)
        {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_notifications)
        {
            Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // Return activity layout
    @Override
    protected int layoutRes()
    {
        return R.layout.activity_main;
    }



}