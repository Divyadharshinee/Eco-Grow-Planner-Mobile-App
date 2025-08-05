package com.example.ecogrowplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.ecogrowplanner.ui.camera.CameraFragment;
import com.example.ecogrowplanner.ui.community.CommunityFragment;
import com.example.ecogrowplanner.ui.diagnose.DiagnoseFragment;
import com.example.ecogrowplanner.ui.expert.ExpertFragment;
import com.example.ecogrowplanner.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set up Drawer Toggle with Toolbar
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Ensure Action Bar Home Button is Enabled
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // ✅ Update Navigation Drawer Header with Logged-in User Data
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navEmail = headerView.findViewById(R.id.nav_email);

        // Retrieve the logged-in user details
        Intent intent = getIntent();
        String loggedUsername = intent.getStringExtra("username");
        String loggedEmail = intent.getStringExtra("email");

        if (loggedUsername != null && loggedEmail != null) {
            navUsername.setText(loggedUsername);
            navEmail.setText(loggedEmail);
        }

        // Handle Navigation Drawer Clicks
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_diagnose) {
                selectedFragment = new DiagnoseFragment();
            } else if (id == R.id.nav_camera) {
                selectedFragment = new CameraFragment();
            } else if (id == R.id.nav_community) {
                selectedFragment = new CommunityFragment();
            } else if (id == R.id.nav_expert) {
                selectedFragment = new ExpertFragment();
            } else if (id == R.id.nav_settings) {
                Toast.makeText(MainActivity.this, "Opening Settings...", Toast.LENGTH_SHORT).show();
                Intent setIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(setIntent);
            } else if (id == R.id.nav_logout) {
                Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logoutIntent);
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, selectedFragment)
                        .commit();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Handle Bottom Navigation Clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_diagnose) {
                selectedFragment = new DiagnoseFragment();
            } else if (id == R.id.nav_camera) {
                selectedFragment = new CameraFragment();
            } else if (id == R.id.nav_community) {
                selectedFragment = new CommunityFragment();
            } else if (id == R.id.nav_expert) {
                selectedFragment = new ExpertFragment();
            } else {
                return false;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, selectedFragment)
                    .commit();
            return true;
        });

        // Load HomeFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new HomeFragment())
                    .commit();
        }
    }

    // Handle Drawer Toggle Clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}