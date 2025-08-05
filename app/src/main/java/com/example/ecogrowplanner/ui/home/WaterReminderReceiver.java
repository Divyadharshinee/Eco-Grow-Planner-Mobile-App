package com.example.ecogrowplanner.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;





public class WaterReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper.sendNotification(context, "Water Reminder", "Time to water your plants!", true);
    }
}


