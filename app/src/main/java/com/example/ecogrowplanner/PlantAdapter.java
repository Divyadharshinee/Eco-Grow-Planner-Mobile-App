package com.example.ecogrowplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecogrowplanner.Plant;
import com.example.ecogrowplanner.R;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {
    private List<Plant> plantList;
    private Context context;

    public PlantAdapter(List<Plant> plantList, Context context) {
        this.plantList = plantList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);

        // Check if plant name is null or empty before setting it
        if (plant.getName() != null && !plant.getName().trim().isEmpty()) {
            holder.textViewName.setText(plant.getName());
            holder.textViewName.setVisibility(View.VISIBLE); // Ensure it's visible
        } else {
            holder.textViewName.setText("Unknown Plant");
            holder.textViewName.setVisibility(View.VISIBLE);
        }

        // Load image using Glide
        Glide.with(context)
                .load(plant.getImageUrl())
                .placeholder(R.drawable.logo) // Use a placeholder image
                .error(R.drawable.logo) // Use an error image if load fails
                .into(holder.imageViewPlant);
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageViewPlant;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewPlantName);
            imageViewPlant = itemView.findViewById(R.id.imageViewPlant);
        }
    }

    // Update list method
    public void updateList(List<Plant> newPlantList) {
        plantList.clear();
        plantList.addAll(newPlantList);
        notifyDataSetChanged();
    }
}
