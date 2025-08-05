package com.example.ecogrowplanner.ui.home;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ecogrowplanner.R;
import com.example.ecogrowplanner.activities.IdentifyPlantActivity;
import com.example.ecogrowplanner.activities.DiagnosePlantActivity;
import com.example.ecogrowplanner.ui.home.WeatherActivity;
import com.example.ecogrowplanner.ui.home.WaterReminderActivity;
import com.example.ecogrowplanner.ui.home.FertilizerReminderActivity;
import com.example.ecogrowplanner.ui.home.LightMeter;
import com.example.ecogrowplanner.ui.home.WaterCalculatorActivity;
import com.example.ecogrowplanner.ui.home.FertilizerCalculatorActivity;
import com.example.ecogrowplanner.ui.home.Pruneplant;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private static final String WATER_CHANNEL_ID = "water_reminder_channel";
    private static final String FERTILIZER_CHANNEL_ID = "fertilizer_reminder_channel";
    private static final int NOTIFICATION_PERMISSION_CODE = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setClickListener(view.findViewById(R.id.layout_identify_plant), PlantActivity.class);
        setClickListener(view.findViewById(R.id.layout_identify_disease), DiseaseDiseaseActivity.class);
        setClickListener(view.findViewById(R.id.layout_diagnose_disease), DiseaseDiseaseActivity.class);
        setClickListener(view.findViewById(R.id.layout_check_weather), WeatherActivity.class);
        setClickListener(view.findViewById(R.id.img_wat_rem), WaterReminderActivity.class);
        setClickListener(view.findViewById(R.id.img_fet_rem), FertilizerReminderActivity.class);
        setClickListener(view.findViewById(R.id.img_light_meter), LightMeter.class);
        setClickListener(view.findViewById(R.id.img_wat_calc), WaterCalculatorActivity.class);
        setClickListener(view.findViewById(R.id.img_fet_calc), FertilizerCalculatorActivity.class);
        setClickListener(view.findViewById(R.id.img_prune), Pruneplant.class);

        createNotificationChannels();
        requestNotificationPermission();

        return view;
    }

    private void setClickListener(View view, Class<?> targetActivity) {
        if (view != null) {
            view.setOnClickListener(v -> startActivity(new Intent(getActivity(), targetActivity)));
        }
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                NotificationChannel waterChannel = new NotificationChannel(WATER_CHANNEL_ID, "Water Reminder Notifications", NotificationManager.IMPORTANCE_HIGH);
                waterChannel.setDescription("Channel for Water Reminder Notifications");
                NotificationChannel fertilizerChannel = new NotificationChannel(FERTILIZER_CHANNEL_ID, "Fertilizer Reminder Notifications", NotificationManager.IMPORTANCE_HIGH);
                fertilizerChannel.setDescription("Channel for Fertilizer Reminder Notifications");
                notificationManager.createNotificationChannel(waterChannel);
                notificationManager.createNotificationChannel(fertilizerChannel);
            }
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Notification permission denied. Some features may not work.", Toast.LENGTH_LONG).show();
        }
    }
}
