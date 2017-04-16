package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.activities.general.DoctorSignUpActivity;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ecss.medicalmapper.R.id.next_signup;
import static com.example.ecss.medicalmapper.R.id.switch_doctor;

public class SignUpFragment extends Fragment {
    private View mRootView;

    @BindView(next_signup)
    View mNextSignupView;

    @BindView(R.id.switch_doctor)
    TextView mSwitchDoctorTextView;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, mRootView);

        //mNextSignupView = (Button) mRootView.findViewById(R.id.next_signup);
        /*mNextSignupView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), HomeScreenActivity.class));

            }
        });*/

        // mSwitchDoctorTextView = (TextView) mRootView.findViewById(R.id.switch_doctor);
        /*mSwitchDoctorTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DoctorSignUpActivity.class));
            }
        });*/
        return mRootView;
    }

    @OnClick({next_signup, switch_doctor})
    public void onClick(View v) {

        switch (v.getId()) {
            case next_signup:
                startActivity(new Intent(getActivity(), HomeScreenActivity.class));
                break;

            case switch_doctor:
                startActivity(new Intent(getActivity(), DoctorSignUpActivity.class));
                break;
        }
    }
}
