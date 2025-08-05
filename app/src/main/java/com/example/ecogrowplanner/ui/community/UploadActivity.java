package com.example.ecogrowplanner.ui.community;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ecogrowplanner.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText etTitle, etDescription;
    private Button btnSelectImage, btnUpload;
    private ProgressBar progressBar;
    private Uri imageUri;
    private String selectedImagePath;
    private Cloudinary cloudinary;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imageView = findViewById(R.id.imageView);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnUpload = findViewById(R.id.btnUpload);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        // Initialize Cloudinary
        cloudinary = new Cloudinary("cloudinary://281391416621613:OnABnZyQ_Bbl00tKCZZuO3zhtxU@ds04falvc");

        // Initialize Firebase Realtime Database
        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        btnSelectImage.setOnClickListener(v -> pickImageFromGallery());

        btnUpload.setOnClickListener(v -> {
            if (imageUri != null && !etTitle.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty()) {
                new UploadImageTask().execute(selectedImagePath);
            } else {
                Toast.makeText(this, "Please select an image, enter a title, and description", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Pick an image from the gallery
    // Pick an image from the gallery
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    // Handle gallery selection
    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    if (imageUri != null) {
                        selectedImagePath = RealPathUtil.getRealPath(this, imageUri);
                        Picasso.get().load(imageUri).into(imageView);
                    }
                }
            }
    );

    // AsyncTask to upload image to Cloudinary
    private class UploadImageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnUpload.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... filePaths) {
            try {
                File file = new File(filePaths[0]);
                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                return (String) uploadResult.get("secure_url");
            } catch (Exception e) {
                Log.e("Cloudinary Upload", "Upload failed", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String imageUrl) {
            progressBar.setVisibility(View.GONE);
            btnUpload.setEnabled(true);

            if (imageUrl != null) {
                saveDataToFirebase(imageUrl);
            } else {
                Toast.makeText(UploadActivity.this, "Upload Failed!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Save image details to Firebase Realtime Database
    private void saveDataToFirebase(String imageUrl) {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || imageUrl == null || imageUrl.isEmpty()) {
            Toast.makeText(this, "Please enter title, description, and upload an image!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get Firebase Database reference
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        // Generate unique ID for each upload
        String uploadId = databaseRef.push().getKey();

        Map<String, Object> imageData = new HashMap<>();
        imageData.put("title", title);
        imageData.put("description", description);
        imageData.put("imageUrl", imageUrl);

        if (uploadId != null) {
            databaseRef.child(uploadId).setValue(imageData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(UploadActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                        finish(); // ✅ Close UploadActivity after successful upload
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(UploadActivity.this, "Error saving data! Please try again.", Toast.LENGTH_SHORT).show();
                    });
        }
    }


}


