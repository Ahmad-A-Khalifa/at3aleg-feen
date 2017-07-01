package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ecss.medicalmapper.At3alegFenApplication;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.network.ConnectivityReceiver;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.PlacesSearchBranch;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.SearchResultsWrapper;
import com.example.ecss.medicalmapper.userInterface.fragments.DisplaySearchPlacesFragment;

import java.util.List;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbar;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbarDisconnected;

public class DisplaySearchPlacesActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    public static final String EXTRA_SEARCH_PLACE = "SearchPlace";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_places);

        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }

        if (getIntent() != null && getIntent().hasExtra(EXTRA_SEARCH_PLACE) && getIntent().getExtras().getSerializable(EXTRA_SEARCH_PLACE) != null) {

            DisplaySearchPlacesFragment displaySearchPlacesFragment = DisplaySearchPlacesFragment.newInstance((SearchResultsWrapper) getIntent().getExtras().getSerializable(EXTRA_SEARCH_PLACE));
            getSupportFragmentManager().beginTransaction().replace(R.id.display_search_places_fragment, displaySearchPlacesFragment).commit();
        }
    }

    public static void startActivity(Context context, List<PlacesSearchBranch> branches) {
        if (context == null) {
            return;
        }

        SearchResultsWrapper wrapper = new SearchResultsWrapper(branches);

        Intent i = new Intent(new Intent(context, DisplaySearchPlacesActivity.class).putExtra(EXTRA_SEARCH_PLACE, wrapper));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
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
