package com.antho.newsreader.view.activities.notifications.handlers;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.antho.newsreader.R;
/** Handles notification channel creation **/
public class NotificationUtil
{
    private static final String CHANNEL_ID = "channel_news";
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
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return channelId;
        }
        else
        {
            return null;
        }
    }
}
