package com.antho.newsreader.view.activities.notifications;
/** Global notification builder **/
import android.support.v4.app.NotificationCompat;
/** Update active notifications from other Services/Activities **/
public class GlobalNotificationBuilder
{
    private static NotificationCompat.Builder sGlobalNotificationCompatBuilder = null;
    // Constructor
    private GlobalNotificationBuilder() { }
    //
    public static void setNotificationCompatBuilderInstance (NotificationCompat.Builder builder)
    {
        sGlobalNotificationCompatBuilder = builder;
    }
    //
    /*public static NotificationCompat.Builder getNotificationCompatBuilderInstance()
    {
        return sGlobalNotificationCompatBuilder;
    }*/
}

