package com.example.ecogrowplanner.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.ecogrowplanner.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PlantActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final String API_KEY = "BuYd83sP0U0SIw2GmSuorFA7q8RJkxVwgzFxeMgW6NAOENVVYs"; // Replace with your actual API key
    private static final String API_URL = "https://api.plant.id/v2/identify"; // Updated API endpoint

    private ImageView imageView;
    private TextView resultTextView;
    private Uri imageUri;
    private File imageFile;

    // Camera launcher
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Glide.with(this)
                                .load(imageUri)
                                .skipMemoryCache(true) // Skip memory cache
                                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE) // Skip disk cache
                                .into(imageView);

                    }

            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.resultTextView);
        Button captureButton = findViewById(R.id.captureButton);
        Button identifyButton = findViewById(R.id.identifyButton);

        captureButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            } else {
                openCamera();
            }
        });

        identifyButton.setOnClickListener(v -> {
            if (imageFile != null && imageFile.exists()) {
                identifyPlantDisease();
            } else {
                resultTextView.setText("Please capture an image first.");
            }
        });
    }

    private void openCamera() {
        imageFile = new File(getExternalFilesDir("Pictures"), "plant_image.jpg");

        // Delete the old image if it exists
        if (imageFile.exists()) {
            imageFile.delete();
        }

        imageUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", imageFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraLauncher.launch(intent);
    }

    private void identifyPlantDisease() {
        OkHttpClient client = new OkHttpClient();
        String base64Image = encodeImageToBase64(imageFile);

        if (base64Image == null) {
            runOnUiThread(() -> resultTextView.setText("Error processing image."));
            return;
        }

        try {
            JSONArray imagesArray = new JSONArray();
            imagesArray.put(base64Image);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("images", imagesArray);
            jsonObject.put("modifiers", new JSONArray().put("similar_images").put("health_all")); // Updated modifiers
            jsonObject.put("language", "en");
            jsonObject.put("plant_details", new JSONArray().put("common_names").put("description").put("treatment")); // Updated request params

            RequestBody body = RequestBody.create(
                    jsonObject.toString(),
                    MediaType.parse("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(API_URL) // Updated API URL
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Api-Key", API_KEY)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> resultTextView.setText("Error: " + e.getMessage()));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        runOnUiThread(() -> resultTextView.setText("API Error: " + response.message()));
                        return;
                    }

                    String responseData = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(responseData);
                        JSONArray suggestions = jsonResponse.getJSONArray("suggestions");

                        if (suggestions.length() > 0) {
                            JSONObject firstSuggestion = suggestions.getJSONObject(0);
                            JSONObject plantDetails = firstSuggestion.getJSONObject("plant_details");

                            String commonName = plantDetails.has("common_names") ?
                                    plantDetails.getJSONArray("common_names").getString(0) :
                                    "Unknown Plant";

                            String description = plantDetails.has("description") ?
                                    plantDetails.getString("description") :
                                    "No description available.";

                            String treatment = plantDetails.has("treatment") ?
                                    plantDetails.getString("treatment") :
                                    "No treatment information available.";

                            runOnUiThread(() -> resultTextView.setText(
                                    "Common Name: " + commonName + "\n\n" +
                                            "Description: " + description + "\n\n" +
                                            "Treatment: " + treatment
                            ));
                        } else {
                            runOnUiThread(() -> resultTextView.setText("No plant disease identified."));
                        }
                    } catch (Exception e) {
                        runOnUiThread(() -> resultTextView.setText("Error parsing response"));
                    }
                }
            });

        } catch (Exception e) {
            runOnUiThread(() -> resultTextView.setText("Error: " + e.getMessage()));
        }
    }

    private String encodeImageToBase64(File imageFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        return Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);
    }
}
