package com.example.ecogrowplanner.ui.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecogrowplanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddPost;
    private ImageAdapter imageAdapter;
    private DatabaseReference databaseRef;
    private List<ImageModel> imageList;

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize FloatingActionButton
        fabAddPost = view.findViewById(R.id.fabAddPost);
        if (fabAddPost == null) {
            Log.e("CommunityFragment", "FAB is NULL! Check fragment_community.xml.");
        } else {
            fabAddPost.setOnClickListener(v -> {
                Log.d("CommunityFragment", "FAB clicked! Opening UploadActivity...");
                startActivity(new Intent(getActivity(), UploadActivity.class));
            });
        }

        // Initialize Firebase database
        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        imageList = new ArrayList<>();
        imageAdapter = new ImageAdapter(imageList, getContext());
        recyclerView.setAdapter(imageAdapter);

        // Load images from Firebase
        loadImages();

        return view;
    }

    private void loadImages() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ImageModel image = dataSnapshot.getValue(ImageModel.class);
                    if (image != null && image.getImageUrl() != null && !image.getImageUrl().isEmpty()) {
                        imageList.add(image);
                    } else {
                        Log.e("CommunityFragment", "Invalid image data: " + dataSnapshot);
                    }
                }
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("CommunityFragment", "Error loading images: " + error.getMessage());
            }
        });
    }
}
