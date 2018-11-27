package com.antho.newsreader.view.activities.notifications.handlers;
/** Notification util **/
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.content.Context;
import android.util.Log;

import com.antho.newsreader.R;

/** Handles notification channel creation **/
public class NotificationUtil
{
    public static final String CHANNEL_ID = "channel_news";
    // Create a notifications channel to enable notifications usage
    public static String createNotificationChannel(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "channel_news_1";
            CharSequence channelName = context.getString(R.string.channel_name);
            String channelDescription = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return channelId;
        }
        else
        {
            return null;
        }
    }
}
