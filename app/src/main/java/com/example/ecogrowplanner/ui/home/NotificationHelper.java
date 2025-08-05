package com.example.ecogrowplanner.ui.home;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

import com.example.ecogrowplanner.R;

public class NotificationHelper {
    private static final String WATER_CHANNEL_ID = "water_reminder_channel";
    private static final String FERTILIZER_CHANNEL_ID = "fertilizer_reminder_channel";

    public static void sendNotification(Context context, String title, String message, boolean isWaterReminder) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, HomeFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Choose the correct notification channel
        String channelId = isWaterReminder ? WATER_CHANNEL_ID : FERTILIZER_CHANNEL_ID;
        int notificationId = isWaterReminder ? 1 : 2; // Different ID for each type to avoid overwriting

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(isWaterReminder ? R.drawable.logo : R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (notificationManager != null) {
            notificationManager.notify(notificationId, builder.build());
        }
    }
}

