package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.activities.general.DoctorSignUp;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreen;

/**
 * A placeholder fragment containing a simple view.
 */
public class SignUpFragment extends Fragment {
    private View rootView;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        final Button button = (Button) rootView.findViewById(R.id.next_signup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeScreen.class);
                startActivity(intent);

            }
        });

        final TextView textView = (TextView) rootView.findViewById(R.id.switch_doctor);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DoctorSignUp.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
