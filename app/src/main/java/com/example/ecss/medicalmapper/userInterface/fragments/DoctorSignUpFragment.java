package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.activities.general.Payment;

/**
 * A placeholder fragment containing a simple view.
 */
public class DoctorSignUpFragment extends Fragment {
    private View mRootView;

    public DoctorSignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_doctor_sign_up, container, false);

        final Button button1 = (Button) mRootView.findViewById(R.id.degree);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

/*                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Doctor Degrees");

                ListView modeList = new ListView(getContext());
                String[] stringArray = DoctorInfo.DEGREE;
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
                modeList.setAdapter(modeAdapter);

                builder.setView(modeList);
                final Dialog dialog = builder.create();

                dialog.show();*/
            }

        });
        final Button button2 = (Button) mRootView.findViewById(R.id.specialization);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Doctor Specializations");

                ListView modeList = new ListView(getContext());
                String[] stringArray = DoctorInfo.SPECIALIZATION;
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
                modeList.setAdapter(modeAdapter);

                builder.setView(modeList);
                final Dialog dialog = builder.create();

                dialog.show();*/
            }

        });
        final Button button = (Button) mRootView.findViewById(R.id.next_payment);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Payment.class);
                startActivity(intent);
            }
        });

        return mRootView;
    }
}
