package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.ecss.medicalmapper.At3alegFenApplication;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.network.ConnectivityReceiver;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbar;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbarDisconnected;


public class SignInActivity extends FragmentActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    public static void startActivity(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, SignInActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        At3alegFenApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnackbar(isConnected, findViewById(android.R.id.content), this);
    }
}
