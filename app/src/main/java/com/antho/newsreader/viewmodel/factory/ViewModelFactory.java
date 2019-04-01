package com.antho.newsreader.viewmodel.factory;
/** Viewmodel factory**/
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.antho.newsreader.viewmodel.NewsViewModel;
/** Generates viewmodels with a string parameter **/
public class ViewModelFactory implements ViewModelProvider.Factory
{
    private String mParam;
    // Constructor
    public ViewModelFactory(String param)
    {
        mParam = param;
    }
    // Return viewmodel with given string as a parameter
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new NewsViewModel(mParam);
    }
}
