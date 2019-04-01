package com.antho.newsreader.viewmodel;
/** News ViewModel Test**/
import com.antho.newsreader.model.news.News;
import com.jraska.livedata.TestLifecycle;
import com.jraska.livedata.TestObserver;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
/** **/
public class NewsViewModelTest
{
    @Rule
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
    private NewsViewModel viewModel;
    //
    @Before
    public void setUp() throws Exception
    {
        viewModel = new NewsViewModel("BUSINESS_NEWS_TAG");
    }
    //
    @Test
    public void directAssertion()
    {
        LiveData<List<News>> liveData = viewModel.getNews();
        TestObserver.test(liveData)
                .assertNoValue();
    }
    //
    @Test
    public void setValueTest()
    {
        MutableLiveData<List<News>> liveData = viewModel.getMutableLiveData();
        TestObserver<List<News>> testObserver = TestObserver.test(liveData);

        List<News> dummyList = TestUtility.getTestingNewsListOfSize(3);
        liveData.setValue(dummyList);

        List<News> value = testObserver.value();
        assertThat(value).isEqualTo(dummyList);

        liveData.removeObserver(testObserver);
        assertThat(liveData.hasObservers()).isFalse();
    }

    @Test
    public void counterHistoryTest()
    {
        MutableLiveData<List<News>> liveData = viewModel.getMutableLiveData();
        TestObserver<List<News>> testObserver = TestObserver.test(liveData);

        List<News> dummyList = TestUtility.getTestingNewsListOfSize(3);
        liveData.setValue(dummyList);

        testObserver.assertHasValue()
                .assertHistorySize(1);

        for(int i = 0; i < 4; i++) {
            liveData.setValue(TestUtility.getTestingNewsListOfSize(30));
        }

        testObserver.
                assertHasValue()
                .assertHistorySize(5);

    }

    @Test
    public void useObserverWithLifecycle() {
        TestObserver<List<News>> testObserver = TestObserver.create();
        TestLifecycle testLifecycle = TestLifecycle.initialized();

        viewModel.getNews().observe(testLifecycle, testObserver);

        testObserver.assertNoValue();

        testLifecycle.resume();

        for (int i = 0; i < 4; i++) {
            viewModel
                    .getMutableLiveData()
                    .setValue(TestUtility.getTestingNewsListOfSize(4));
        }

        List<News> newsList = TestUtility.getTestingNewsListOfSize(10);
        viewModel.getMutableLiveData().setValue(newsList);

        testObserver.assertHasValue()
                .assertValue(newsList)
                .assertHistorySize(5);

        viewModel.getNews().removeObserver(testObserver);
    }
}