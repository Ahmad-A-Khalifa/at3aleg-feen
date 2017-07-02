package com.example.ecss.medicalmapper.storage.sharedPrefrences;

/**
 * Created by khalifa on 30/06/17.
 */

import android.content.Context;

import com.example.ecss.medicalmapper.utility.LanguageManager;


public class PreferencesHelper {

    public static final String APPLICATION_LANGUAGE = "language";

    public synchronized static String getAppLanguage(Context context) {
        return PreferencesHandler.getString(context,
                APPLICATION_LANGUAGE,
                LanguageManager.ARABIC);
    }

    public synchronized static void setAppLanguage(Context context, String language) {
        PreferencesHandler.setString(context, APPLICATION_LANGUAGE, language);
    }
}
