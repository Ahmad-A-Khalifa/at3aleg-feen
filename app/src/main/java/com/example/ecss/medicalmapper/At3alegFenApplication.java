package com.example.ecss.medicalmapper;

import android.app.Application;

import com.example.ecss.medicalmapper.network.ConnectivityReceiver;

public class At3alegFenApplication extends Application {

    private static At3alegFenApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized At3alegFenApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.mConnectivityReceiverListener = listener;
    }
}


