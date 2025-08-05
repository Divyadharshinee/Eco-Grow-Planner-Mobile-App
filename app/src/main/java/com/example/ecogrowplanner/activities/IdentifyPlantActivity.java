package com.example.ecogrowplanner.activities;





import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecogrowplanner.R;

public class IdentifyPlantActivity extends AppCompatActivity { // ✅ Extend AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_plant); // ✅ Ensure XML exists
    }
}

