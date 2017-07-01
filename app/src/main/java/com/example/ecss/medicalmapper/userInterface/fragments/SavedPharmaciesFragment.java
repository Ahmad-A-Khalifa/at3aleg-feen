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
import com.example.ecss.medicalmapper.model.place.MedicalPlaces;
import com.example.ecss.medicalmapper.model.place.Pharmacy;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.userInterface.adapters.SavedLabsAdapter;
import com.example.ecss.medicalmapper.userInterface.adapters.SavedPharmaciesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.getUserFromSharedPreferences;

public class SavedPharmaciesFragment extends Fragment {
    private View mRootView;
    private static final String ARGUMENT_EXTRA_SAVED_PHARMACIES = "pharmacies";
    List<Pharmacy> pharmacies;

    @BindView(R.id.saved_pharmacies_list)
    RecyclerView mList;
    private SavedPharmaciesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SavedPharmaciesFragment() {
        // Required empty public constructor
    }

    public static SavedPharmaciesFragment newInstance(ArrayList<Pharmacy> pharmacyList) {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(ARGUMENT_EXTRA_SAVED_PHARMACIES, pharmacyList);
        SavedPharmaciesFragment fragment = new SavedPharmaciesFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_saved_pharmacies, container, false);
        ButterKnife.bind(this, mRootView);
        /*if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_SAVED_PHARMACIES) && getArguments().getParcelableArrayList(ARGUMENT_EXTRA_SAVED_PHARMACIES) != null) {

            pharmacies = getArguments().getParcelableArrayList(ARGUMENT_EXTRA_SAVED_PHARMACIES);
            if (pharmacies != null) {
                mAdapter = new SavedPharmaciesAdapter();
                mLayoutManager = new LinearLayoutManager(getActivity());
                mList.setLayoutManager(mLayoutManager);
                mList.setItemAnimator(new DefaultItemAnimator());
                mList.setAdapter(mAdapter);
                mAdapter.updateAdapter(pharmacies);
            }
        }*/

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
                                mAdapter = new SavedPharmaciesAdapter();
                                mLayoutManager = new LinearLayoutManager(getActivity());
                                mList.setLayoutManager(mLayoutManager);
                                mList.setItemAnimator(new DefaultItemAnimator());
                                mList.setAdapter(mAdapter);
                                if (places.getPharmacies() != null)
                                    mAdapter.updateAdapter(places.getPharmacies());

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

