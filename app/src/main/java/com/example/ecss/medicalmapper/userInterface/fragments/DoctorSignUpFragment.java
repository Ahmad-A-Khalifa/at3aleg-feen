package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.activities.general.PaymentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ecss.medicalmapper.R.id.degree;
import static com.example.ecss.medicalmapper.R.id.next_payment;
import static com.example.ecss.medicalmapper.R.id.specialization;

/**
 * A placeholder fragment containing a simple view.
 */
public class DoctorSignUpFragment extends Fragment {
    private View mRootView;

    @BindView(degree)
    Button mDegreeButton;

    @BindView(specialization)
    Button mSpecializationButton;

    @BindView(next_payment)
    Button mNextPaymentButton;


    public DoctorSignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_doctor_sign_up, container, false);

        ButterKnife.bind(this, mRootView);

        //mDegreeButton = (Button) mRootView.findViewById(R.id.degree);
        /*mDegreeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }

        });*/
        //mSpecializationButton = (Button) mRootView.findViewById(R.id.specialization);
        /*mSpecializationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });*/
        //final Button button = (Button) mRootView.findViewById(R.id.next_payment);
        /*mNextPaymentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), PaymentActivity.class));
            }
        });*/

        return mRootView;
    }

    @OnClick({R.id.degree, R.id.specialization, R.id.next_payment})
    public void onClick(View v) {

        switch (v.getId()) {
            case degree:

/*                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Doctor Degrees");

                ListView modeList = new ListView(getContext());
                String[] stringArray = DoctorInfo.DEGREE;
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
                modeList.setAdapter(modeAdapter);

                builder.setView(modeList);
                final Dialog dialog = builder.create();

                dialog.show();*/
                break;

            case specialization:
/*AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Doctor Specializations");

                ListView modeList = new ListView(getContext());
                String[] stringArray = DoctorInfo.SPECIALIZATION;
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
                modeList.setAdapter(modeAdapter);

                builder.setView(modeList);
                final Dialog dialog = builder.create();

                dialog.show();*/
                break;

            case next_payment:
                startActivity(new Intent(getActivity(), PaymentActivity.class));
                break;
        }
    }
}
