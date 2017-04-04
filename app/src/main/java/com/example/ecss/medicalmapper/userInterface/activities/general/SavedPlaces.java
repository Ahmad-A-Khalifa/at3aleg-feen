package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.adapters.ViewPagerAdapter;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedClinics;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedHospitals;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedLabs;
import com.example.ecss.medicalmapper.userInterface.fragments.SavedPharmacies;

public class SavedPlaces extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_places);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SavedHospitals(), "Hospital");
        adapter.addFragment(new SavedClinics(), "Clinic");
        adapter.addFragment(new SavedPharmacies(), "Pharmacy");
        adapter.addFragment(new SavedLabs(), "Lab");
        viewPager.setAdapter(adapter);
    }


}