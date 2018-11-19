package com.antho.newsreader.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.antho.newsreader.viewmodel.NewsViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private String mParam;


    public ViewModelFactory(String param) {

        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NewsViewModel(mParam);
    }
}
