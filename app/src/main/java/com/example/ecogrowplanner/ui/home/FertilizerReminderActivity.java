package com.example.ecogrowplanner.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.TimePickerDialog;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.ecogrowplanner.R;


public class FertilizerReminderActivity extends AppCompatActivity {
    private EditText edtPlantSize;
    private TextView txtFertilizerAmount, txtSelectedTime;
    private int selectedHour, selectedMinute;
    private double fertilizerAmount = 0; // Stores calculated fertilizer amount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fertilizer_reminder); // Corrected layout reference

        edtPlantSize = findViewById(R.id.edt_plant_size);
        txtFertilizerAmount = findViewById(R.id.txt_fertilizer_amount);
        txtSelectedTime = findViewById(R.id.txt_selected_time);
        Button btnPickTime = findViewById(R.id.btn_pick_time);
        Button btnSetReminder = findViewById(R.id.btn_set_fertilizer_reminder); // Corrected ID

        // Listen for plant size input to auto-calculate fertilizer amount
        edtPlantSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateFertilizerAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Open Time Picker when "Select Reminder Time" is clicked
        btnPickTime.setOnClickListener(view -> showTimePicker());

        // Set Reminder when "Set Fertilizer Reminder" is clicked
        btnSetReminder.setOnClickListener(view -> setFertilizerReminder());
    }

    // Calculate fertilizer amount based on plant size
    private void calculateFertilizerAmount() {
        String plantSizeText = edtPlantSize.getText().toString().trim();
        if (!plantSizeText.isEmpty()) {
            double plantSize = Double.parseDouble(plantSizeText);
            fertilizerAmount = plantSize * 5; // Example: 5 grams per cm
            txtFertilizerAmount.setText("Fertilizer Required: " + fertilizerAmount + " grams");
        } else {
            txtFertilizerAmount.setText("Fertilizer Required: 0 grams");
            fertilizerAmount = 0;
        }
    }

    // Show Time Picker Dialog
    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {
                    selectedHour = hourOfDay;
                    selectedMinute = minute1;
                    txtSelectedTime.setText(String.format("Selected Time: %02d:%02d", selectedHour, selectedMinute));
                }, hour, minute, true);
        timePickerDialog.show();
    }

    // Set the Fertilizer Reminder
    private void setFertilizerReminder() {
        String plantSizeText = edtPlantSize.getText().toString().trim();

        if (plantSizeText.isEmpty()) {
            Toast.makeText(this, "Please enter plant size!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fertilizerAmount == 0) {
            Toast.makeText(this, "Invalid plant size!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set Alarm for Reminder
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE, selectedMinute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, FertilizerReminderReceiver.class); // Corrected class reference
        intent.putExtra("fertilizerAmount", fertilizerAmount);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, "Reminder set for " + selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
        }
    }
}

