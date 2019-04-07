package com.antho.newsreader.viewmodel;
/** Search viewmodel test **/

import com.antho.newsreader.model.search.Search;
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
/** Unit tests on search viewmodel**/
public class SearchViewModelTest
{
    @Rule
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
    private SearchViewModel viewModel;
    // Set up dummy viewmodel to perform tests
    @Before
    public void setUp() throws Exception
    {
        viewModel = new SearchViewModel();
    }
    //
    @Test
    public void directAssertion()
    {
        LiveData<List<Search>> liveData = viewModel.getSearchNews();
        TestObserver.test(liveData)
                .assertNoValue();
    }
    //
    @Test
    public void setValueTest()
    {
        MutableLiveData<List<Search>> liveData = viewModel.getMutableLiveData();
        TestObserver<List<Search>> testObserver = TestObserver.test(liveData);
        List<Search> dummyList = TestUtility.getTestingSearchListOfSize(3);
        liveData.setValue(dummyList);
        List<Search> value = testObserver.value();
        assertThat(value).isEqualTo(dummyList);
        liveData.removeObserver(testObserver);
        assertThat(liveData.hasObservers()).isFalse();
    }
    //
    @Test
    public void counterHistoryTest()
    {
        MutableLiveData<List<Search>> liveData = viewModel.getMutableLiveData();
        TestObserver<List<Search>> testObserver = TestObserver.test(liveData);
        List<Search> dummyList = TestUtility.getTestingSearchListOfSize(3);
        liveData.setValue(dummyList);
        testObserver.assertHasValue()
                .assertHistorySize(1);
        for(int i = 0; i < 4; i++)
        {
            liveData.setValue(TestUtility.getTestingSearchListOfSize(30));
        }
        testObserver.
                assertHasValue()
                .assertHistorySize(5);
    }
    //
    @Test
    public void useObserverWithLifecycle()
    {
        TestObserver<List<Search>> testObserver = TestObserver.create();
        TestLifecycle testLifecycle = TestLifecycle.initialized();
        viewModel.getSearchNews().observe(testLifecycle, testObserver);
        testObserver.assertNoValue();
        testLifecycle.resume();
        for (int i = 0; i < 4; i++)
        {
            viewModel
                    .getMutableLiveData()
                    .setValue(TestUtility.getTestingSearchListOfSize(4));
        }
        List<Search> searchList = TestUtility.getTestingSearchListOfSize(10);
        viewModel.getMutableLiveData().setValue(searchList);
        testObserver.assertHasValue()
                .assertValue(searchList)
                .assertHistorySize(5);
        viewModel.getSearchNews().removeObserver(testObserver);
    }
}