package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.adapters.ViewPagerAdapter;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedClinicsFragment;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedHospitalsFragment;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedLabsFragment;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedPharmaciesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPlacesActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }*/
        setContentView(R.layout.activity_saved_places);

        ButterKnife.bind(this);

        // mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        // mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SavedHospitalsFragment(), "Hospital");
        adapter.addFragment(new SavedClinicsFragment(), "Clinic");
        adapter.addFragment(new SavedPharmaciesFragment(), "Pharmacy");
        adapter.addFragment(new SavedLabsFragment(), "Lab");
        viewPager.setAdapter(adapter);
    }


}