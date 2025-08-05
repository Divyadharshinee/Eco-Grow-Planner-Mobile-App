package com.example.ecogrowplanner.ui.diagnose;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.ecogrowplanner.R;

public class DiagnoseFragment extends Fragment {

    private ImageView btnIdentifyDisease, btnTreatment;
    private ImageView imgChlorosis, imgBrownTips, imgMoldMildew, imgrootrot , imgwilting, imgsun;
    private ImageView imgWateringTips, imgFertilizingTips, imgSunlightTips, imgsoil, imgpest, imghumidity;
    private ImageView imgSoilPrep, imgCropRotation, imgComposting, imggermination,imgpollinators, imggorganic;

    public DiagnoseFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnose, container, false);

        // Diagnose Section
        btnIdentifyDisease = view.findViewById(R.id.btn_identify_disease);
        btnTreatment = view.findViewById(R.id.btn_treatment);

        // Common Problems Section
        imgChlorosis = view.findViewById(R.id.img_chlorosis);
        imgBrownTips = view.findViewById(R.id.img_brown_tips);
        imgMoldMildew = view.findViewById(R.id.img_mold_mildew);
        imgrootrot = view.findViewById(R.id.img_rootrot);
        imgwilting = view.findViewById(R.id.img_wiltingleaves);
        imgsun = view.findViewById(R.id.img_sunburned);

        // Plant Care Tips Section
        imgWateringTips = view.findViewById(R.id.img_watering_tips);
        imgFertilizingTips = view.findViewById(R.id.img_fertilizing_tips);
        imgSunlightTips = view.findViewById(R.id.img_sunlight_tips);
        imgsoil = view.findViewById(R.id.img_soiltips);
        imgpest = view.findViewById(R.id.img_pestcontroltips);
        imghumidity = view.findViewById(R.id.img_airandhumiditytips);

        // Cultivation Tips Section
        imgSoilPrep = view.findViewById(R.id.img_soil_prep);
        imgCropRotation = view.findViewById(R.id.img_crop_rotation);
        imgComposting = view.findViewById(R.id.img_composting);
        imggermination = view.findViewById(R.id.img_seedStartingGerminationTips);
        imgpollinators = view.findViewById(R.id.img_pollinators);
        imggorganic = view.findViewById(R.id.img_organic);

        // Set click listeners for Diagnose section
        btnIdentifyDisease.setOnClickListener(v -> startNewActivity(DiseaseIdentificationActivity.class));
        btnTreatment.setOnClickListener(v -> startNewActivity(DiseaseTreatmentActivity.class));

        // Set click listeners for Common Problems section
        imgChlorosis.setOnClickListener(v -> openCommonProblemsActivity("Chlorosis"));
        imgBrownTips.setOnClickListener(v -> openCommonProblemsActivity("Brown Tips"));
        imgMoldMildew.setOnClickListener(v -> openCommonProblemsActivity("Mold and Mildew"));
        imgrootrot.setOnClickListener(v -> openCommonProblemsActivity("Root Rot"));
        imgwilting.setOnClickListener(v -> openCommonProblemsActivity("Wilting Leaves"));
        imgsun.setOnClickListener(v -> openCommonProblemsActivity("Sunburned Leaves"));

        // Set click listeners for Plant Care Tips section
        imgWateringTips.setOnClickListener(v -> openPlantCareTipsActivity("Watering Tips"));
        imgFertilizingTips.setOnClickListener(v -> openPlantCareTipsActivity("Fertilizing Tips"));
        imgSunlightTips.setOnClickListener(v -> openPlantCareTipsActivity("Sunlight Tips"));
        imgsoil.setOnClickListener(v -> openPlantCareTipsActivity("Soil Tips"));
        imgpest.setOnClickListener(v -> openPlantCareTipsActivity("pest control tips"));
        imghumidity.setOnClickListener(v -> openPlantCareTipsActivity("air and humidity tips"));


        // Set click listeners for Cultivation Tips section
        imgSoilPrep.setOnClickListener(v -> openCultivationTipsActivity("Soil Preparation"));
        imgCropRotation.setOnClickListener(v -> openCultivationTipsActivity("Crop Rotation"));
        imgComposting.setOnClickListener(v -> openCultivationTipsActivity("Composting"));
        imggermination.setOnClickListener(v -> openCultivationTipsActivity("Germination Tips"));
        imgpollinators.setOnClickListener(v -> openCultivationTipsActivity("Attracting Pollinators"));
        imggorganic.setOnClickListener(v -> openCultivationTipsActivity("Organic Fertilization Techniques"));

        return view;
    }

    // Methods to handle click events and open corresponding activities
    private void openCommonProblemsActivity(String problemName) {
        Intent intent = new Intent(getActivity(), CommonProblemsActivity.class);
        intent.putExtra("PROBLEM_NAME", problemName); // FIXED KEY
        startActivity(intent);
    }

    private void openPlantCareTipsActivity(String tipName) {
        Intent intent = new Intent(getActivity(), PlantCareTipsActivity.class);
        intent.putExtra("TIP_NAME", tipName);
        startActivity(intent);
    }

    private void openCultivationTipsActivity(String tipName) {
        Intent intent = new Intent(getActivity(), CultivationTipsActivity.class);
        intent.putExtra("TIP_NAME", tipName);
        startActivity(intent);
    }

    private void startNewActivity(Class<?> activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }
}

