package com.antho.newsreader.view.activities.notifications.handlers;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import androidx.core.app.NotificationManagerCompat;

import timber.log.Timber;

import com.antho.newsreader.view.activities.notifications.NotificationsActivity;
/** Creates a dismiss action for notifications **/
@SuppressLint("Registered")
public class BigTextIntentService extends IntentService
{
    public static final String ACTION_DISMISS = "com.antho.newsreader/.view.activities.notifications.handlers.action.DISMISS";
    public BigTextIntentService()
    {
        super("BigTextIntentService");
    }
    //
    @Override
    protected void onHandleIntent(Intent intent)
    {
        Timber.d( "onHandleIntent(): %s", intent);
        if (intent != null)
        {
            final String action = intent.getAction();
            if (ACTION_DISMISS.equals(action))
            {
                handleActionDismiss();
            }
        }
    }
    // Handles action Dismiss in the provided background thread.
    private void handleActionDismiss()
    {
        Timber.d( "handleActionDismiss()");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.cancel(NotificationsActivity.NOTIFICATION_ID);
    }
}
