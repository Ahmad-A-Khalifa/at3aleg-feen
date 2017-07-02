package com.example.ecss.medicalmapper.userInterface.fragments;

/**
 * Created by khalifa on 30/06/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.storage.sharedPrefrences.PreferencesHelper;

public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;

    public static SettingsFragment newInstance(){
        return new SettingsFragment();
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_prefs);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            view.setBackgroundColor(
                    ResourcesCompat.getColor(getResources(), android.R.color.white, null));
        }
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnSharedPreferenceChangeListener){
            mSharedPreferenceChangeListener = (OnSharedPreferenceChangeListener) activity;
        }
        else {
            String exceptionMessage = activity.toString()
                    + " must implement OnFragmentInteractionListener";
            throw new RuntimeException(exceptionMessage);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        getPreferenceManager()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key){
            case PreferencesHelper.APPLICATION_LANGUAGE:
                if (mSharedPreferenceChangeListener != null){
                    mSharedPreferenceChangeListener.onLanguageChanged(
                            PreferencesHelper.getAppLanguage(getActivity())
                    );
                }
                break;
        }
    }

    public interface OnSharedPreferenceChangeListener{
        void onLanguageChanged(String newLanguage);
    }
}
