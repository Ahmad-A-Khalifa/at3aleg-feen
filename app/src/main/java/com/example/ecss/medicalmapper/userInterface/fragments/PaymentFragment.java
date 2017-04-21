package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ecss.medicalmapper.R.id.finish_btn;

/**
 * A placeholder fragment containing a simple view.
 */
public class PaymentFragment extends Fragment {
    @BindView(finish_btn)
    Button mFinishButton;
    private View mRootView;


    public PaymentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_payment, container, false);
        ButterKnife.bind(this, mRootView);

        //final Button button = (Button) mRootView.findViewById(R.id.finish_btn);
        /*mFinishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), HomeScreenActivity.class));

            }

        });*/
        return mRootView;
    }

    @OnClick({R.id.finish_btn})
    public void onClick(View v) {

        switch (v.getId()) {

            case finish_btn:
                startActivity(new Intent(getActivity(), HomeScreenActivity.class));
                break;
        }
    }
}
