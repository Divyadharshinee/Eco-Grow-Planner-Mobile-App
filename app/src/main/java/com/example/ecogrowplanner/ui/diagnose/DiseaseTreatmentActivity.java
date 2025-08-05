package com.example.ecogrowplanner.ui.diagnose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ecogrowplanner.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

public class DiseaseTreatmentActivity extends AppCompatActivity {
    private static final String TAG = "CropHealthApp";
    private static final String API_KEY = ""; // Replace with your actual API key
    private static final String API_URL = "";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imageView;
    private TextView resultText;
    private Button diagnoseButton;
    private Button takePictureButton;
    private Uri currentPhotoUri;
    private File imageFile;
    private final OkHttpClient client = new OkHttpClient();
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_treatment);

        // Initialize UI elements
        imageView = findViewById(R.id.imagePreview);
        resultText = findViewById(R.id.resultText);
        diagnoseButton = findViewById(R.id.diagnoseButton);
        takePictureButton = findViewById(R.id.takePictureButton);

        // Get image URI from intent
        String imageUriString = getIntent().getStringExtra("image_uri");
        String imagePath = getIntent().getStringExtra("image_path");

        if (imageUriString != null && imagePath != null) {
            currentPhotoUri = Uri.parse(imageUriString);
            imageFile = new File(imagePath);
            imageView.setImageURI(currentPhotoUri);
            diagnoseButton.setEnabled(true); // Enable button after setting image
        } else {
            // No image received, but still allow using the camera
            diagnoseButton.setEnabled(false);
        }

        diagnoseButton.setOnClickListener(v -> identifyImage());

        // Set up camera button
        takePictureButton.setOnClickListener(v -> dispatchTakePictureIntent());
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Error creating image file", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error creating image file", ex);
                return;
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                try {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            getApplicationContext().getPackageName() + ".provider",
                            photoFile);
                    currentPhotoUri = photoURI;
                    imageFile = photoFile;
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (Exception e) {
                    Log.e(TAG, "Error creating file provider URI", e);
                    Toast.makeText(this, "Error with camera: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "No camera app available", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Make sure the directory exists
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                // The image was saved to the file we created
                if (currentPhotoUri != null && imageFile != null && imageFile.exists()) {
                    try {
                        Log.d(TAG, "Image captured successfully at: " + currentPhotoPath);

                        // Refresh the media scanner to show the new image in gallery
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        mediaScanIntent.setData(currentPhotoUri);
                        sendBroadcast(mediaScanIntent);

                        // Display image and enable diagnose button
                        imageView.setImageURI(null); // Clear previous image
                        imageView.setImageURI(currentPhotoUri); // Set new image
                        diagnoseButton.setEnabled(true);
                    } catch (Exception e) {
                        Log.e(TAG, "Error displaying captured image", e);
                        Toast.makeText(this, "Error displaying image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Image file not found or URI is null");
                    Toast.makeText(this, "Error: Image not found after capture", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
                Toast.makeText(this, "Image capture cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // Image capture failed
                Toast.makeText(this, "Image capture failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void identifyImage() {
        if (currentPhotoUri == null) {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
            return;
        }

        resultText.setText("Analyzing image...");

        try {
            String encodedImage = encodeImage(currentPhotoUri);
            if (encodedImage == null) {
                Toast.makeText(this, "Failed to encode image", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONObject payload = new JSONObject();
            JSONArray imagesArray = new JSONArray();
            imagesArray.put(encodedImage);
            payload.put("images", imagesArray);
            payload.put("latitude", 49.1951239);
            payload.put("longitude", 16.6077111);
            payload.put("similar_images", true);

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(payload.toString(), JSON);

            String detailsParams = "common_names,gbif_id,eppo_code,type,taxonomy,wiki_url,wiki_description,description,treatment,symptoms,severity,spreading";

            Request request = new Request.Builder()
                    .url(API_URL + "?details=" + detailsParams)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Api-Key", API_KEY)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> {
                        Toast.makeText(DiseaseTreatmentActivity.this, "Request failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        resultText.setText("Identification failed");
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    Log.d(TAG, "API Response: " + responseBody); // Print full response

                    runOnUiThread(() -> {
                        try {
                            parseAndDisplayResults(responseBody);
                        } catch (JSONException e) {
                            Toast.makeText(DiseaseTreatmentActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            resultText.setText("Error parsing identification results");
                            Log.e(TAG, "Parsing Error", e);
                        }
                    });
                }
            });

        } catch (JSONException | IOException e) {
            Toast.makeText(this, "Error preparing request: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            resultText.setText("Identification failed");
        }
    }

    private String encodeImage(Uri imageUri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(imageUri);
        if (inputStream == null) return null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();

        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void parseAndDisplayResults(String jsonResponse) throws JSONException {
        JSONObject response = new JSONObject(jsonResponse);
        StringBuilder result = new StringBuilder("DISEASE DETAILS:\n");

        if (response.has("result")) {
            JSONObject resultObj = response.getJSONObject("result");

            if (resultObj.has("disease") && resultObj.getJSONObject("disease").has("suggestions")) {
                JSONArray diseaseSuggestions = resultObj.getJSONObject("disease").getJSONArray("suggestions");

                for (int i = 0; i < diseaseSuggestions.length(); i++) {
                    JSONObject suggestion = diseaseSuggestions.getJSONObject(i);
                    result.append("Disease: ").append(suggestion.optString("name", "Unknown")).append("\n");

                    result.append("Scientific Name: ").append(suggestion.optString("scientific_name", "Unknown")).append("\n");
                    result.append("Probability: ").append(String.format("%.2f%%", suggestion.optDouble("probability", 0) * 100)).append("\n");

                    if (suggestion.has("details")) {
                        JSONObject details = suggestion.getJSONObject("details");

                        result.append("Type: ").append(details.optString("type", "N/A")).append("\n");
                        result.append("EPPO Code: ").append(details.optString("eppo_code", "N/A")).append("\n");
                        result.append("GBIF ID: ").append(details.optInt("gbif_id", 0)).append("\n");
                        result.append("Wiki URL: ").append(details.optString("wiki_url", "N/A")).append("\n");
                        result.append("Description: ").append(details.optString("description", "No description available")).append("\n");

                        if (details.has("treatment")) {
                            JSONObject treatment = details.getJSONObject("treatment");
                            result.append("Treatment:\n");
                            result.append("  Biological: ").append(treatment.optString("biological", "N/A")).append("\n");
                            result.append("  Chemical: ").append(treatment.optString("chemical", "N/A")).append("\n");
                            result.append("  Prevention: ").append(treatment.optString("prevention", "N/A")).append("\n");
                        }
                    }
                    result.append("\n");
                }
            }
        }

        if (result.length() == 0) {
            resultText.setText("No disease detected.");
        } else {
            resultText.setText(result.toString());
        }
    }
}