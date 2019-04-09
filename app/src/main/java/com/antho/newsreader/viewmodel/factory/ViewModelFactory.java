package com.antho.newsreader.viewmodel.factory;
import com.antho.newsreader.viewmodel.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
/** Generates viewmodel with a string parameter **/
public class ViewModelFactory implements ViewModelProvider.Factory
{
    private final String mParam;
    // Constructor
    public ViewModelFactory(String param)
    {
        mParam = param;
    }
    // Return viewmodel with given string as a parameter
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        //noinspection unchecked
        return (T) new NewsViewModel(mParam);
    }
}
