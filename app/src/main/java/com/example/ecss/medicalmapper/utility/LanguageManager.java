package com.example.ecss.medicalmapper.utility;

/**
 * Created by khalifa on 30/06/17.
 */

import android.app.Activity;
import android.content.res.Configuration;

import java.util.Locale;

public class LanguageManager {
    public static final String ENGLISH = "en";
    public static final String ARABIC = "ar";

    @SuppressWarnings("deprecation")
    public static void setLanguage(Activity activity, String language){
        if (!language.equals(ARABIC) && !language.equals(ENGLISH)){
            return;
        }
        Locale locale = new Locale(language);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        activity.getResources().updateConfiguration(
                configuration,
                activity.getResources().getDisplayMetrics());
        Locale.setDefault(locale);
        activity.recreate();
    }
}