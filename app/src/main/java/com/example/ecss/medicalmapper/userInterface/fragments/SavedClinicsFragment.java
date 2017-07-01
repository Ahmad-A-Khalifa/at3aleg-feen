package com.example.ecss.medicalmapper.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.PlacesResponse;
import com.example.ecss.medicalmapper.model.place.Clinic;
import com.example.ecss.medicalmapper.model.place.MedicalPlaces;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.userInterface.adapters.SavedClinicsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.getUserFromSharedPreferences;


public class SavedClinicsFragment extends Fragment {
    private View mRootView;
    private static final String ARGUMENT_EXTRA_SAVED_CLINIC = "clinic";

    List<Clinic> mClinics;

    @BindView(R.id.saved_clinics_list)
    RecyclerView mList;

    private SavedClinicsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SavedClinicsFragment() {

    }

    public static SavedClinicsFragment newInstance(ArrayList<Clinic> clinicList) {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(ARGUMENT_EXTRA_SAVED_CLINIC, clinicList);
        SavedClinicsFragment fragment = new SavedClinicsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_saved_clinics, container, false);
        ButterKnife.bind(this, mRootView);
       /* if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_SAVED_CLINIC) && getArguments().getParcelable(ARGUMENT_EXTRA_SAVED_CLINIC) != null) {

            mClinics = getArguments().getParcelable(ARGUMENT_EXTRA_SAVED_CLINIC);*/

        callSavedPlacesFromApi();


        return mRootView;
    }

    private void callSavedPlacesFromApi() {
        if (checkInternetConnection()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            if (getUserFromSharedPreferences(getContext()).getUserId() != -1) {
                Call<PlacesResponse> call = apiService.GetSavedPlaces(getUserFromSharedPreferences(getContext()).getUserId());
                call.enqueue(new Callback<PlacesResponse>() {
                    @Override
                    public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {

                        MedicalPlaces places = null;
                        ApiCallStatus apiCallStatus = null;

                        if (response.body() != null) {
                            places = response.body().getMedicalPlaces();
                            apiCallStatus = response.body().getApiCallStatus();
                        }

                        if (apiCallStatus != null && places != null && apiCallStatus.getIsSuccessful()) {
                            mAdapter = new SavedClinicsAdapter();
                            mLayoutManager = new LinearLayoutManager(getActivity());
                            mList.setLayoutManager(mLayoutManager);
                            mList.setItemAnimator(new DefaultItemAnimator());
                            mList.setAdapter(mAdapter);
                            if (places.getClinics() != null)
                                mAdapter.updateAdapter(places.getClinics());

                        } else {
                            if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                                Toast.makeText(getContext(), apiCallStatus.getErrorStatus(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlacesResponse> call, Throwable t) {
                    }
                });
            } else {
                Toast.makeText(getActivity(), getString(R.string.sign_in_problem_message), Toast.LENGTH_LONG).show();
            }
        }
    }
}
