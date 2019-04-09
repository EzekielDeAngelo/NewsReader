package com.antho.newsreader.view.activities;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.antho.newsreader.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
/** UI tests for main activity **/
@SuppressWarnings("deprecation")
@RunWith(AndroidJUnit4.class)
public class  MainActivityTest
{
    private MainActivity mainActivity = null;
    @Rule
    public final ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void setUp() {
        mainActivity = mainActivityRule.getActivity();
    }
    // Context of the app under test.
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.antho.newsreader", appContext.getPackageName());
    }
    // Test on search activity launch
    @Test
    public void testLaunchSearchActivity()
    {
        onView(withId(R.id.search_item)).perform(click());
        mainActivityRule.launchActivity(new Intent());
    }
    // Test on notifications activity launch
    @Test
    public void testLaunchNotificationsActivity()
    {
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(mainActivity, R.id.settings_notifications, 0);
        mainActivityRule.launchActivity(new Intent());
    }
    // Perform clicks on navigation view to put fragment change under test
    @Test
    public void testChangeFragment()
    {
        onView(withId(R.id.action_world)).perform(click());
        onView(withId(R.id.action_business)).perform(click());
        onView(withId(R.id.action_popular)).perform(click());
        onView(withId(R.id.action_sports)).perform(click());
    }
}