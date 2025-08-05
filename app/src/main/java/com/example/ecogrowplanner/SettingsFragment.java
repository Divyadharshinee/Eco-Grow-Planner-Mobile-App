package com.example.ecogrowplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import java.util.Locale;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey);

        // Handle Language Selection
        ListPreference languagePref = findPreference("language");
        if (languagePref != null) {
            languagePref.setOnPreferenceChangeListener((preference, newValue) -> {
                setAppLocale(newValue.toString());
                return true;
            });
        }

        // Handle Theme Selection
        ListPreference themePref = findPreference("theme");
        if (themePref != null) {
            themePref.setOnPreferenceChangeListener((preference, newValue) -> {
                applyTheme(newValue.toString());
                return true;
            });
        }

        // Share App
        Preference shareAppPref = findPreference("share_app");
        if (shareAppPref != null) {
            shareAppPref.setOnPreferenceClickListener(preference -> {
                shareApp();
                return true;
            });
        }

        // Terms of Service
        Preference termsPref = findPreference("terms_of_service");
        if (termsPref != null) {
            termsPref.setOnPreferenceClickListener(preference -> {
                openWebPage("https://yourwebsite.com/terms");
                return true;
            });
        }

        // Privacy Policy
        Preference privacyPref = findPreference("privacy_policy");
        if (privacyPref != null) {
            privacyPref.setOnPreferenceClickListener(preference -> {
                openWebPage("https://yourwebsite.com/privacy");
                return true;
            });
        }
    }

    // Change App Language
    private void setAppLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getActivity().getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(config, dm);

        // Save language preference
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", getActivity().MODE_PRIVATE).edit();
        editor.putString("language", languageCode);
        editor.apply();

        // Restart app to apply changes
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

    // Apply Theme
    private void applyTheme(String themeValue) {
        if ("Dark".equals(themeValue)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if ("Light".equals(themeValue)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        // Save theme preference
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", getActivity().MODE_PRIVATE).edit();
        editor.putString("theme", themeValue);
        editor.apply();
    }

    // Share App
    private void shareApp() {
        String shareText = "Check out this amazing app: https://play.google.com/store/apps/details?id=" + getActivity().getPackageName();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    // Open Web Page
    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
