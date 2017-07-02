package com.example.ecss.medicalmapper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;
import com.example.ecss.medicalmapper.userInterface.fragments.SettingsFragment;
import com.example.ecss.medicalmapper.utility.LanguageManager;

public class SettingsActivity extends PreferenceActivity implements SettingsFragment.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, SettingsFragment.newInstance()).commit();
    }

    public static void startActivity(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    @Override
    public void onLanguageChanged(String newLanguage) {
        LanguageManager.setLanguage(this, newLanguage);
    }

    @Override
    public void onBackPressed() {
        HomeScreenActivity.startActivity(this);
        finish();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        private void bindPreferenceSummaryToValue(Preference preference) {
            // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(this);

            // Trigger the listener immediately with the preference's
            // current value.
            onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.settings_language_key)));
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list (since they have separate labels/values).
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    preference.setSummary(listPreference.getEntries()[prefIndex]);
                }
            }
            return true;
        }
    }
}
