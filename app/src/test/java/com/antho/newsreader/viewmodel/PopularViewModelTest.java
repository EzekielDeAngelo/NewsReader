package com.antho.newsreader.viewmodel;
/** Popular viewmodel test **/
import com.antho.newsreader.model.popular.Popular;
import com.jraska.livedata.TestLifecycle;
import com.jraska.livedata.TestObserver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static org.assertj.core.api.Assertions.assertThat;
/** Unit tests on popular viewmodel **/
public class PopularViewModelTest
{
    @Rule
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
    private PopularViewModel viewModel;
    // Set up dummy viewmodel to perform tests
    @Before
    public void setUp() {
        viewModel = new PopularViewModel();
    }
    //
    @Test
    public void directAssertion()
    {
        LiveData<List<Popular>> liveData = viewModel.getPopularNews();
        TestObserver.test(liveData)
                .assertNoValue();
    }
    //
    @Test
    public void setValueTest()
    {
        MutableLiveData<List<Popular>> liveData = viewModel.getMutableLiveData();
        TestObserver<List<Popular>> testObserver = TestObserver.test(liveData);
        List<Popular> dummyList = TestUtility.getTestingPopularListOfSize(3);
        liveData.setValue(dummyList);
        List<Popular> value = testObserver.value();
        assertThat(value).isEqualTo(dummyList);
        liveData.removeObserver(testObserver);
        assertThat(liveData.hasObservers()).isFalse();
    }
    //
    @Test
    public void counterHistoryTest()
    {
        MutableLiveData<List<Popular>> liveData = viewModel.getMutableLiveData();
        TestObserver<List<Popular>> testObserver = TestObserver.test(liveData);
        List<Popular> dummyList = TestUtility.getTestingPopularListOfSize(3);
        liveData.setValue(dummyList);
        testObserver.assertHasValue()
                .assertHistorySize(1);
        for(int i = 0; i < 4; i++)
        {
            liveData.setValue(TestUtility.getTestingPopularListOfSize(30));
        }
        testObserver.
                assertHasValue()
                .assertHistorySize(5);
    }
    //
    @Test
    public void useObserverWithLifecycle()
    {
        TestObserver<List<Popular>> testObserver = TestObserver.create();
        TestLifecycle testLifecycle = TestLifecycle.initialized();
        viewModel.getPopularNews().observe(testLifecycle, testObserver);
        testObserver.assertNoValue();
        testLifecycle.resume();
        for (int i = 0; i < 4; i++)
        {
            viewModel
                    .getMutableLiveData()
                    .setValue(TestUtility.getTestingPopularListOfSize(5));
        }
        List<Popular> newsList = TestUtility.getTestingPopularListOfSize(10);
        viewModel.getMutableLiveData().setValue(newsList);
        testObserver.assertHasValue()
                .assertValue(newsList)
                .assertHistorySize(5);
        viewModel.getPopularNews().removeObserver(testObserver);
    }
}
