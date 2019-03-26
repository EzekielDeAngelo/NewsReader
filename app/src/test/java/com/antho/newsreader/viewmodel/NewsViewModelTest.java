package com.antho.newsreader.viewmodel;

import com.antho.newsreader.db.NewsService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.spy;

public class NewsViewModelTest
{
    private NewsViewModel newsViewModel;
    @Mock
    private NewsService service;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        newsViewModel = spy(new NewsViewModel(""));

    }
    @Test
    public void test()
    {

    }
    @Spy
    NewsViewModel viewModel = new NewsViewModel("");

}