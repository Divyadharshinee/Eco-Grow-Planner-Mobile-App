package com.example.ecogrowplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private EditText locationField, sizeField, experienceField;
    private Button saveProfileButton;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Views
        locationField = findViewById(R.id.locationField);
        sizeField = findViewById(R.id.sizeField);
        experienceField = findViewById(R.id.experienceField);
        saveProfileButton = findViewById(R.id.saveProfileButton);

        // Get user data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }

        saveProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });
    }

    private void saveProfileData() {
        String location = locationField.getText().toString();
        String size = sizeField.getText().toString();
        String experience = experienceField.getText().toString();

        if (location.isEmpty() || size.isEmpty() || experience.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save profile data to Firebase
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("profiles").child(username);

        ProfileData profileData = new ProfileData(location, size, experience);
        profileRef.setValue(profileData);

        Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();

        // Navigate to Main Activity (Plant Selection)
        Intent intent = new Intent(ProfileActivity.this, plantselection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();

    }

    // Profile data class
    public static class ProfileData {
        private String location;
        private String size;
        private String experience;

        public ProfileData() {
            // Required empty constructor for Firebase
        }

        public ProfileData(String location, String size, String experience) {
            this.location = location;
            this.size = size;
            this.experience = experience;
        }

        // Getters and setters
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }

        public String getSize() { return size; }
        public void setSize(String size) { this.size = size; }

        public String getExperience() { return experience; }
        public void setExperience(String experience) { this.experience = experience; }
    }
}