package com.example.ecogrowplanner.ui.home;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecogrowplanner.R;

public class LightMeter extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightValue, lightDescription;
    private ProgressBar lightProgress;
    private ImageView lightIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_meter);

        // Initialize UI components
        lightValue = findViewById(R.id.lightValue);
        lightDescription = findViewById(R.id.lightDescription);
        lightProgress = findViewById(R.id.lightProgress);
        lightIcon = findViewById(R.id.lightIcon);

        // Setup sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightValue.setText("No Light Sensor Found");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];

        // Update light level text
        lightValue.setText("Light Level: " + lux + " lux");

        // Update progress bar
        int progress = (int) Math.min(lux, lightProgress.getMax());
        lightProgress.setProgress(progress);

        // Change description and icon based on brightness levels
        if (lux < 100) {
            lightDescription.setText("Very Low Light");
            lightIcon.setImageResource(R.drawable.ic_dim_light);
        } else if (lux < 500) {
            lightDescription.setText("Low Light");
            lightIcon.setImageResource(R.drawable.ic_medium_light);
        } else if (lux < 1000) {
            lightDescription.setText("Moderate Light");
            lightIcon.setImageResource(R.drawable.ic_bright_light);
        } else {
            lightDescription.setText("Very Bright Light");
            lightIcon.setImageResource(R.drawable.ic_sunlight);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}

