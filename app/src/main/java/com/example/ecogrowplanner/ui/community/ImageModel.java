package com.example.ecogrowplanner.ui.community;

public class ImageModel {
    private String title;
    private String description;
    private String imageUrl;

    public ImageModel() {
        // Empty constructor required for Firestore
    }

    public ImageModel(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

