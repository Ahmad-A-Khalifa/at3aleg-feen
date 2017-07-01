package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ecss.medicalmapper.At3alegFenApplication;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.network.ConnectivityReceiver;
import com.example.ecss.medicalmapper.userInterface.adapters.ViewPagerAdapter;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedClinicsFragment;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedHospitalsFragment;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedLabsFragment;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedPharmaciesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbar;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbarDisconnected;

public class SavedPlacesActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static void startActivity(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, SavedPlacesActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_places);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        SavedClinicsFragment savedClinicsFragment = new SavedClinicsFragment();

        adapter.addFragment(savedClinicsFragment, "Clinic");

        SavedHospitalsFragment savedHospitalsFragment = new SavedHospitalsFragment();

        adapter.addFragment(savedHospitalsFragment, "Hospital");

        SavedPharmaciesFragment savedPharmaciesFragment = new SavedPharmaciesFragment();

        adapter.addFragment(savedPharmaciesFragment, "Pharmacy");

        SavedLabsFragment savedLabsFragment = new SavedLabsFragment();

        adapter.addFragment(savedLabsFragment, "Lab");
        viewPager.setAdapter(adapter);
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


