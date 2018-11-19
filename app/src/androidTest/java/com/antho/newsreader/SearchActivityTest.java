package com.antho.newsreader;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.antho.newsreader.view.activities.search.SearchActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SearchActivityTest
{
    @Rule
    public ActivityTestRule<SearchActivity> notificationsActivityActivityTestRule = new ActivityTestRule<>(SearchActivity.class);

    private SearchActivity searchActivity = null;

    @Before
    public void setUp() throws Exception
    {
        searchActivity = notificationsActivityActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch()
    {
        View view = searchActivity.findViewById(R.id.search_query);
        assertNotNull(view);
    }
}
