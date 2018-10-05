package com.antho.newsreader;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.antho.newsreader.view.activities.MainActivity;
import com.antho.newsreader.view.activities.NotificationsActivity;
import com.antho.newsreader.view.activities.search.SearchActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.view.Gravity;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;
    // Monitor for notifications activity
    Instrumentation.ActivityMonitor notificationsMonitor = getInstrumentation().addMonitor(NotificationsActivity.class.getName(), null, false);
    // Monitor for search activity
    Instrumentation.ActivityMonitor searchMonitor = getInstrumentation().addMonitor(SearchActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception
    {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view = mainActivity.findViewById(R.id.toolbar);
        assertNotNull(view);
    }
    @Test
    public void testViewPager()
    {
        assertNotNull(mainActivity.findViewById(R.id.pager));
        onView(allOf(withId(R.id.recyclerview_top_stories), isDisplayed())).perform(click());
        onView(withId(R.id.pager)).perform(swipeLeft());
        onView(allOf(withId(R.id.recyclerview_most_popular), isDisplayed())).perform(click());
        mainActivity.finish();
    }
    @Test
    public void testLaunchNotificationsActivity()
    {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_notifications));
        Activity notificationsActivity = getInstrumentation().waitForMonitorWithTimeout(notificationsMonitor, 5000);
        assertNotNull(notificationsActivity);
        notificationsActivity.finish();
    }
    @Test
    public void testLaunchSearchActivity()
    {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_search));
        Activity searchActivity = getInstrumentation().waitForMonitorWithTimeout(searchMonitor, 5000);
        assertNotNull(searchActivity);
        searchActivity.finish();
    }
    @After
    public void tearDown() throws Exception
    {
        mainActivity = null;
    }
}
