package com.example.ecogrowplanner.ui.camera;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiagnoseActivity extends AppCompatActivity {
    private static final String TAG = "CropHealthApp";
    private static final String API_KEY = "YcVATNv08nvrknrgntkRlP97XFj30qP5dZrL3SJCNUX5S4evdk"; // Replace with your actual API key
    private static final String API_URL = "https://crop.kindwise.com/api/v1/identification";

    private ImageView imageView;
    private TextView resultText;
    private Button diagnoseButton;
    private Uri currentPhotoUri;
    private File imageFile;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);

        // Initialize UI elements
        imageView = findViewById(R.id.imagePreview);
        resultText = findViewById(R.id.resultText);
        diagnoseButton = findViewById(R.id.diagnoseButton);

        // Get image URI from intent
        String imageUriString = getIntent().getStringExtra("image_uri");
        String imagePath = getIntent().getStringExtra("image_path");

        if (imageUriString != null && imagePath != null) {
            currentPhotoUri = Uri.parse(imageUriString);
            imageFile = new File(imagePath);
            imageView.setImageURI(currentPhotoUri);
            diagnoseButton.setEnabled(true); // Enable button after setting image
        } else {
            Toast.makeText(this, "Error: No image received!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        diagnoseButton.setOnClickListener(v -> identifyImage());
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
                        Toast.makeText(DiagnoseActivity.this, "Request failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(DiagnoseActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

