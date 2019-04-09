package com.antho.newsreader.view.activities.notifications.handlers;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Bundle;

import com.antho.newsreader.R;
import com.antho.newsreader.view.activities.MainActivity;
import com.antho.newsreader.view.activities.notifications.GlobalNotificationBuilder;
import com.antho.newsreader.view.activities.search.SearchResultsActivity;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import timber.log.Timber;

/** Job service to show notifications once a day **/
public class NotificationJobService extends JobService
{
    private NotificationManagerCompat mNotificationManagerCompat;
    private static final int NOTIFICATION_ID = 888;

    private int dailyNews;
    private String query;
    private String categories;
    private String beginDate;
    private String endDate;
    // Starts the job
    @Override
    public boolean onStartJob(JobParameters params)
    {
        Timber.d("Job started");
        dailyNews = params.getExtras().getInt("dailynews");
        query = params.getExtras().getString("query");
        categories = params.getExtras().getString("categories");
        beginDate = params.getExtras().getString("begin");
        endDate = params.getExtras().getString("end");
        mNotificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        doBackgroundWork();
        return true;
    }
    // Runs the scheduled task
    private void doBackgroundWork()
    {
        new Thread(() -> {
            if (dailyNews != 0)
                checkForNotifications();
        }).start();
    }
    // Gets called by the system if the job is cancelled before being finished
    @Override
    public boolean onStopJob(JobParameters params)
    {
        Timber.d( "Job cancelled before completion");
        return true;
    }
    // Search for notifications with specified query settings
    private void checkForNotifications()
    {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                .bigText("Tap to go to the articles")
                .setBigContentTitle("There is " + dailyNews + " news about " + query + " to read today!")
                .setSummaryText("Your daily news report" );
        Intent notifyIntent = new Intent(this, SearchResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        bundle.putString("categories", categories);
        bundle.putString("begin", beginDate);
        bundle.putString("end", endDate);
        notifyIntent.putExtras(bundle);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent dismissIntent = new Intent(this, BigTextIntentService.class);
        dismissIntent.setAction(BigTextIntentService.ACTION_DISMISS);
        PendingIntent dismissPendingIntent = PendingIntent.getService(this, 0, dismissIntent, 0);
        NotificationCompat.Action dismissAction = new NotificationCompat.Action.Builder(R.drawable.ic_search,"Dismiss",
                        dismissPendingIntent)
                        .build();
        NotificationCompat.Builder notificationCompatBuilder =
                new NotificationCompat.Builder(
                        getApplicationContext(), MainActivity.NOTIFICATION_CHANNEL_ID);
        GlobalNotificationBuilder.setNotificationCompatBuilderInstance(notificationCompatBuilder);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            notification = notificationCompatBuilder.setStyle(bigTextStyle)
                    .setContentTitle("There is " + dailyNews + " news about " + query + " to read today!")
                    .setContentText("NewsReader" )
                    .setSmallIcon(R.drawable.ic_world_news)
                    .setContentIntent(notifyPendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                    .setCategory(Notification.CATEGORY_REMINDER)
                    .addAction(dismissAction)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .build();
        }
        assert notification != null;
        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
