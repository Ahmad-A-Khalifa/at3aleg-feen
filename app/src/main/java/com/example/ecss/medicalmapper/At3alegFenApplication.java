package com.example.ecss.medicalmapper;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.example.ecss.medicalmapper.network.ConnectivityReceiver;
import com.example.ecss.medicalmapper.storage.sharedPrefrences.PreferencesHelper;

import java.util.Locale;

public class At3alegFenApplication extends Application {

    private static At3alegFenApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        checkLanguage();
        mInstance = this;
    }

    public static synchronized At3alegFenApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.mConnectivityReceiverListener = listener;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resetLocale();
    }

    private void resetLocale() {
        String appLanguage = PreferencesHelper.getAppLanguage(this);
        Locale locale = new Locale(appLanguage);
        Configuration newConfig = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            newConfig.setLocale(locale);
        }
        else {
            newConfig.locale = locale;
        }
        Locale.setDefault(locale);
        getBaseContext().getResources().updateConfiguration(
                newConfig, getBaseContext().getResources().getDisplayMetrics());
    }

    private void checkLanguage() {
        String appLanguage = PreferencesHelper.getAppLanguage(this);
        PreferencesHelper.setAppLanguage(this, appLanguage);

        final Resources resources = getBaseContext().getResources();
        Locale locale = new Locale(appLanguage);
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        Locale.setDefault(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}


