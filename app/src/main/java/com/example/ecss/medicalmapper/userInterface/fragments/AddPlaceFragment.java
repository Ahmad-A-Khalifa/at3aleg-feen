package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.user.Doctor;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;
import com.example.ecss.medicalmapper.utility.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ecss.medicalmapper.R.color.dark_blue;
import static com.example.ecss.medicalmapper.R.id.add_place_address_notes;
import static com.example.ecss.medicalmapper.R.id.add_place_building_number;
import static com.example.ecss.medicalmapper.R.id.add_place_finish;
import static com.example.ecss.medicalmapper.R.id.add_place_phone_number;
import static com.example.ecss.medicalmapper.R.id.add_place_place_name;
import static com.example.ecss.medicalmapper.R.id.add_place_specialization;
import static com.example.ecss.medicalmapper.R.id.add_place_street;


public class AddPlaceFragment extends Fragment {
    @BindView(add_place_place_name)
    EditText mPlaceName;

    @BindView(add_place_specialization)
    EditText mPlaceSpecialization;

    @BindView(add_place_building_number)
    EditText mBuildingNumber;

    @BindView(add_place_street)
    EditText mStreet;

    @BindView(add_place_address_notes)
    EditText mAddressNotes;

    @BindView(add_place_phone_number)
    EditText mPhoneNumber;

    @BindView(add_place_finish)
    Button mFinishButton;

    public static AddPlaceFragment newInstance() {
        Bundle args = new Bundle();
        // args.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        AddPlaceFragment fragment = new AddPlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AddPlaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_place, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({add_place_finish})
    public void onClick(View v) {

        switch (v.getId()) {
            case add_place_finish:
                Doctor doctor = Utility.getPremiumUserFromSharedPreferences(getActivity());
                if (doctor.getUserId() != -1) {
                    /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    AddPlaceRequest request = new AddPlaceRequest(doctor.getUserId(), mPlaceName.toString(), mPlaceSpecialization.toString(), mBuildingNumber.toString(), mStreet.toString(), mAddressNotes.toString(), mPhoneNumber.toString());
                    Call<ApiCallStatus> call = apiService.AddPlace(request);
                    call.enqueue(new Callback<ApiCallStatus>() {
                        @Override
                        public void onResponse(Call<ApiCallStatus> call, Response<ApiCallStatus> response) {
                            ApiCallStatus status = null;

                            if (response.body() != null) {
                                status = response.body();
                            }

                            if (status != null && status.getIsSuccessful()) {

                            } else {
                                if (status != null && status.getErrorStatus() != null)
                                    Toast.makeText(getActivity(), status.getErrorStatus(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiCallStatus> call, Throwable t) {
                        }
                    });*/
                }

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());
                mDialogBuilder.setTitle(getString(R.string.add_new_place));
                mDialogBuilder.setMessage(getString(R.string.add_new_place_success));
                mDialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        HomeScreenActivity.startActivity(getActivity());
                    }
                });

                AlertDialog mAlertDialog = mDialogBuilder.create();
                mAlertDialog.show();
                mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(dark_blue));

                break;
        }
    }
}
