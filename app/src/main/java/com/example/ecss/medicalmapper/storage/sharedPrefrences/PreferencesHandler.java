package com.example.ecss.medicalmapper.storage.sharedPrefrences;

/**
 * Created by khalifa on 30/06/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PreferencesHandler {

    public static void setString(Context context, String key, String value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt(key, value);
        editor.apply();
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putLong(key, value);
        editor.apply();
    }

    public static void setStringArray(Context context, String key, ArrayList<String> stringArray) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putStringSet(key, new TreeSet<>(stringArray));
        editor.apply();
    }

    public static void setStringSet(Context context, String key, Set<String> stringSet) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putStringSet(key, new HashSet<>(stringSet));
        editor.apply();
    }

    public static long getLong(Context context, String key, long default_value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        return sp.getLong(key, default_value);
    }

    public static boolean getBoolean(Context context, String key, boolean default_value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        return sp.getBoolean(key, default_value);
    }

    public static String getString(Context context, String key, String default_value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        return sp.getString(key, default_value);
    }

    public static int getInt(Context context, String key, int default_value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        return sp.getInt(key, default_value);
    }

    public static Set<String> getStringSet(Context context, String key, Set<String> defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        return sp.getStringSet(key, defaultValue);
    }

    public static ArrayList<String> getStringArray(Context context,
                                                   String key,
                                                   Set<String> defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        Set<String> stringSet = sp.getStringSet(key, defaultValue);
        if (stringSet != null)
            return new ArrayList<>(stringSet);
        else
            return new ArrayList<>();
    }
}