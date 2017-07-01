package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.fragments.ReportMedicalPlaceFragment;

public class ReportMedicalPlaceActivity extends AppCompatActivity
        implements ReportMedicalPlaceFragment.OnFragmentInteractionListener {

    private static final String EXTRA_USER_ID = "extra_user_id";
    private static final String EXTRA_PLACE_ID = "extra_place_id";
    private static final String EXTRA_BRANCH_ID = "extra_branch_id";

    private static final String STATE_USER_ID = "state_user_id";
    private static final String STATE_PLACE_ID = "state_place_id";
    private static final String STATE_BRANCH_ID = "state_branch_id";

    private int mUserId;
    private int mPlaceId;
    private int mBranchId;

    public static void startActivity(Context context, int userId, int placeId, int branchId) {
        if (context != null) {
            context.startActivity(
                    new Intent(context, ReportMedicalPlaceActivity.class)
                            .putExtra(EXTRA_USER_ID, userId)
                            .putExtra(EXTRA_PLACE_ID, placeId)
                            .putExtra(EXTRA_BRANCH_ID, branchId)
            );
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_USER_ID, mUserId);
        outState.putInt(STATE_PLACE_ID, mPlaceId);
        outState.putInt(STATE_BRANCH_ID, mBranchId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_medical_place);
        initialize(savedInstanceState);
    }

    private void initialize(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(STATE_USER_ID) &&
                savedInstanceState.containsKey(STATE_PLACE_ID) &&
                savedInstanceState.containsKey(STATE_BRANCH_ID)) {
            mUserId = savedInstanceState.getInt(STATE_USER_ID);
            mPlaceId = savedInstanceState.getInt(STATE_PLACE_ID);
            mBranchId = savedInstanceState.getInt(STATE_BRANCH_ID);
        } else if (getIntent() != null &&
                getIntent().getExtras() != null &&
                getIntent().getExtras().containsKey(EXTRA_USER_ID) &&
                getIntent().getExtras().containsKey(EXTRA_PLACE_ID) &&
                getIntent().getExtras().containsKey(EXTRA_BRANCH_ID)) {
            mUserId = getIntent().getExtras().getInt(EXTRA_USER_ID);
            mPlaceId = getIntent().getExtras().getInt(EXTRA_PLACE_ID);
            mBranchId = getIntent().getExtras().getInt(EXTRA_BRANCH_ID);
        } else {
            Toast.makeText(this, getString(R.string.error_general), Toast.LENGTH_SHORT).show();
            return;
        }
        ReportMedicalPlaceFragment fragment =
                (ReportMedicalPlaceFragment) getSupportFragmentManager()
                        .findFragmentByTag(ReportMedicalPlaceFragment.TAG);
        if (fragment == null) {
            fragment = ReportMedicalPlaceFragment.newInstance(mUserId, mPlaceId, mBranchId);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_report_medical_place, fragment)
                .commit();
    }

    @Override
    public void onReportFinished() {
        ReportMedicalPlaceActivity.this.finish();
    }
}
