package com.example.ecogrowplanner.ui.diagnose;



import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.ecogrowplanner.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiseaseIdentificationActivity extends AppCompatActivity {
    private static final String TAG = "DiagnoseDiseaseActivity";
    private static final String API_KEY = "YcVATNv08nvrknrgntkRlP97XFj30qP5dZrL3SJCNUX5S4evdk"; // Store securely
    private static final String API_URL = "https://crop.kindwise.com/api/v1/identification";

    private ImageView imagePreview;
    private TextView resultText;
    private Button captureButton, identifyButton;
    private Uri currentPhotoUri;
    private File imageFile;
    private final OkHttpClient client = new OkHttpClient();

    // Camera result launcher
    private final ActivityResultLauncher<Uri> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.TakePicture(),
            result -> {
                if (result) {
                    // Ensure old image is cleared
                    imagePreview.setImageURI(null);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), currentPhotoUri);
                        imagePreview.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
                    }


                    // Force media scanner to update the file
                    getContentResolver().notifyChange(currentPhotoUri, null);

                    identifyButton.setEnabled(true);
                } else {
                    Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_identification);

        imagePreview = findViewById(R.id.imageView);
        resultText = findViewById(R.id.resultTextView);
        captureButton = findViewById(R.id.captureButton);
        identifyButton = findViewById(R.id.identifyButton);

        identifyButton.setEnabled(false);

        captureButton.setOnClickListener(v -> openCamera());
        identifyButton.setOnClickListener(v -> identifyImage());
    }

    private void openCamera() {
        try {
            imageFile = createImageFile();
            currentPhotoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", imageFile);
            takePictureLauncher.launch(currentPhotoUri);
        } catch (IOException e) {
            Toast.makeText(this, "Failed to create image file", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    private void identifyImage() {
        if (currentPhotoUri == null) {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
            return;
        }

        resultText.setText("Analyzing image...");
        identifyButton.setEnabled(false);

        try {
            String encodedImage = encodeImage(currentPhotoUri);
            if (encodedImage == null) {
                resultText.setText("Failed to encode image");
                identifyButton.setEnabled(true);
                return;
            }

            JSONObject payload = new JSONObject();
            JSONArray imagesArray = new JSONArray();
            imagesArray.put(encodedImage);
            payload.put("images", imagesArray);
            payload.put("latitude", 49.1951239);
            payload.put("longitude", 16.6077111);
            payload.put("similar_images", true);

            RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Api-Key", API_KEY)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(() -> {
                        resultText.setText("Identification failed: " + e.getMessage());
                        identifyButton.setEnabled(true);
                    });
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String responseBody = response.body().string();

                    runOnUiThread(() -> {
                        try {
                            parseAndDisplayResults(responseBody);
                        } catch (JSONException e) {
                            resultText.setText("Error parsing results");
                        }
                        identifyButton.setEnabled(true);
                    });
                }
            });

        } catch (JSONException | IOException e) {
            resultText.setText("Error preparing request: " + e.getMessage());
            identifyButton.setEnabled(true);
        }
    }

    private String encodeImage(Uri imageUri) throws IOException {
        try (InputStream inputStream = getContentResolver().openInputStream(imageUri)) {
            if (inputStream == null) return null;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        }
    }

    private void parseAndDisplayResults(String jsonResponse) throws JSONException {
        JSONObject response = new JSONObject(jsonResponse);
        StringBuilder result = new StringBuilder();

        if (response.has("result")) {
            JSONObject resultObj = response.getJSONObject("result");

            if (resultObj.has("is_plant")) {
                boolean isPlant = resultObj.getJSONObject("is_plant").getBoolean("binary");
                result.append("Is Plant: ").append(isPlant ? "Yes" : "No").append("\n\n");
            }

            if (resultObj.has("disease") && resultObj.getJSONObject("disease").has("suggestions")) {
                JSONArray diseaseSuggestions = resultObj.getJSONObject("disease").getJSONArray("suggestions");
                for (int i = 0; i < diseaseSuggestions.length(); i++) {
                    JSONObject suggestion = diseaseSuggestions.getJSONObject(i);
                    result.append(suggestion.getString("name")).append("\n");

                    if (suggestion.has("scientific_name")) {
                        result.append("Scientific Name: ").append(suggestion.getString("scientific_name")).append("\n");
                    }

                    result.append("Probability: ").append(String.format("%.2f%%", suggestion.getDouble("probability") * 100)).append("\n\n");
                }
            }
        }

        resultText.setText(result.length() > 0 ? result.toString() : "No identification results found");
    }
}