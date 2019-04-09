package com.antho.newsreader.view.activities.notifications;
import androidx.core.app.NotificationCompat;
/** Update active notifications from other Services/Activities **/
public class GlobalNotificationBuilder
{
    // Constructor
    private GlobalNotificationBuilder() { }
    //
    public static void setNotificationCompatBuilderInstance (NotificationCompat.Builder builder)
    {
        @SuppressWarnings("unused") NotificationCompat.Builder sGlobalNotificationCompatBuilder = builder;
    }
}

