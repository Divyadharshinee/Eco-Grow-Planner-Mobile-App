package com.example.ecogrowplanner.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ecogrowplanner.R;

import org.json.JSONArray;
import org.json.JSONException;
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

public class IdentifyActivity extends AppCompatActivity {
    private static final String API_KEY = "BuYd83sP0U0SIw2GmSuorFA7q8RJkxVwgzFxeMgW6NAOENVVYs"; // Replace with your actual API key
    private static final String API_URL = "https://api.plant.id/v2/identify";

    private ImageView imageView;
    private TextView resultTextView;
    private Button identifyButton;
    private Uri imageUri;
    private File imageFile;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.resultTextView);
        identifyButton = findViewById(R.id.identifyButton);

        imageUri = Uri.parse(getIntent().getStringExtra("image_uri"));
        String imagePath = getIntent().getStringExtra("image_path");
        imageFile = new File(imagePath);

        Glide.with(this).load(imageUri).into(imageView);

        identifyButton.setOnClickListener(v -> {
            resultTextView.setText("Identifying... Please wait.");
            identifyPlant();
        });
    }

    private void identifyPlant() {
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
            jsonObject.put("modifiers", new JSONArray().put("similar_images"));
            jsonObject.put("language", "en");
            jsonObject.put("plant_details", new JSONArray().put("common_names").put("description").put("scientific_name"));

            RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .url(API_URL)
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
                            String commonName = firstSuggestion.getJSONObject("plant_details").getJSONArray("common_names").getString(0);
                            String description = firstSuggestion.getJSONObject("plant_details").getString("description");

                            runOnUiThread(() -> resultTextView.setText("Plant Name: " + commonName + "\n\nDescription: " + description));
                        } else {
                            runOnUiThread(() -> resultTextView.setText("No matching plant found."));
                        }
                    } catch (JSONException e) {
                        runOnUiThread(() -> resultTextView.setText("Error parsing response."));
                    }
                }
            });

        } catch (JSONException e) {
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

