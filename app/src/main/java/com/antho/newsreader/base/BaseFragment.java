package com.antho.newsreader.base;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    @SuppressWarnings("SameReturnValue")
    @LayoutRes
    protected abstract int layoutRes();
}
