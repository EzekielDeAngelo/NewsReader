package com.antho.newsreader.view.activities;
/** Notification activity **/
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.antho.newsreader.R;
/** Handle notifications system **/
public class NotificationsActivity extends AppCompatActivity
{
    // Set up toolbar for navigation through activities
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
