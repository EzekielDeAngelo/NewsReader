package com.antho.newsreader.base;
/** Base activity**/
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import butterknife.ButterKnife;
/** Implements base methods for all activities **/
public abstract class BaseActivity  extends AppCompatActivity
{
    // Set layout and support action bar
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);
    }
    // Set a return value as a layout resource reference
    @LayoutRes
    protected abstract int layoutRes();
    //
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    // Helper method to display a toast
    protected void showToast(String message)
    {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
