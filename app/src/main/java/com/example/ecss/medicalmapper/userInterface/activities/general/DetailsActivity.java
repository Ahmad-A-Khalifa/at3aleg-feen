package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ecss.medicalmapper.At3alegFenApplication;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.network.ConnectivityReceiver;
import com.example.ecss.medicalmapper.userInterface.fragments.DetailsFragment;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbar;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbarDisconnected;

public class DetailsActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private static final String EXTRA_PLACE = "Place";

    public static void startActivity(Context context, MedicalPlace place) {
        if (context == null) {
            return;
        }

        Intent i = new Intent(context, DetailsActivity.class).putExtra(EXTRA_PLACE, place);
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }*/
        setContentView(R.layout.activity_details);
        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }

        if (getIntent() != null && getIntent().hasExtra(EXTRA_PLACE) && getIntent().getExtras().getParcelable(EXTRA_PLACE) != null) {

            DetailsFragment detailsFragment = DetailsFragment.newInstance((MedicalPlace) getIntent().getExtras().getParcelable(EXTRA_PLACE));
            getSupportFragmentManager().beginTransaction().replace(R.id.DetailsFragment, detailsFragment).commit();
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
