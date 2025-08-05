package com.example.ecogrowplanner.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FertilizerReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the calculated fertilizer amount from the intent
        double fertilizerAmount = intent.getDoubleExtra("fertilizerAmount", 0);
        String message = "Fertilize your plant with " + fertilizerAmount + " grams.";

        // Log message for debugging
        Log.d("FertilizerReminder", message);

        // Show notification
        NotificationHelper.sendNotification(context, "Fertilizer Reminder", "Time to fertilize your plant!", false);
    }
}


