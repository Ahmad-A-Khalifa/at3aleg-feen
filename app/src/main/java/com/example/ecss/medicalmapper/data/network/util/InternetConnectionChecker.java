package com.example.ecss.medicalmapper.data.network.util;

import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * This class is important for checking the internet connectivity before invoking any network-
 * related calls.
 */
public class InternetConnectionChecker implements BaseInternetConnectionChecker {

    private Context mContext;

    /**
     * Parametrized constructor.
     * @param context a context from which the checker is created to check the connection
     */
    public InternetConnectionChecker(Context context) {
        mContext = context;
    }

    /**
     * This method checks the internet connection and returns true if the device is connected
     * to the internet, false otherwise.
     * @return true if device is connected to the internet, false otherwise.
     */
    @Override
    public boolean isNetworkAvailable(){
        NetworkInfo info =
                ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE))
                        .getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
