package com.example.ecogrowplanner.ui.camera;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.ecogrowplanner.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";
    private Button btnIdentify, btnDiagnose;
    private ImageButton btnCapture;
    private boolean isIdentifyMode = true;
    private Uri imageUri;
    private File imageFile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        // Initialize UI Elements
        btnIdentify = view.findViewById(R.id.btn_identify);
        btnDiagnose = view.findViewById(R.id.btn_diagnose);
        btnCapture = view.findViewById(R.id.btn_capture);

        btnIdentify.setEnabled(false); // Default to Identify Mode

        btnIdentify.setOnClickListener(v -> switchMode(true));
        btnDiagnose.setOnClickListener(v -> switchMode(false));

        btnCapture.setOnClickListener(v -> checkCameraPermission());

        return view;
    }

    /** Switch between Identify and Diagnose Mode */
    private void switchMode(boolean identifyMode) {
        isIdentifyMode = identifyMode;
        btnIdentify.setEnabled(!identifyMode);
        btnDiagnose.setEnabled(identifyMode);
        Toast.makeText(getContext(), isIdentifyMode ? "Identify Mode Selected" : "Diagnose Mode Selected", Toast.LENGTH_SHORT).show();
    }

    /** Check Camera Permission Before Opening */
    private void checkCameraPermission() {
        if (!isAdded()) return;

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    /** Request Permission for Camera */
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    openCamera();
                } else {
                    Toast.makeText(getContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    /** Open Camera & Capture Image */
    private void openCamera() {
        if (!isAdded()) return;

        try {
            imageFile = createImageFile();
            if (imageFile == null) {
                Toast.makeText(getContext(), "Error creating image file", Toast.LENGTH_SHORT).show();
                return;
            }

            imageUri = FileProvider.getUriForFile(requireContext(), requireContext().getPackageName() + ".provider", imageFile);
            Log.d(TAG, "Image file: " + imageFile.getAbsolutePath());

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            cameraLauncher.launch(intent);
        } catch (IOException e) {
            Log.e(TAG, "File creation failed", e);
            Toast.makeText(getContext(), "Failed to create file", Toast.LENGTH_SHORT).show();
        }
    }

    /** Capture Image Result */
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (!isAdded()) return;

                if (result.getResultCode() == requireActivity().RESULT_OK && imageUri != null && imageFile != null && imageFile.exists()) {
                    Log.d(TAG, "Captured image: " + imageFile.getAbsolutePath());
                    sendImageToNextActivity();
                } else {
                    Toast.makeText(getContext(), "Image capture failed", Toast.LENGTH_SHORT).show();
                }
            });

    /** Send Image to Next Activity */
    private void sendImageToNextActivity() {
        if (!isAdded() || imageUri == null || imageFile == null || !imageFile.exists()) {
            Toast.makeText(getContext(), "Error: No image available!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getContext(), isIdentifyMode ? IdentifyActivity.class : DiagnoseActivity.class);
        intent.putExtra("image_uri", imageUri.toString());
        intent.putExtra("image_path", imageFile.getAbsolutePath());

        Log.d(TAG, "Sending Image to Activity: " + imageFile.getAbsolutePath());
        startActivity(intent);
    }

    /** Create an Image File */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
}
