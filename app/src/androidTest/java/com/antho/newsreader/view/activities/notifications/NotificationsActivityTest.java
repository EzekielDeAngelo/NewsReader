package com.antho.newsreader.view.activities.notifications;
/** **/
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import com.antho.newsreader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
/** **/
@RunWith(AndroidJUnit4.class)
public class NotificationsActivityTest
{
    @Rule
    public ActivityTestRule<NotificationsActivity> searchActivityRule = new ActivityTestRule<>(NotificationsActivity.class);

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