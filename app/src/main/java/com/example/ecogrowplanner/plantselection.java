package com.example.ecogrowplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class plantselection extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlantAdapter adapter;
    private List<Plant> plantList = new ArrayList<>();
    private FirebaseFirestore db;
    private DatabaseReference databaseRef;
    private Button btnFilter;
    private Spinner spinnerClimate, spinnerSoil, spinnerPlantType;
    private TextView txtFilteredPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        recyclerView = findViewById(R.id.recyclerViewPlants);
        btnFilter = findViewById(R.id.btnFilter);
        spinnerClimate = findViewById(R.id.spinnerClimate);
        spinnerSoil = findViewById(R.id.spinnerSoil);
        spinnerPlantType = findViewById(R.id.spinnerPlantType);
        txtFilteredPlants = findViewById(R.id.txtFilteredPlants);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlantAdapter(plantList, this);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("filtered_plants");

        loadPlants(); // Load all plants initially
        setupSpinners(); // Populate Spinners

        // Filter button click listener
        btnFilter.setOnClickListener(v -> {
            String selectedClimate = spinnerClimate.getSelectedItem().toString();
            String selectedSoil = spinnerSoil.getSelectedItem().toString();
            String selectedType = spinnerPlantType.getSelectedItem().toString();
            filterPlants(selectedClimate, selectedSoil, selectedType);
        });
    }

    private void setupSpinners() {
        String[] climates = {"Climate", "Tropical", "Temperate", "Desert", "Mediterranean"};
        String[] soils = {"Soil Type", "Loamy", "Sandy", "Clay", "Peaty", "Saline"};
        String[] plantTypes = {"Plant Type", "Herb", "Shrub", "Tree", "Climber", "Creeper"};

        setupSpinner(spinnerClimate, climates);
        setupSpinner(spinnerSoil, soils);
        setupSpinner(spinnerPlantType, plantTypes);
    }

    private void setupSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void loadPlants() {
        db.collection("plants").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                plantList.clear();
                for (DocumentSnapshot doc : task.getResult()) {
                    Plant plant = doc.toObject(Plant.class);
                    plantList.add(plant);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(plantselection.this, "Failed to load plants", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterPlants(String selectedClimate, String selectedSoil, String selectedType) {
        Query query = db.collection("plants");

        if (!selectedClimate.equalsIgnoreCase("All")) {
            query = query.whereEqualTo("climate", selectedClimate);
        }
        if (!selectedSoil.equalsIgnoreCase("All")) {
            query = query.whereEqualTo("soilType", selectedSoil);
        }
        if (!selectedType.equalsIgnoreCase("All")) {
            query = query.whereEqualTo("plantType", selectedType);
        }

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Plant> filteredList = new ArrayList<>();
                StringBuilder plantNames = new StringBuilder("Filtered Plants: ");

                for (DocumentSnapshot doc : task.getResult()) {
                    Plant plant = doc.toObject(Plant.class);
                    if (plant != null) {
                        filteredList.add(plant);
                        plantNames.append(plant.getName()).append(", ");
                    }
                }

                adapter.updateList(filteredList); // Update RecyclerView
                saveFilteredPlantsToRealtimeDatabase(filteredList); // Save to Realtime Database

                runOnUiThread(() -> {
                    if (filteredList.isEmpty()) {
                        txtFilteredPlants.setText("No plants found");
                    } else {
                        txtFilteredPlants.setText(plantNames.toString().replaceAll(", $", ""));
                    }
                });

                Toast.makeText(plantselection.this, plantNames.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(plantselection.this, "Filter failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveFilteredPlantsToRealtimeDatabase(List<Plant> filteredPlants) {
        // ✅ Clear previous filtered data before adding new filtered plants
        databaseRef.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (filteredPlants.isEmpty()) {
                    Toast.makeText(plantselection.this, "No plants found", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ✅ Track completed database operations
                int totalPlants = filteredPlants.size();
                int[] savedCount = {0};

                for (Plant plant : filteredPlants) {
                    String key = databaseRef.push().getKey(); // Unique ID for each plant
                    databaseRef.child(key).setValue(plant).addOnCompleteListener(saveTask -> {
                        if (saveTask.isSuccessful()) {
                            savedCount[0]++; // Increase count on successful save

                            // ✅ Move to the next page ONLY when all plants are saved
                            if (savedCount[0] == totalPlants) {
                                Toast.makeText(plantselection.this, "Filtered plants saved!", Toast.LENGTH_SHORT).show();

                                // ✅ Wait for 5 seconds before moving to the next activity
                                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                    Intent intent = new Intent(plantselection.this, MainActivity.class);
                                    startActivity(intent);
                                    finish(); // Close the current activity
                                }, 3000); // 5000ms = 5 seconds
                            }
                        } else {
                            Toast.makeText(plantselection.this, "Failed to save some plants", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Toast.makeText(plantselection.this, "Failed to clear previous data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
