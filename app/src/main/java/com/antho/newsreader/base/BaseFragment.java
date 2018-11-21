package com.antho.newsreader.base;
/** Base fragment**/
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/** Implements base methods for all fragments **/
public abstract class BaseFragment  extends Fragment
{
    private Unbinder unbinder;
    // Inflates the fragment's view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    // Unbind view
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
    // Set a return value as a layout resource reference
    @LayoutRes
    public abstract int layoutRes();
}
