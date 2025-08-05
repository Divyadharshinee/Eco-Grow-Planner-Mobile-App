package com.example.ecogrowplanner;


// Model class for Plants
public class Plant {
    private String name;      // Ensure Firestore field name matches
    private String imageUrl;
    private String climate;
    private String soilType;
    private String plantType;

    // ✅ Empty constructor (REQUIRED for Firestore)
    public Plant() {
    }

    // ✅ Full Constructor
    public Plant(String name, String imageUrl, String climate, String soilType, String plantType) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.climate = climate;
        this.soilType = soilType;
        this.plantType = plantType;
    }

    // ✅ Getter methods (Ensure these match Firestore field names)
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getClimate() {
        return climate;
    }

    public String getSoilType() {
        return soilType;
    }

}

