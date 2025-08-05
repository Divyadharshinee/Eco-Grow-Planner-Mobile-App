package com.example.ecogrowplanner.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecogrowplanner.R;

public class WaterCalculatorActivity extends AppCompatActivity {
    private EditText edtPlantSize;
    private TextView txtWaterAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_calculator); // Updated layout reference

        edtPlantSize = findViewById(R.id.edt_plant_size);
        txtWaterAmount = findViewById(R.id.txt_water_amount);

        // Listen for plant size input to auto-calculate water amount
        edtPlantSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateWaterAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Calculate water amount based on plant size
    private void calculateWaterAmount() {
        String plantSizeText = edtPlantSize.getText().toString().trim();
        if (!plantSizeText.isEmpty()) {
            double plantSize = Double.parseDouble(plantSizeText);
            double waterAmount = plantSize * 10; // Example: 10 ml per cm
            txtWaterAmount.setText("Water Required: " + waterAmount + " ml");
        } else {
            txtWaterAmount.setText("Water Required: 0 ml");
        }
    }
}

