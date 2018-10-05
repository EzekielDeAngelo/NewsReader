package com.antho.newsreader;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.antho.newsreader.view.activities.MainActivity;
import com.antho.newsreader.view.activities.NotificationsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NotificationsActivityTest
{

    @Rule
    public ActivityTestRule<NotificationsActivity> notificationsActivityActivityTestRule = new ActivityTestRule<>(NotificationsActivity.class);

    private NotificationsActivity notificationsActivity = null;

    @Before
    public void setUp() throws Exception
    {
        notificationsActivity = notificationsActivityActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch()
    {
        View view = notificationsActivity.findViewById(R.id.editText);
        assertNotNull(view);
    }
}
