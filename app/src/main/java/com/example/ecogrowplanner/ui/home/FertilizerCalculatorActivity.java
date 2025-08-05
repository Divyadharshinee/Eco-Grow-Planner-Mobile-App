package com.example.ecogrowplanner.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecogrowplanner.R;

public class FertilizerCalculatorActivity extends AppCompatActivity {
    private EditText edtPlantSize;
    private TextView txtFertilizerAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fertilizer_calculator); // Updated layout reference

        edtPlantSize = findViewById(R.id.edt_plant_size);
        txtFertilizerAmount = findViewById(R.id.txt_fertilizer_amount);

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
    }

    // Calculate fertilizer amount based on plant size
    private void calculateFertilizerAmount() {
        String plantSizeText = edtPlantSize.getText().toString().trim();
        if (!plantSizeText.isEmpty()) {
            double plantSize = Double.parseDouble(plantSizeText);
            double fertilizerAmount = plantSize * 5; // Example: 5 grams per cm
            txtFertilizerAmount.setText("Fertilizer Required: " + fertilizerAmount + " grams");
        } else {
            txtFertilizerAmount.setText("Fertilizer Required: 0 grams");
        }
    }
}



