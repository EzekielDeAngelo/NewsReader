package com.antho.newsreader.view.activities.notifications;
/** Notifications activity test**/

import com.antho.newsreader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
/** UI tests for notifications activity **/
@RunWith(AndroidJUnit4.class)
public class NotificationsActivityTest
{
    @Rule
    public ActivityTestRule<NotificationsActivity> searchActivityRule = new ActivityTestRule<>(NotificationsActivity.class);
    // Perform tests on every view of the notifications activity
    @Test
    public void testNotifications()
    {
        onView(withId(R.id.searchTerm )).perform(typeText("Jean-Michel"), closeSoftKeyboard());
        onView(withId(R.id.artsCheckBox)).perform(click());
        onView(withId(R.id.businessCheckBox)).perform(click());
        onView(withId(R.id.entrepreneursCheckBox)).perform(click());
        onView(withId(R.id.politicsCheckBox)).perform(click());
        onView(withId(R.id.sportsCheckBox)).perform(click());
        onView(withId(R.id.travelCheckBox)).perform(click());
        onView(withId(R.id.sortSwitch)).perform(click());
    }
}