package com.antho.newsreader.viewmodel;

import android.arch.lifecycle.Observer;

import com.antho.newsreader.db.NewsService;
import com.antho.newsreader.model.news.News;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class NewsViewModelTest
{
    private NewsViewModel newsViewModel;
    @Mock
    private NewsService service;
    @Mock
    Observer observer = Observer<List<News>>();
    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        newsViewModel = spy(new NewsViewModel(""));

    }
    @Test
    public void test()
    {
        observer = mock(Observer<List<News>>);
        newsViewModel.getNews().observeForever(observer);
        newsViewModel.loadTopStories();
       // verify(observer).onChanged(Resource(ResourceState.LOADING))
    }
    @Spy
    NewsViewModel viewModel = new NewsViewModel("");

}