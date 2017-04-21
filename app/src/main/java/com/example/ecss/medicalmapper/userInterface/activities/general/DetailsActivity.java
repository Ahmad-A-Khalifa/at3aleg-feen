package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.userInterface.fragments.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {
    private static final String EXTRA_PLACE = "Place";

    public static void startActivity(Context context, MedicalPlace place) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, DetailsActivity.class).putExtra(EXTRA_PLACE, place));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }*/
        setContentView(R.layout.activity_details);

        if (getIntent() != null && getIntent().hasExtra(EXTRA_PLACE) && getIntent().getExtras().getParcelable(EXTRA_PLACE) != null) {

            DetailsFragment detailsFragment = DetailsFragment.newInstance((MedicalPlace) getIntent().getExtras().getParcelable(EXTRA_PLACE));
            getSupportFragmentManager().beginTransaction().replace(R.id.DetailsFragment, detailsFragment).commit();
        }
    }
}
